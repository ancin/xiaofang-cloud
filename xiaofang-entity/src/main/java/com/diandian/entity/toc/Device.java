package com.diandian.entity.toc;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备表
 * </p>
 *
 * @author ancin
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Device对象", description="设备表")
@TableName("t_device")
public class Device extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备序列号")
    private String deviceNo;

    @ApiModelProperty(value = "设备序名称")
    private String deviceName;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

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

}
