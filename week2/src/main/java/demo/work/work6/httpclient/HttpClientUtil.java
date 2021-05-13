package demo.work.work6.httpclient;


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

/**
 * 使用HttpClient 发送Get请求
 *
 * @author xxl
 */
public class HttpClientUtil {

    private static final CloseableHttpClient httpClient;

    static {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000*60)
                .build();
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();
    }

    /**
     *
     * @param url
     * @param params [0] 请求参数, [1] headers
     * @return
     */
    public static String get(String url, Map<String,String> ...params){

        if(StringUtils.isEmpty(url)){
            throw new RuntimeException("url不能为空");
        }

        //请求参数
        if(params != null && params.length > 0){
            setGetParams(url, params[0]);
        }


        HttpGet get = new HttpGet(url);

        //headers
        if(params != null && params.length > 1){
            setGetHeaders(get, params[1]);
        }

        CloseableHttpResponse response = null;

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
    private static void setGetParams(String url, Map<String,String> params){

        if(!params.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Map.Entry entry : params.entrySet()){
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                sb.append("&");
            }
            url += "?" + sb.toString();
            url.replace(url.charAt(url.lastIndexOf("&")) + "","");
        }
    }

    /**
     * 设置header
     * @param get
     * @param headers
     */
    private static void setGetHeaders(HttpGet get, Map<String,String> headers){
        if(!headers.isEmpty()){
            for(Map.Entry<String,String> entry : headers.entrySet()){
                get.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    private static void close(Closeable res){
        if(res != null){
            try {
                res.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
