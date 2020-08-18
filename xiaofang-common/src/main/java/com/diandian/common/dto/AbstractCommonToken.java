package com.diandian.common.dto;

import com.diandian.common.enums.LoginTypeEnum;

/**
 * @author shengxiaohua
 * @Description: 抽象公共token
 * @create 2020-01-06 17:45
 * @last modify by [shengxiaohua 2020-01-06 17:45]
 **/
public abstract class AbstractCommonToken {
    public abstract LoginTypeEnum getLoginType();
}
