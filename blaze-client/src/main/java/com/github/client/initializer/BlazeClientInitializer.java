package com.github.client.initializer;

import com.github.blaze.ImDecoder;
import com.github.blaze.ImEncoder;
import com.github.client.handler.ImClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Zer01ne
 * @since 2020/1/18 1:30
 */
public class BlazeClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ImEncoder());
        pipeline.addLast(new ImDecoder());
        pipeline.addLast(new ImClientHandler());
    }
}
