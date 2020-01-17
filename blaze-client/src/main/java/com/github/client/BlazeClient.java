package com.github.client;

import com.github.blaze.ImDecoder;
import com.github.blaze.ImEncoder;
import com.github.client.config.ClientConfig;
import com.github.client.handler.ImClientHandler;
import com.github.client.initializer.BlazeClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @author Zer01ne
 * @since 2020/1/15 22:40
 */
public class BlazeClient {
    public static void main(String[] args) {
        new BlazeClient().start();
    }
    /**
     * client start
     */
    private void start() {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new BlazeClientInitializer());

        try {
            ChannelFuture future = bootstrap.connect(ClientConfig.SERVER_HOST, ClientConfig.SERVER_POST).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
