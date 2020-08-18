package com.diandian.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author shengxiaohua
 * @Description: 模板消息DTO
 * @create 2020-04-19 9:19
 * @last modify by [shengxiaohua 2020-04-19 9:19]
 **/
@Data
public class WxTemplateMsgDTO {
    private String toUser;

    private String templateId;

    private String url;

    private String first;

    private String remark;

    private Map<String, String> keyMap;
}
