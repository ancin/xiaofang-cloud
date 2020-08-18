package com.diandian.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shengxiaohua
 * @Description: 返回结果
 * @create 2019-11-24 15:02
 * @last modify by [shengxiaohua 2019-11-24 15:02]
 **/
public class ResultBase<T> implements Serializable {
    private boolean isSuccess = false;
    private String errorMsg = "";
    private String errorCode = "200";
    private T value;
    private Map<String,Object> other;

    public ResultBase() {
    }

    public ResultBase(T value) {
        this.isSuccess = true;
        this.value = value;
    }

    public ResultBase(String errorMsg, String errorCode) {
        this.isSuccess = false;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public ResultBase(boolean success, String errorMsg, String errorCode) {
        this.isSuccess = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Map<String, Object> getOther() {
        return other;
    }

    public void setOther(Map<String, Object> other) {
        this.other = other;
    }
}
