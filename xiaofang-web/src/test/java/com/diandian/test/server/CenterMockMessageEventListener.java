package com.diandian.test.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.diandian.framework.netty.entity.Request;
import com.diandian.framework.netty.entity.Response;
import com.diandian.framework.netty.listener.EventBehavior;
import com.diandian.framework.netty.listener.MessageEventListener;
import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName CenterMockMessageEventListener
 * @description:
 * @author: ancin
 * @create: 2020-09-15 16:00
 * @Version 1.0
 **/
public class CenterMockMessageEventListener implements MessageEventListener {
    @Override
    public EventBehavior channelRead(ChannelHandlerContext ctx, WrappedChannel channel, Object msg) {
        if (msg instanceof Request) {
            Request request = (Request) msg;


            System.out.println("Message received: '{}'." + request.toString());


            Response response = new Response();
            response.setSequence(request.getSequence());
            response.setCode(0);

            if (request.getMessage() != null) {
                JSONObject jsonObject = JSON.parseObject(request.getMessage().toString());
                String action = jsonObject.getString("action");
                if (action.equalsIgnoreCase("getServerInfo")) {
                    JSONObject json = new JSONObject();
                    JSONArray ret = new JSONArray();
                    JSONObject row1 = new JSONObject();
                    row1.put("ip", "127.0.0.1");
                    row1.put("port", 8000);
                    JSONObject row2 = new JSONObject();
                    row2.put("ip", "127.0.0.1");
                    row2.put("port", 8010);

                    ret.add(row1);
                    ret.add(row2);
                    json.put("server_list", ret);
                    response.setResult(json.toString());
                } else if (action.equalsIgnoreCase("updateConnects")) {
                } else if (action.equalsIgnoreCase("register")) {
                }
            }
            channel.writeAndFlush(response);
        }
        return EventBehavior.CONTINUE;
    }
}
