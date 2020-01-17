package com.github.server.handler;

import com.github.blaze.protocol.BlazeProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
/**
 * @author Zer01ne
 * @since 2020/1/18 0:30
 */
public class ImServerHandler extends SimpleChannelInboundHandler<BlazeProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BlazeProtocol msg) throws Exception {
        System.out.println(msg);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
