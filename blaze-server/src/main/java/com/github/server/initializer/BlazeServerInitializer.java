package com.github.server.initializer;

import com.github.blaze.ImDecoder;
import com.github.blaze.ImEncoder;
import com.github.server.handler.ImServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Zer01ne
 * @since 2020/1/18 1:30
 */
public class BlazeServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ImEncoder());
        pipeline.addLast(new ImDecoder());
        pipeline.addLast(new ImServerHandler());
    }
}
