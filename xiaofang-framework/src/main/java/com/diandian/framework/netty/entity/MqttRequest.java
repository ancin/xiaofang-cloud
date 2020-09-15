package com.diandian.framework.netty.entity;

import io.netty.handler.codec.mqtt.MqttQoS;

/**
 * @ClassName MqttRequest
 * @description:
 * @author: ancin
 * @create: 2020-09-15 14:57
 * @Version 1.0
 **/
public class MqttRequest {
    private boolean mutable = true;
    private byte[] payload;
    private MqttQoS qos = MqttQoS.AT_MOST_ONCE;
    private boolean retained = false;
    private boolean dup = false;
    private int messageId;

    public MqttRequest() {
        this.setPayload(new byte[0]);
    }

    public MqttRequest(byte[] payload) {
        this.setPayload(payload);
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public void clearPayload() {
        this.checkMutable();
        this.payload = new byte[0];
    }

    public void setPayload(byte[] payload) {
        this.checkMutable();
        if (payload == null) {
            throw new NullPointerException();
        } else {
            this.payload = payload;
        }
    }

    public boolean isRetained() {
        return this.retained;
    }

    public void setRetained(boolean retained) {
        this.checkMutable();
        this.retained = retained;
    }

    public MqttQoS getQos() {
        return qos;
    }

    public void setQos(MqttQoS qos) {
        this.qos = qos;
    }

    public boolean isMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    protected void checkMutable() throws IllegalStateException {
        if (!this.mutable) {
            throw new IllegalStateException();
        }
    }

    public boolean isDup() {
        return dup;
    }

    public void setDup(boolean dup) {
        this.dup = dup;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return new String(this.payload);
    }
}
