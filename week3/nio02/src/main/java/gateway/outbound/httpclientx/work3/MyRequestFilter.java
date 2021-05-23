package gateway.outbound.httpclientx.work3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 自定义过滤器，过滤请求的url
 * @author xxl
 */
public class MyRequestFilter extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;

        String uri = fullHttpRequest.uri();
        if(uri.contains("/testFilter")){
            ctx.fireExceptionCaught(new RuntimeException("被拦截，未允许的请求"));
        }else {
            //执行下一个inboundhandler
            ctx.fireChannelRead(msg);
        }
    }
}
