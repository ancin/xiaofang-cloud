package com.diandian.framework.netty.service.server;

import com.diandian.framework.netty.service.EventDispatcher;
import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ClassName ServerDispatchHandler
 * @description:
 * @author: ancin
 * @create: 2020-09-14 18:08
 * @Version 1.0
 **/
@Slf4j
public class ServerDispatchHandler extends ChannelInboundHandlerAdapter {
    protected EventDispatcher eventDispatcher;

    public ServerDispatchHandler(EventDispatcher eventDispatcher) {
        if (eventDispatcher == null) {
            throw new IllegalArgumentException("eventDispatcher cannot be null.");
        }

        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String channelId = ctx.channel().id().asShortText();
        if (log.isDebugEnabled()) {
            log.debug("Message received from channel '{} : '{}'.", channelId, msg);
        }
        WrappedChannel channel = ((Server) eventDispatcher.getService()).getChannel(channelId);
        eventDispatcher.dispatchMessageEvent(ctx, channel, msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        WrappedChannel channel = new WrappedChannel(ctx.channel());
        String channelId = channel.id().asShortText();
        if (log.isDebugEnabled()) {
            log.debug("Connected on channel '{}'.", channelId);
        }
        ((Server) eventDispatcher.getService()).getChannels().put(channelId, channel);
        eventDispatcher.dispatchChannelEvent(ctx, channel);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        closeChannel(ctx);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String channelId = ctx.channel().id().asShortText();
        WrappedChannel channel = ((Server) eventDispatcher.getService()).getChannel(channelId);
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
        String channelId = ctx.channel().id().asShortText();
        WrappedChannel channel = ((Server) eventDispatcher.getService()).getChannels().remove(channelId);
        if (channel != null) {
            if (log.isDebugEnabled()) {
                log.debug("Channel '{}' was closed.", channelId);
            }

            eventDispatcher.dispatchChannelEvent(ctx, channel);
        }
        ctx.close();
    }
}
