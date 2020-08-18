package com.diandian.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diandian.entity.toc.BaseEntity;
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
public class MenuDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片")
    private String icon;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "父菜单id")
    private Long parentId;

    @ApiModelProperty(value = "创建人")
    private String creator;



    @ApiModelProperty(value = "最后修改人")
    private String modifier;

    private Page page;


}
