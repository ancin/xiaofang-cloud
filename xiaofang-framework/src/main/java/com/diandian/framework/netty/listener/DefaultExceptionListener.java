package com.diandian.framework.netty.listener;

import com.diandian.framework.netty.service.WrappedChannel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DefaultExceptionListener
 * @description:
 * @author: ancin
 * @create: 2020-09-14 13:51
 * @Version 1.0
 **/
@Slf4j
public class DefaultExceptionListener implements ExceptionEventListener {
    @Override
    public EventBehavior exceptionCaught(ChannelHandlerContext ctx, WrappedChannel channel, Throwable cause) {
        if (cause != null && channel.remoteAddress() != null) {
            log.warn("Exception caught on channel {}, caused by: '{}'.", channel.id().asShortText(), cause);
        }

        return EventBehavior.CONTINUE;
    }
}
