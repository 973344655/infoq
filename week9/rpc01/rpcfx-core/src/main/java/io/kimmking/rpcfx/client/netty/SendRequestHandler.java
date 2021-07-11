package io.kimmking.rpcfx.client.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SendRequestHandler extends ChannelInboundHandlerAdapter {

    private String url;

    private String req;
    private MediaType type;

    public SendRequestHandler(String url, String req, MediaType type){
        this.url = url;
        this.req = req;
        this.type = type;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String respJson = "";
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(type, req))
                .build();
        try {
             respJson = client.newCall(request).execute().body().string();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(respJson);
//            ctx.writeAndFlush(respJson);
            ctx.channel().close();

        }

    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("555555555555555555555555");
//        FullHttpResponse response = (FullHttpResponse)msg;
//        ByteBuf buf = response.content();
//        String result = buf.toString(CharsetUtil.UTF_8);
//        System.out.println("response -> "+result);
//        re = result;
//    }
}
