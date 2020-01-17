package com.github.blaze;

import com.github.blaze.protocol.BlazeProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
/**
 * im decoder
 * @author Zer01ne
 * @since 2020/1/17 23:44
 */
public class ImDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < 8) {
            return;
        }
        // protocol type
        int protocolType = in.readInt();
        // data length
        int bodyLength = in.readInt();
        if (in.readableBytes() < bodyLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] body = new byte[bodyLength];
        in.readBytes(body);

        BlazeProtocol protocol = new BlazeProtocol();
        protocol.setProtocolType(protocolType);
        protocol.setBody(new String(body));
        out.add(protocol);
    }
}
