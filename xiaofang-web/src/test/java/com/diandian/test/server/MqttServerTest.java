package com.diandian.test.server;

import com.alibaba.fastjson.JSONObject;
import com.diandian.framework.netty.entity.MqttRequest;
import com.diandian.framework.netty.service.SocketType;
import com.diandian.framework.netty.service.WrappedChannel;
import com.diandian.framework.netty.service.server.Server;

/**
 * @ClassName MqttServerTest
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:21
 * @Version 1.0
 **/
public class MqttServerTest {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.setPort(8000);
        server.setOpenCount(true);
        server.setCheckHeartbeat(true);
        server.setOpenStatus(true);
        server.setOpenExecutor(true);
        server.addEventListener(new EchoMessageEventListener());
        server.setSocketType(SocketType.MQTT);
        server.bind();

        //模拟推送
        JSONObject message = new JSONObject();
        message.put("action", "echo");
        message.put("message", "this is yb push message!");

        MqttRequest mqttRequest = new MqttRequest((message.toString().getBytes()));
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
