package com.github.client;

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
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
                            }


                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello World".getBytes()));
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                ctx.close();
                            }
                        });
                    }
                });

        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
