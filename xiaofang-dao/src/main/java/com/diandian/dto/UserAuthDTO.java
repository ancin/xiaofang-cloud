package com.diandian.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAuthDTO {

    private String realName;

    private Integer authType;

    private Page page;
}
