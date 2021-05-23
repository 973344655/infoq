package gateway.outbound.httpclientx.work1;


import gateway.filter.HeaderHttpRequestFilter;
import gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * ChannelInboundHandler 用于代理发送请求
 * @author xxl
 */
@Slf4j
public class MyHttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final List<String> proxyServer;
    private MyHttpOutboundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public MyHttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        //只使用了一个server
        this.handler = new MyHttpOutboundHandler(this.proxyServer.get(0));
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            //使用自定义handler处理请求
            handler.handle(fullRequest, ctx);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
