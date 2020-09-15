package com.diandian.framework.netty.count;

import com.diandian.framework.netty.service.server.Server;
import com.diandian.framework.netty.service.server.ServerContext;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName CountHandler
 * @description:
 * @author: ancin
 * @create: 2020-09-14 18:13
 * @Version 1.0
 **/
@ChannelHandler.Sharable
public class CountHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server server = ServerContext.getContext().getServer();
        server.getCountInfo().setCurChannelNum(server.getChannels().size());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Server server = ServerContext.getContext().getServer();
        server.getCountInfo().setCurChannelNum(server.getChannels().size());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CountInfo countInfo = ServerContext.getContext().getServer().getCountInfo();
        countInfo.getReceiveNum().incrementAndGet();
        countInfo.setLastReceive(System.currentTimeMillis());
        super.channelRead(ctx, msg);
    }
}
