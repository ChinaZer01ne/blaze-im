package com.github.blaze.enums;
/**
 * protocol type
 * @author Zer01ne
 * @since 2020/1/18 1:12
 */
public enum ProtocolTypeEnum {
    /**
     * 消息数据传输
     * */
    DATA(1,"消息数据传输");

    /**
     * status
     * */
    private Integer type;
    /**
     * status
     * */
    private String desc;

    ProtocolTypeEnum(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
