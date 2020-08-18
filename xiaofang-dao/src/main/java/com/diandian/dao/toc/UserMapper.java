package com.diandian.dao.toc;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diandian.entity.toc.User;

/***
 * @author ancin
 */
public interface UserMapper extends BaseMapper<User> {
    User getByUserName(String userName);

    User getUserByOpenId(String openId);

    String getOpenIdById(Long userId);

    User getUserByPhoneNumber(String mobile);
}
