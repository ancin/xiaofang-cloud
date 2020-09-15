package com.diandian.framework.netty.exception;

/**
 * @ClassName SocketRuntimeException
 * @description:
 * @author: ancin
 * @create: 2020-09-14 14:17
 * @Version 1.0
 **/
public class SocketRuntimeException extends RuntimeException {
    public SocketRuntimeException() {
        super();
    }

    public SocketRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocketRuntimeException(String message) {
        super(message);
    }

    public SocketRuntimeException(Throwable cause) {
        super(cause);
    }

}
