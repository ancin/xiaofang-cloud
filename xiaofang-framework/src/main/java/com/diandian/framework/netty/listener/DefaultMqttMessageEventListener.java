package com.diandian.framework.netty.listener;

import com.diandian.framework.netty.service.WrappedChannel;
import com.diandian.framework.netty.service.server.Server;
import com.diandian.framework.netty.service.server.ServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DefaultMqttMessageEventListener
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:23
 * @Version 1.0
 **/
@Slf4j
public class DefaultMqttMessageEventListener implements MessageEventListener  {
    @Override
    public EventBehavior channelRead(ChannelHandlerContext ctx, WrappedChannel channel, Object msg) {
        if (msg instanceof MqttMessage) {
            MqttMessage message = (MqttMessage) msg;
            MqttMessageType messageType = message.fixedHeader().messageType();
            switch (messageType) {
                case CONNECT:
                    this.connect(channel, (MqttConnectMessage) message);
                    break;
                case PUBLISH:
                    this.publish(channel, (MqttPublishMessage) message);
                    break;
                case SUBSCRIBE:
                    this.subscribe(channel, (MqttSubscribeMessage) message);
                    break;
                case UNSUBSCRIBE:
                    this.unSubscribe(channel, (MqttUnsubscribeMessage) message);
                    break;
                case PINGREQ:
                    this.pingReq(channel, message);
                    break;
                case DISCONNECT:
                    this.disConnect(channel, message);
                    break;
                default:
                    if (log.isDebugEnabled()) {
                        log.debug("Nonsupport server message  type of '{}'.", messageType);
                    }
                    break;
            }
        }
        return EventBehavior.CONTINUE;
    }

    public void connect(WrappedChannel channel, MqttConnectMessage msg) {
        if (log.isDebugEnabled()) {
            String clientId = msg.payload().clientIdentifier();
            log.debug("MQTT CONNECT received on channel '{}', clientId is '{}'.",
                    channel.id().asShortText(), clientId);
        }

        MqttConnAckMessage okResp = (MqttConnAckMessage) MqttMessageFactory.newMessage(new MqttFixedHeader(
                        MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, true), null);
        channel.writeAndFlush(okResp);
    }

    public void pingReq(WrappedChannel channel, MqttMessage msg) {
        if (log.isDebugEnabled()) {
            log.debug("MQTT pingReq received.");
        }

        Server server = ServerContext.getContext().getServer();
        if(server != null) {
            server.getCountInfo().getHeartbeatNum().incrementAndGet();
        }

        MqttMessage pingResp = new MqttMessage(new MqttFixedHeader(MqttMessageType.PINGRESP, false,
                MqttQoS.AT_MOST_ONCE, false, 0));
        channel.writeAndFlush(pingResp);
    }

    public void disConnect(WrappedChannel channel, MqttMessage msg) {
        if (channel.isActive()) {
            channel.close();

            if (log.isDebugEnabled()) {
                log.debug("MQTT channel '{}' was closed.", channel.id().asShortText());
            }
        }
    }

    public void publish(WrappedChannel channel, MqttPublishMessage msg) {
    }

    public void subscribe(WrappedChannel channel, MqttSubscribeMessage msg) {
        MqttSubAckMessage subAckMessage = (MqttSubAckMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(msg.variableHeader().messageId()),
                new MqttSubAckPayload(0));
        channel.writeAndFlush(subAckMessage);
    }

    public void unSubscribe(WrappedChannel channel, MqttUnsubscribeMessage msg) {
        MqttUnsubAckMessage unSubAckMessage = (MqttUnsubAckMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(msg.variableHeader().messageId()), null);
        channel.writeAndFlush(unSubAckMessage);
    }

}
