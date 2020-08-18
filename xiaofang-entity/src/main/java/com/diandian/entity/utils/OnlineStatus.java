package com.diandian.entity.utils;

/**
 * @ClassName OnlineStatus
 * @author: ancin
 * @create: 2019-12-18 16:25
 * @Version 1.0
 **/
public enum OnlineStatus {
    /** 用户状态 */
    on_line("在线"), off_line("离线");

    private final String info;

    private OnlineStatus(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
}
