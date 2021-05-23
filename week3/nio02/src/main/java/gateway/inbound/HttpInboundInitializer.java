package gateway.inbound;

import gateway.outbound.httpclientx.work4.RouteChannelHandler;
import gateway.outbound.httpclientx.work1.MyHttpInboundHandler;
import gateway.outbound.httpclientx.work3.MyRequestFilter;
import gateway.outbound.httpclientx.work3.MyResponseFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
	
	private List<String> proxyServer;
	
	public HttpInboundInitializer(List<String> proxyServer) {
		this.proxyServer = proxyServer;
	}
	
	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
//		if (sslCtx != null) {
//			p.addLast(sslCtx.newHandler(ch.alloc()));
//		}
		p.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		//注册响应信息过滤器
		p.addLast(new MyResponseFilter());
//		p.addLast(new HttpInboundHandler(this.proxyServer));
		//随机路由
		p.addLast(new RouteChannelHandler(this.proxyServer));
		//注册请求信息过滤器
		p.addLast(new MyRequestFilter());
		//整合之前的httpclient
		p.addLast(new MyHttpInboundHandler(this.proxyServer));
	}
}
