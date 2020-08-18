package com.diandian.common.listener;

import com.alibaba.fastjson.JSONObject;
import com.diandian.common.config.WxConfig;
import com.diandian.common.constants.WxUrlConstant;
import com.diandian.common.utils.HttpClientUtil;
import com.diandian.common.dto.OrderCreateEvent;
import com.diandian.common.dto.WxModelMsg;
import com.diandian.common.dto.WxMsgOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shengxiaohua
 * @Description: 微信订单创建消息发送监听器
 * @create 2019-11-24 14:10
 * @last modify by [shengxiaohua 2019-11-24 14:10]
 **/
@Component
@Slf4j
public class WxMsgOrderCreateListener implements ApplicationListener {

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private WxConfig wxConfig;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(!(applicationEvent instanceof OrderCreateEvent)){
            return;
        }
        OrderCreateEvent orderCreateEvent = (OrderCreateEvent) applicationEvent;
        //发送模板消息
        String templateMsg = generateTemplateMsg(orderCreateEvent.getWxModelMsg());
        String accessToken = wxConfig.getTokenFromWx();
        JSONObject jsonObject = httpClientUtil.createPostMsg(WxUrlConstant.URL_MSG_MODEL+accessToken,templateMsg);
        log.info(jsonObject.toJSONString());
    }

    private String generateTemplateMsg(WxModelMsg wxModelMsg){
        Map<String,Object> result = new HashMap<>();
        result.put("touser",wxModelMsg.getOpenId());
        result.put("template_id",wxModelMsg.getTemplateId());
        result.put("url",wxModelMsg.getToUrl());

        String fontColor = "#0000000";
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("first",new WxMsgOption(wxModelMsg.getOrderTitle(),fontColor));
        dataMap.put("keyword1",new WxMsgOption(wxModelMsg.getOrderName(),fontColor));
        dataMap.put("keyword2",new WxMsgOption(wxModelMsg.getOrderTime(),fontColor));
        dataMap.put("keyword3",new WxMsgOption(wxModelMsg.getOrderMoney(),fontColor));
        dataMap.put("remark",new WxMsgOption(wxModelMsg.getOrderRemark(),fontColor));

        result.put("data",dataMap);

        return JSONObject.toJSONString(result);
    }
}
