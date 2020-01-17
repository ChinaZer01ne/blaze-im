package com.github.blaze;

import com.github.blaze.protocol.BlazeProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.StringUtil;

/**
 * im encoder
 * protocol: header + body
 * @author Zer01ne
 * @since 2020/1/17 23:41
 */
public class ImEncoder extends MessageToByteEncoder<BlazeProtocol> {

    public static final int  EMPTY_STR_LENGTH = 0;
    @Override
    protected void encode(ChannelHandlerContext ctx, BlazeProtocol msg, ByteBuf out) throws Exception {

        // data type
        Integer protocolType = msg.getProtocolType();
        out.writeInt(protocolType);

        // data
        String body = msg.getBody();

        if (StringUtil.isNullOrEmpty(body)) {
            out.writeInt(EMPTY_STR_LENGTH);
        }else {
            int length = body.length();
            // data length
            out.writeInt(length);
            // data content
            out.writeBytes(body.getBytes());
        }

    }
}
