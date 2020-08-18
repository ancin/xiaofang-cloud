package com.diandian.entity.toc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shengxiaohua
 * @Description: 第三方用户登录表
 * @create 2020-02-01 20:59
 * @last modify by [shengxiaohua 2020-02-01 20:59]
 **/
@Data
@ApiModel(value="第三方用户对象", description="第三方用户登录表")
@TableName("user_token")
public class UserToken extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(name = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(name = "第三方应用")
    private String platform;

    @ApiModelProperty(name = "第三方唯一ID")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(name = "AccessToken")
    @TableField("access_token")
    private String accessToken;

    @ApiModelProperty(name = "RefreshToken")
    @TableField("refresh_token")
    private String refreshToken;

    @ApiModelProperty(name = "有效期")
    @TableField("expires_in")
    private Long expiresIn;
}
