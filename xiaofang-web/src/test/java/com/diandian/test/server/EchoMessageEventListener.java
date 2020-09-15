package com.diandian.test.server;

import com.diandian.framework.netty.listener.DefaultMqttMessageEventListener;
import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.*;

/**
 * @ClassName EchoMessageEventListener
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:22
 * @Version 1.0
 **/
public class EchoMessageEventListener extends DefaultMqttMessageEventListener {

    @Override
    public void publish(WrappedChannel channel, MqttPublishMessage msg) {
        String topic = msg.variableHeader().topicName();
        ByteBuf buf = msg.content().duplicate();
        byte[] tmp = new byte[buf.readableBytes()];
        buf.readBytes(tmp);
        String content = new String(tmp);

        MqttPublishMessage sendMessage = (MqttPublishMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.AT_MOST_ONCE, false, 0),
                new MqttPublishVariableHeader(topic, 0),
                Unpooled.buffer().writeBytes(new String(content.toUpperCase()).getBytes()));
        channel.writeAndFlush(sendMessage);
    }
}
