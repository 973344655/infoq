package gateway.outbound.httpclientx.work1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 之前的httpclient,整合netty 发送请求
 * @author xxl
 */
public class MyHttpOutboundHandler {
    /**
     * httpclient
     */
    private final CloseableHttpClient httpClient;

    /**
     * 使用线程池执行请求
     */
    private final ExecutorService executor;

    private final String server;

    public MyHttpOutboundHandler(String server){
        this.server = server;
        int cores = Runtime.getRuntime().availableProcessors();

        //自定义线程池
        executor = new ThreadPoolExecutor(
                cores,
                cores,
                5L,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1024),
                new MyThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        //初始化httpclient
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000*60)
                .build();
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                .build();



    }

    /**
     * 提交任务
     * @param fullRequest
     * @param ctx
     */
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        //路由选择
        String url;
        if(!StringUtils.isEmpty(fullRequest.headers().get("gateway_nio_server"))){
            url = fullRequest.headers().get("gateway_nio_server") + fullRequest.uri();
        }else {
            url = this.server + fullRequest.uri();
        }
        executor.submit(()->fetchGet(fullRequest, ctx, url));
    }

    /**
     * 代理发送请求，并将结果返回
     * @param fullRequest
     * @param ctx
     * @param url
     */
    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx,
                     final String url){

        FullHttpResponse response = null;

        try {
            String msg = get(url);

            byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
            ByteBuf buf = Unpooled.wrappedBuffer(bytes);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK,buf);

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", bytes.length);

            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            ctx.flush();
        }catch (Exception e){
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        }finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }

    }

    /**
     *
     * @param url
     * @param params [0] 请求参数, [1] headers
     * @return
     */
    public  String get(String url, Map<String,String>...params){

        if(StringUtils.isEmpty(url)){
            throw new RuntimeException("url不能为空");
        }

        //请求参数
        if(params != null && params.length > 0){
            url = setGetParams(url, params[0]);
        }

        HttpGet get = new HttpGet(url);

        //headers
        if(params != null && params.length > 1){
            setGetHeaders(get, params[1]);
        }

        CloseableHttpResponse response = null;

        //发送请求
        try {
            response = httpClient.execute(get);
            if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
            throw new RuntimeException("发生错误: " + response.getStatusLine().getStatusCode());
        }catch (IOException ioe){
            throw new RuntimeException("请求发生异常: " + ioe.getMessage());
        }finally {
            close(response);
        }


    }

    /**
     * 拼接参数
     * @param url
     * @param params
     */
    private  String setGetParams(String url, Map<String,String> params){
        if(!params.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Map.Entry entry : params.entrySet()){
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                sb.append("&");
            }
            sb.deleteCharAt(sb.lastIndexOf("&"));
            return url + "?" + sb.toString();
        }
        return url;
    }

    /**
     * 设置header
     * @param get
     * @param headers
     */
    private  void setGetHeaders(HttpGet get, Map<String,String> headers){
        if(!headers.isEmpty()){
            for(Map.Entry<String,String> entry : headers.entrySet()){
                get.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 关闭流
     * @param res
     */
    private  void close(Closeable res){
        if(res != null){
            try {
                res.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



}
