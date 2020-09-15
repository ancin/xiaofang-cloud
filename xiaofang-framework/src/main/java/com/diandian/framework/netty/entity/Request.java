package com.diandian.framework.netty.entity;

import java.io.Serializable;

/**
 * @ClassName Request
 * @description:
 * @author: ancin
 * @create: 2020-09-14 13:34
 * @Version 1.0
 **/
public class Request  extends BaseMessage implements Serializable {
    private Object message;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request [sequence=");
        sb.append(sequence);
        sb.append(", message=");
        sb.append(message);
        sb.append("]");
        return sb.toString();
    }
}
