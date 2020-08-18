package com.diandian.common.dto;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @author shengxiaohua
 * @Description: 订单创建事件
 * @create 2019-11-24 14:00
 * @last modify by [shengxiaohua 2019-11-24 14:00]
 **/
@Data
public class OrderCreateEvent extends ApplicationEvent {

    private WxModelMsg wxModelMsg;

    public OrderCreateEvent(Object source,WxModelMsg wxModelMsg) {
        super(source);
        this.wxModelMsg = wxModelMsg;
    }
}
