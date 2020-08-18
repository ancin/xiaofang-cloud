package com.diandian.entity.toc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UserAuth对象", description="用户表")
public class UserAuth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "身份证正面")
    private String idcardFront;
    @ApiModelProperty(value = "身份证反面")
    private String idcardReverse;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "0-认证中 1-认证完成 2-认证失败")
    private Integer authType;
}
