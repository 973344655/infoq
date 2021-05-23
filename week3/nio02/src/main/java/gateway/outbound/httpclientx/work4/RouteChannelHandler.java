package gateway.outbound.httpclientx.work4;

import gateway.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

/**
 * 路由，随机路由
 * @author xxl
 */
public class RouteChannelHandler extends ChannelInboundHandlerAdapter {

    private final List<String> servers;

    public RouteChannelHandler(List<String> servers){
        this.servers = servers;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        //随机路由
        ((FullHttpRequest) msg).headers()
                .set("gateway_nio_server",new RandomHttpEndpointRouter().route(servers));
        //通知下一个inbound
        ctx.fireChannelRead(request);
    }
}
