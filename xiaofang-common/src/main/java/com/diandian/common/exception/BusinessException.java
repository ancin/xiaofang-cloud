package com.diandian.common.exception;

import java.text.MessageFormat;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2019-11-24 15:08
 * @last modify by [shengxiaohua 2019-11-24 15:08]
 **/
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private String code = "500";

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BusinessException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BusinessException(String msg, String code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]",this.msg,this.code);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

