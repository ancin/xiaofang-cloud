package com.diandian.test.server;

import com.diandian.framework.netty.codec.JsonDecoder;
import com.diandian.framework.netty.codec.JsonEncoder;
import com.diandian.framework.netty.service.server.Server;

/**
 * @ClassName CenterMock1
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:59
 * @Version 1.0
 **/
public class CenterMock1 {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.setPort(9000);
        server.setCheckHeartbeat(false);
        server.addChannelHandler("decoder", new JsonDecoder());
        server.addChannelHandler("encoder", new JsonEncoder());
        server.addEventListener(new CenterMockMessageEventListener());
        server.bind();
    }
}
