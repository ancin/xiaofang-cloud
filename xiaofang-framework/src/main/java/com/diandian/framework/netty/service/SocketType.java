package com.diandian.framework.netty.service;

/**
 * @ClassName SocketType
 * @author: ancin
 * @create: 2020-09-14 13:45
 * @Version 1.0
 **/
public enum SocketType {
    /**
     * 普通socket
     */
    NORMAL,
    /**
     * MQTT
     */
    MQTT,
    /**
     * MQTT web socket
     */
    MQTT_WS;
}
