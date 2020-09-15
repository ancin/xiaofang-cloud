package com.diandian.framework.netty.service.server;

import com.alibaba.fastjson.JSONObject;
import com.diandian.framework.netty.entity.Request;
import com.diandian.framework.netty.service.client.Client;
import com.diandian.framework.netty.util.AddressUtil;
import com.diandian.framework.netty.util.Sequence;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName ServerStateReportJob
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:02
 * @Version 1.0
 **/
@Slf4j
public class ServerStateReportJob implements Runnable {
    private static final long DEFAULT_PERIOD_IN_MILLIS = 5000L;

    private long periodInMillis = DEFAULT_PERIOD_IN_MILLIS;


    private Server server;
    private Client client;

    public ServerStateReportJob(Server server, Client client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (server.hasRegisteredToCenter()) {
                    if (!channelIsValid(client)) {
                        log.warn("Server '{}' currently has no valid channel to register center '{}' to report " + "state, just try later.",
                                server.getServiceName(), client.getCurServer());
                    } else {
                        // 向center汇报当前server状态。
                        Request request = new Request();
                        request.setSequence(Sequence.getInstance().addAndGet("CENTER_CLIENT"));

                        JSONObject json = new JSONObject();
                        json.put("action", "updateConnects");
                        json.put("service", server.getServiceName());
                        json.put("ip", StringUtils.isEmpty(server.getIp()) ? AddressUtil.getLocalIp() : server.getIp());
                        json.put("port", server.getPort());
                        json.put("connects", server.getChannels().size());
                        request.setMessage(json.toString());

                        client.send(request);

                        log.info("Server '{}' reported state to register center '{}' using sequence '{}'.",
                                new Object[] { server.getServiceName(), client.getCurServer(), request.getSequence() });
                    }
                } else {
                    log.warn("Server '{}' has not registered to center, no need to report server state now, just " + "try later.", server.getServiceName());
                }
            } catch (Throwable ex) {
                log.error("Server '{}' failed to report server state to register center.", server.getServiceName(), ex);
            }

            try {
                Thread.sleep(periodInMillis);
            } catch (InterruptedException ex) {
                log.warn("Server state report job received InterruptedException when sleeping.");
            }
        }
    }

    private boolean channelIsValid(Client client) {
        if (client == null) {
            return false;
        }

        if (client.getChannel() == null || !client.getChannel().isActive()) {
            return false;
        }

        return true;
    }
}
