package com.diandian.test.server;

import com.diandian.framework.netty.entity.MqttRequest;
import com.diandian.framework.netty.service.SocketType;
import com.diandian.framework.netty.service.WrappedChannel;
import com.diandian.framework.netty.service.server.Server;

/**
 * @ClassName MqttWsServerTest
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:24
 * @Version 1.0
 **/
public class MqttWsServerTest {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.setPort(8000);
        server.addEventListener(new EchoMessageEventListener());
        server.setSocketType(SocketType.MQTT_WS);
        server.bind();

        //模拟推送
        String message = "this is a web socket message!";
        MqttRequest mqttRequest = new MqttRequest((message.getBytes()));
        while (true) {
            if (server.getChannels().size() > 0) {
                System.out.println("模拟推送消息");
                for (WrappedChannel channel : server.getChannels().values()) {
                    server.send(channel, "yb/notice/", mqttRequest);
                }
            }
            Thread.sleep(1000L);
        }
    }
}
