package com.diandian.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shengxiaohua
 * @Description: 提现审核入参
 * @create 2020-03-14 20:57
 * @last modify by [shengxiaohua 2020-03-14 20:57]
 **/
@Data
public class WithDrawAuthDTO {
    @ApiModelProperty("提现记录id")
    private Long id;
    @ApiModelProperty("状态：1：待审核 2：提现成功 3：提现失败 4：审核成功 5：审核失败")
    private Integer status;
    @ApiModelProperty("原因")
    private String reason;
}
