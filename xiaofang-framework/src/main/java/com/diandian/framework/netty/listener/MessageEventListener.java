package com.diandian.framework.netty.listener;

import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;

import java.util.EventListener;

/**
 * @ClassName MessageEventListener
 * @author: ancin
 * @create: 2020-09-14 13:53
 * @Version 1.0
 **/
public interface MessageEventListener extends EventListener {
    /**
     * 接收消息
     *
     * @param ctx
     * @param channel
     * @param msg
     * @return
     */
    EventBehavior channelRead(ChannelHandlerContext ctx, WrappedChannel channel, Object msg);
}
