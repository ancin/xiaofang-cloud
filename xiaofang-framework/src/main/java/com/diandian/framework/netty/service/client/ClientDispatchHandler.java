package com.diandian.framework.netty.service.client;

import com.diandian.framework.netty.service.EventDispatcher;
import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ClassName ClientDispatchHandler
 * @description:
 * @author: ancin
 * @create: 2020-09-14 18:21
 * @Version 1.0
 **/
@Slf4j
public class ClientDispatchHandler extends ChannelInboundHandlerAdapter {
    private EventDispatcher eventDispatcher;

    public ClientDispatchHandler(EventDispatcher eventDispatcher) {
        if (eventDispatcher == null) {
            throw new IllegalArgumentException("eventDispatcher");
        }

        this.eventDispatcher = eventDispatcher;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Message received from channel '{}' : '{}'.", ctx.channel().id().asShortText(), msg.toString());
        }

        WrappedChannel channel = ((BaseClient) eventDispatcher.getService()).getChannel();
        eventDispatcher.dispatchMessageEvent(ctx, channel, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Connected on channel '{}'.", ctx.channel().id().asShortText());
        }

        WrappedChannel channel = ((BaseClient) eventDispatcher.getService()).getChannel();
        if (channel == null) {
            channel = new WrappedChannel(ctx.channel());
            ((BaseClient) eventDispatcher.getService()).setChannel(channel);
        }
        eventDispatcher.dispatchChannelEvent(ctx, channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        closeChannel(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        WrappedChannel channel = ((BaseClient) eventDispatcher.getService()).getChannel();
        if (channel != null) {
            eventDispatcher.dispatchExceptionCaught(ctx, channel, cause);
        }
        // 处理IOException，主动关闭channel
        if (cause instanceof IOException) {
            ctx.close();
            closeChannel(ctx);
        }
    }

    private void closeChannel(ChannelHandlerContext ctx) {
        WrappedChannel channel = ((BaseClient) eventDispatcher.getService()).getChannel();
        if (channel != null) {
            ((BaseClient) eventDispatcher.getService()).setChannel(null);
            if (log.isDebugEnabled()) {
                log.debug("Channel '{}' was closed.", channel.id().asShortText());
            }

            eventDispatcher.dispatchChannelEvent(ctx, channel);
        }
    }
}
