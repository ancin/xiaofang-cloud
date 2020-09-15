package com.diandian.framework.netty.entity;

import java.io.Serializable;

/**
 * @ClassName Heartbeat
 * @description:
 * @author: ancin
 * @create: 2020-09-14 13:38
 * @Version 1.0
 **/
public class Heartbeat extends BaseMessage implements Serializable {
    public static final byte[] BYTES = new byte[0];

    private Heartbeat() {
    }

    private static Heartbeat instance = new Heartbeat();

    public static Heartbeat getSingleton() {
        return instance;
    }


}
