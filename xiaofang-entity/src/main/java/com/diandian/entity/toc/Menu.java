package com.diandian.entity.toc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author ancin
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Menu对象", description="菜单表")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片")
    private String icon;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "name")
    private String name;


    @ApiModelProperty(value = "父菜单id")
    private Long parentId;

    private int hidden;

}
