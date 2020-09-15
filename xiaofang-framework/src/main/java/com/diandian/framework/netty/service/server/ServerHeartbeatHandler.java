package com.diandian.framework.netty.service.server;

import com.diandian.framework.netty.entity.Heartbeat;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ServerHeartbeatHandler
 * @description:
 * @author: ancin
 * @create: 2020-09-14 18:10
 * @Version 1.0
 **/
@ChannelHandler.Sharable
@Slf4j
public class ServerHeartbeatHandler extends ChannelInboundHandlerAdapter {
    public ServerHeartbeatHandler() {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.WRITER_IDLE) {
            } else if (e.state() == IdleState.READER_IDLE) {
                ctx.channel().close();
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Heartbeat) {
            if (log.isDebugEnabled()) {
                log.debug("Heartbeat received.");
            }

            Server server = ServerContext.getContext().getServer();
            if(server != null) {
                server.getCountInfo().getHeartbeatNum().incrementAndGet();
            }
            return;
        }
        super.channelRead(ctx, msg);
    }
}
