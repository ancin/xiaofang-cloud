package com.diandian.framework.netty.listener;

/**
 * @ClassName EventBehavior
 * @author: ancin
 * @create: 2020-09-14 13:50
 * @Version 1.0
 **/
public enum EventBehavior {
    /**
     * 继续消息传递
     */
    CONTINUE,
    /**
     * 停止消息传递
     */
    BREAK;
}
