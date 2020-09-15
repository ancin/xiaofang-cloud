package com.diandian.framework.netty.listener;

import com.diandian.framework.netty.entity.Request;
import com.diandian.framework.netty.entity.Response;
import com.diandian.framework.netty.future.InvokeFuture;
import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DefaultMessageEventListener
 * @description:
 * @author: ancin
 * @create: 2020-09-14 13:52
 * @Version 1.0
 **/
@Slf4j
public class DefaultMessageEventListener implements MessageEventListener{

    @Override
    public EventBehavior channelRead(ChannelHandlerContext ctx, WrappedChannel channel, Object msg) {
        if (log.isDebugEnabled()) {
            log.debug("Message received on channel '{}'.", channel.id().asShortText());
        }

        if (msg != null) {
            if (msg instanceof Request) {
                Request request = (Request) msg;
                if (request.getMessage() != null) {
                    // 处理请求消息
                    processRequest(ctx, channel, request);
                }
            } else if (msg instanceof Response) {
                Response response = (Response) msg;
                // 处理反馈消息
                processResponse(ctx, response, channel);
            }
        }
        return EventBehavior.CONTINUE;
    }

    private void processRequest(ChannelHandlerContext ctx, WrappedChannel channel, Request request) {
    }

    private void processResponse(ChannelHandlerContext ctx, Response response, WrappedChannel channel) {
        // Future方式
        InvokeFuture future = channel.getFutures().remove(response.getSequence());
        if (future != null) {
            if (response.getCause() != null) {
                future.setCause(response.getCause());
            } else {
                future.setResult(response);
            }
        }
    }

}
