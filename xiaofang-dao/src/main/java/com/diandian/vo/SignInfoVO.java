package com.diandian.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shengxiaohua
 * @Description: 用户签到数据VO
 * @create 2019-12-29 15:09
 * @last modify by [shengxiaohua 2019-12-29 15:09]
 **/
@Data
public class SignInfoVO {
    @ApiModelProperty("连续签到天数")
    private int continueSignDays;
    @ApiModelProperty("累计学习小时数")
    private int totalStudyHours;
    @ApiModelProperty("我的奖学金")
    private BigDecimal rewardMoney;
}
