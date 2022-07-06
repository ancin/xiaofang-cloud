package com.diandian.entity.toc;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ancin
 * @Description:
 * @create 2019-11-26 15:41
 * @last modify by [ 2021-11-26 15:41]
 **/
@Data
public class BaseEntity {

    @ApiModelProperty("创建人")
    private String creator;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("创建时间")
    @TableField("gmt_created")
    private Date gmtCreated = new Date();
    @ApiModelProperty("修改人")
    private String modifier;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("修改时间")
    @TableField("gmt_modified")
    private Date   gmtModified = new Date();
    @ApiModelProperty("是否删除：Y是N否")
    @TableField("is_deleted")
    private String isDeleted   = "N";
}
