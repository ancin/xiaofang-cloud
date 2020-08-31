package com.diandian.entity.toc;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户设置表格
 * </p>
 *
 * @author ancin
 * @since 2020-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="TSetting对象", description="用户设置表格")
@TableName("t_setting")
public class Setting extends BaseEntity {

    private static final long serialVersionUID = 1L;

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



}
