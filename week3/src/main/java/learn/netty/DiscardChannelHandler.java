package learn.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 自定义的入站处理Handler
 * @author xxl
 */
public class DiscardChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取channle数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        //丢弃所有数据
//        ((ByteBuf)msg).release();
        //原样返回数据
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        //遇到异常，关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
