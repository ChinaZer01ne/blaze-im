package com.github.blaze.protocol;

import lombok.Data;

/**
 * @author Zer01ne
 * @since 2020/1/17 22:31
 */
@Data
public class BlazeProtocol {
    /**
     * protocol type
     * */
    private Integer protocolType;
    /**
     * content
     * */
    private String body;
}
