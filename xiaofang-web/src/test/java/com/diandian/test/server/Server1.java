package com.diandian.test.server;

import com.diandian.framework.netty.service.server.Server;

/**
 * https://github.com/daoshenzzg/socket-mqtt
 * @ClassName Server1
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:16
 * @Version 1.0
 **/
public class Server1 {
    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.setPort(8000);
        server.setCheckHeartbeat(false);
        server.setCenterAddr("127.0.0.1:9000,127.0.0.1:9010");
        server.addEventListener(new JsonEchoMessageEventListener());
        server.bind();
    }
}
