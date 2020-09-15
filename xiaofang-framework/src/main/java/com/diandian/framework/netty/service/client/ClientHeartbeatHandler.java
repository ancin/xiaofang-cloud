package com.diandian.framework.netty.service.client;

import com.diandian.framework.netty.entity.Heartbeat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ClientHeartbeatHandler
 * @description:
 * @author: ancin
 * @create: 2020-09-14 18:22
 * @Version 1.0
 **/
@Slf4j
@ChannelHandler.Sharable
public class ClientHeartbeatHandler extends ChannelInboundHandlerAdapter {
    public ClientHeartbeatHandler() {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.WRITER_IDLE) {
                Channel channel = ctx.channel();
                if (channel != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("WRITER_IDLE, send Heartbeat...");
                    }
                    channel.writeAndFlush(Heartbeat.getSingleton());
                }
            } else if (e.state() == IdleState.READER_IDLE) {
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
