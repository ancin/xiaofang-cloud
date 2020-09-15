package com.diandian.test.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diandian.framework.netty.entity.Request;
import com.diandian.framework.netty.entity.Response;
import com.diandian.framework.netty.listener.EventBehavior;
import com.diandian.framework.netty.listener.MessageEventListener;
import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName JsonEchoMessageEventListener
 * @description:
 * @author: ancin
 * @create: 2020-09-15 15:17
 * @Version 1.0
 **/
public class JsonEchoMessageEventListener implements MessageEventListener {
    @Override
    public EventBehavior channelRead(ChannelHandlerContext ctx, WrappedChannel channel, Object msg) {

        if (msg instanceof Request) {
            Request request = (Request) msg;
            if (request.getMessage() != null) {
                Response response = new Response();
                response.setSequence(request.getSequence());
                response.setCode(0);
                JSONObject data = JSON.parseObject(request.getMessage().toString());
                response.setResult(data.getString("message").toUpperCase());

                channel.writeAndFlush(response);
            }
        }

        return EventBehavior.CONTINUE;
    }
}
