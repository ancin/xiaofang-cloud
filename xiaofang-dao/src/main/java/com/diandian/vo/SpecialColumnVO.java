package com.diandian.vo;

import com.diandian.entity.toc.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shengxiaohua
 * @Description: 专栏表VO
 * @create 2019-12-13 17:02
 * @last modify by [shengxiaohua 2019-12-13 17:02]
 **/
@Data
public class SpecialColumnVO  extends BaseEntity {
    @ApiModelProperty(name = "专栏Id")
    private Long id;

    @ApiModelProperty(name = "专栏名")
    private String columnName;

    @ApiModelProperty(name = "封面")
    private String coverIcon;

    @ApiModelProperty(name = "专栏副标题")
    private String subTitle;

    @ApiModelProperty(name = "专栏介绍")
    private String introduce;

    @ApiModelProperty(name = "专栏介绍URL")
    private String introduceUrl;

    @ApiModelProperty(name = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "佣金")
    private BigDecimal commission;

    @ApiModelProperty(name = "专栏类型（1付费专栏 2免费专栏）")
    private Integer columnType;

    @ApiModelProperty(name = "状态（up 上架 down 下架）")
    private String status;

    @ApiModelProperty(name="支付状态(1待支付 2已支付 3已退款 4已关闭)")
    private Integer payStatus;

    @ApiModelProperty(name = "语音介绍")
    private String voiceIntroduce;

    @ApiModelProperty(name = "userId")
    private Long userId;

    @ApiModelProperty(name = "是否收藏")
    private Boolean hasCollect = false;

    private String remark;
}
