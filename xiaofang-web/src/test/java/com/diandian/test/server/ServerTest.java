package com.diandian.test.server;

import com.alibaba.fastjson.JSONObject;
import com.diandian.framework.netty.codec.JsonDecoder;
import com.diandian.framework.netty.codec.JsonEncoder;
import com.diandian.framework.netty.entity.Request;
import com.diandian.framework.netty.service.WrappedChannel;
import com.diandian.framework.netty.service.server.Server;
import lombok.extern.slf4j.Slf4j;

/**
 * https://github.com/daoshenzzg/socket-mqtt
 * @ClassName ServerTest
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:18
 * @Version 1.0
 **/
@Slf4j
public class ServerTest {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.setPort(8000);
        server.addEventListener(new JsonEchoMessageEventListener());
        server.addChannelHandler("decoder", new JsonDecoder());
        server.addChannelHandler("encoder", new JsonEncoder());
        server.bind();

        //模拟推送
        JSONObject message = new JSONObject();
        message.put("action", "echo");
        message.put("message", "server response -this is a normal socket message!");

        Request request = new Request();
        request.setSequence(0);
        request.setMessage(message);
        while (true) {
            if (server.getChannels().size() > 0) {
                System.out.println("服务器推送消息："+request);
                for (WrappedChannel channel : server.getChannels().values()) {
                    channel.send(request);
                    Thread.sleep(2000L);
                }
            }
        }
    }
}
