package com.diandian.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SettingDTO
 * @description:
 * @author: ancin
 * @create: 2020-08-29 15:27
 * @Version 1.0
 **/
@Data
public class SettingDTO {

    private Integer id;
    @ApiModelProperty(value = "设备类型")
    private String deviceModel;

    @ApiModelProperty(value = "设备序列号")
    private String deviceNo;
    private String deviceName;

    @ApiModelProperty(value = "设置详情 JSON格式")
    private String settingContent;

    @ApiModelProperty(value = "用户名")
    private String userName;

    private Integer userId;

    @ApiModelProperty(value = "公司名")
    private String companyName;

    @ApiModelProperty(value = "创建人")
    private String creator;

    private Date gmtCreated;
    private Date   gmtModified;

    @ApiModelProperty(value = "最后修改人")
    private String modifier;

    private Page page;
}
