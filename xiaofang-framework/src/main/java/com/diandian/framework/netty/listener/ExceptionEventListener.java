package com.diandian.framework.netty.listener;

import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;

import java.util.EventListener;

/**
 * @ClassName ExceptionEventListener
 * @author: ancin
 * @create: 2020-09-14 13:57
 * @Version 1.0
 **/
public interface ExceptionEventListener extends EventListener {
    /**
     * 异常捕获
     *
     * @param ctx
     * @param channel
     * @param cause
     * @return
     */
    EventBehavior exceptionCaught(ChannelHandlerContext ctx, WrappedChannel channel, Throwable cause);
}
