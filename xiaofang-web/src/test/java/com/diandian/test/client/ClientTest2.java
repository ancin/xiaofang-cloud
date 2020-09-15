package com.diandian.test.client;

import com.alibaba.fastjson.JSONObject;
import com.diandian.framework.netty.codec.JsonDecoder;
import com.diandian.framework.netty.codec.JsonEncoder;
import com.diandian.framework.netty.entity.Request;
import com.diandian.framework.netty.entity.Response;
import com.diandian.framework.netty.service.client.Client;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketTimeoutException;

/**
 * @ClassName ClientTest2
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:20
 * @Version 1.0
 **/
@Slf4j
public class ClientTest2 {
    public static void main(String[] args) throws InterruptedException, SocketTimeoutException {

        Client client = new Client();
        client.setIp("127.0.0.1");
        client.setPort(8000);
        client.setConnectTimeout(10000);
        client.addChannelHandler("decoder", new JsonDecoder());
        client.addChannelHandler("encoder", new JsonEncoder());
        client.connect();

        for (int i = 0; i < 10; i++) {
            JSONObject message = new JSONObject();
            message.put("action", "echo");
            message.put("message", "client send hello world!");

            Request request = new Request();
            request.setSequence(i);
            request.setMessage(message);
            Response response = (Response) client.sendWithSync(request, 3000);
            Thread.sleep(1000L);
            System.out.println("成功接收到同步的返回: '{}'."+ response);
        }

        client.shutdown();
    }
}
