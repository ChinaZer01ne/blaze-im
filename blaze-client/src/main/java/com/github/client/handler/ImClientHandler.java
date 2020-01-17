package com.github.client.handler;

import com.github.blaze.enums.ProtocolTypeEnum;
import com.github.blaze.protocol.BlazeProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
/**
 * @author Zer01ne
 * @since 2020/1/18 0:32
 */
public class ImClientHandler extends SimpleChannelInboundHandler<BlazeProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BlazeProtocol msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        BlazeProtocol data = new BlazeProtocol();
        data.setProtocolType(ProtocolTypeEnum.DATA.getType());
        data.setBody("Hello World!");
        ctx.writeAndFlush(data);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
