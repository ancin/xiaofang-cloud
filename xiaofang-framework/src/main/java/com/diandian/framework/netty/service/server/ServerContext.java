package com.diandian.framework.netty.service.server;

/**
 * @ClassName ServerContext
 * @description:
 * @author: ancin
 * @create: 2020-09-14 18:08
 * @Version 1.0
 **/
public class ServerContext {
    private ServerContext() {
    }

    private static ServerContext instance = new ServerContext();

    public static ServerContext getContext() {
        return instance;
    }

    private Server server;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
