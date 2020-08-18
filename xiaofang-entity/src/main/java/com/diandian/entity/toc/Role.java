package com.diandian.entity.toc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shengxiaohua
 * @Description: 用户角色表
 * @create 2019-12-16 16:04
 * @last modify by [shengxiaohua 2019-12-16 16:04]
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="角色对象", description="角色表")
public class Role extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(name = "角色名")
    private String roleName;
}
