package com.diandian.test.client;

import com.alibaba.fastjson.JSONObject;
import com.diandian.framework.netty.codec.JsonDecoder;
import com.diandian.framework.netty.codec.JsonEncoder;
import com.diandian.framework.netty.entity.Request;
import com.diandian.framework.netty.entity.Response;
import com.diandian.framework.netty.service.client.Client;
import io.netty.channel.ChannelFuture;

/**
 * @ClassName ClientTest
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:15
 * @Version 1.0
 **/
public class ClientTest {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.setCheckHeartbeat(false);
        client.setCenterAddr("127.0.0.1:9000,127.0.0.1:9010");
        client.addChannelHandler("decoder", new JsonDecoder());
        client.addChannelHandler("encoder", new JsonEncoder());
        client.connect();

        JSONObject message = new JSONObject();
        message.put("action", "echo");
        message.put("message", "hello");

        for (int i = 0; i < 5; i++) {
            Request request = new Request();
            request.setSequence(i);
            request.setMessage(message);
            client.send(request);
          // Response response = client.sendWithSync(request);
          // System.out.println("服务端返回："+response);
            Thread.sleep(5000L);
        }
    }
}
