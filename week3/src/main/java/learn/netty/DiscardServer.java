package learn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty官网抛弃服务
 * @author xxl
 */
public class DiscardServer {

    private static final  int PORT = 8888;

    public void run() throws Exception{
        //创建两个事件循环器，分别用来接收和处理已被接收的请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try{
            //启动类
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
//                                    .addFirst(new TimeServerHandler())
                                    .addLast(new PrintChannelHandler())
                                    .addLast(new DiscardChannelHandler());
                        }
                    })
                    //设置Channel的参数
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //绑定端口， 开始接收连接
            ChannelFuture f = b.bind(PORT).sync();

            //等待服务器socket关闭
            f.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new DiscardServer().run();
    }
}
