package com.diandian.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author shengxiaohua
 * @Description: 签到VO
 * @create 2019-12-29 14:13
 * @last modify by [shengxiaohua 2019-12-29 14:13]
 **/
@Data
public class SignVO {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date signDate;
}
