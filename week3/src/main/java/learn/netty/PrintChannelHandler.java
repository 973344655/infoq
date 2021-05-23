package learn.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/**
 * 用来打印接到的信息
 * @author xxl
 */
public class PrintChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //打印接收到的信息
        try {
//            while (in.isReadable()){
//                System.out.println((char) in.readByte());
//                System.out.flush();
//            }
            System.out.println(in.toString(CharsetUtil.UTF_8));
        }finally {
            in.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
