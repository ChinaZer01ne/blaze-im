package com.github.server;

import com.github.blaze.ImDecoder;
import com.github.blaze.ImEncoder;
import com.github.server.config.ServerConfig;
import com.github.server.handler.ImServerHandler;
import com.github.server.initializer.BlazeServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

/**
 * @author Zer01ne
 * @since 2020/1/15 22:40
 */
public class BlazeServer {
    public static void main(String[] args) {
        new BlazeServer().start();
    }
    /**
     * server start
     */
    private void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new BlazeServerInitializer());
        try {
            ChannelFuture future = serverBootstrap.bind(ServerConfig.LISTEN_PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
