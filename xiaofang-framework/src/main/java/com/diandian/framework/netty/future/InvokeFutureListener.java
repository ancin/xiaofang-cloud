package com.diandian.framework.netty.future;

/**
 * @ClassName InvokeFutureListener
 * @author: ancin
 * @create: 2020-09-14 14:11
 * @Version 1.0
 **/
public interface InvokeFutureListener {
    /**
     * 完成操作
     *
     * @param future
     * @throws Exception
     */
    void operationComplete(InvokeFuture future) throws Exception;
}
