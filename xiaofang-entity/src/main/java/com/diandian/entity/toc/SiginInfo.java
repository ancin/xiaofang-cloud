package com.diandian.entity.toc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @ClassName SiginIn 签到bean
 * @description:
 * @author: ancin
 * @create: 2019-12-31 16:32
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="签到对象", description="签到表")
public class SiginInfo extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "奖励金额")
    private BigDecimal reward;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty("签到日期")
    private String signDate;


}
