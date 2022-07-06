package com.diandian.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName DeviceDTO
 * @description:
 * @author: ancin
 * @create: 2020-08-28 13:59
 * @Version 1.0
 **/
@Data
public class DeviceDTO {

    private Integer id;
    @ApiModelProperty(value = "设备序列号")
    private String deviceNo;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;
    @ApiModelProperty(value = "设备序名称")
    private String deviceName;

    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    @ApiModelProperty(value = "设备状态0离线1在线")
    private String deviceStatus;

    @ApiModelProperty(value = "归属用户")
    private Long userId;

    @ApiModelProperty(value = "地区")
    private String region;

    @ApiModelProperty(value = "分类")
    private String clazz;

    @ApiModelProperty(value = "行业分类")
    private String industry;

    @ApiModelProperty(value = "属于哪个组织")
    private String companyName;

    @ApiModelProperty(value = "创建人")
    private String creator;

    private Date gmtCreated;
    private Date   gmtModified;

    @ApiModelProperty(value = "最后修改人")
    private String modifier;

    private Page page;
}
