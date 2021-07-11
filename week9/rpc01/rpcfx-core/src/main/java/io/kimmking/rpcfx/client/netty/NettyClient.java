package io.kimmking.rpcfx.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import okhttp3.MediaType;

public class NettyClient {

    public void connect(int port, String host, String req, MediaType type) throws Exception{
        String url = "http://" + host + ":" + port + "/";
         //创建客户端处理I/O读写的NioEventLoopGroup Group线程组
         EventLoopGroup group = new NioEventLoopGroup();
         try{
             //创建客户端辅助启动类Bootstrap
             Bootstrap b = new Bootstrap();
             b.group(group).channel(NioSocketChannel.class)
             .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>(){
            //将ChannelHandler设置到ChannelPipleline中，用于处理网络I/O事件
             @Override
             protected void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline()
                             .addLast(new SendRequestHandler(url, req ,type));
//                     .addLast(new ReadHandler());
            }
         });
             //发起异步连接操作，然后调用同步方法等待连接成功。
             ChannelFuture f = b.connect(host,port).sync();

             //等待客户端链路关闭
             f.channel().closeFuture().sync();
            }finally{
                 //优雅退出，释放NIO线程组
                 group.shutdownGracefully();
             }
         }



}
