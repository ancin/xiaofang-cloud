package com.diandian.framework.netty.entity;

import java.io.Serializable;

/**
 * @ClassName BaseMessage
 * @description:
 * @author: ancin
 * @create: 2020-09-14 13:32
 * @Version 1.0
 **/
public class BaseMessage implements Serializable {
    protected int sequence;

    public int getSequence() {
        return sequence;
    }
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
