package gateway.outbound.httpclientx.work3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;


/**
 * 拦截返回信息
 * 添加响应头
 * @author xxl
 */
public class MyResponseFilter extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        FullHttpResponse fullHttpResponse = (FullHttpResponse) msg;

        //添加响应头
        fullHttpResponse.headers().set("hello","nio");
        fullHttpResponse.headers().set("x-content-type-options","nosniff");
        fullHttpResponse.headers().set("x-xss-protection","1; mode=block");
        fullHttpResponse.headers().set("x-download-options","noopen");

        ctx.write(fullHttpResponse);
        ctx.flush();

    }
}
