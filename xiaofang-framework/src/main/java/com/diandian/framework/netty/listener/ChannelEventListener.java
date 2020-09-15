package com.diandian.framework.netty.listener;

import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;

import java.util.EventListener;

/**
 * @ClassName ChannelEventListener
 * @author: ancin
 * @create: 2020-09-14 13:49
 * @Version 1.0
 **/
public interface ChannelEventListener extends EventListener {
    /**
     * 通道连接
     *
     * @param ctx
     * @param channel
     * @return
     */
    EventBehavior channelActive(ChannelHandlerContext ctx, WrappedChannel channel);

    /**
     * 通道关闭
     *
     * @param ctx
     * @param channel
     * @return
     */
    EventBehavior channelInactive(ChannelHandlerContext ctx, WrappedChannel channel);
}
