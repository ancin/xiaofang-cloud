package com.diandian.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shengxiaohua
 * @Description: 用户前端展示VO
 * @create 2019-12-16 17:26
 * @last modify by [shengxiaohua 2019-12-16 17:26]
 **/
@Data
public class UserVO {
    private Long id;

    private Long userId;

    @ApiModelProperty(name = "昵称")
    private String nickName;

    @ApiModelProperty(name = "登录名称（英文）")
    private String loginName;

    @ApiModelProperty(name = "用户头像")
    private String headImg;

    @ApiModelProperty(name = "手机号")
    private String mobile;

    @ApiModelProperty(name = "真实姓名")
    private String realName;

    @ApiModelProperty(name="是否完成实名认证（1是，0否）")
    private Integer finishAuth;

    private String role;
    private Integer subscribe;
}
