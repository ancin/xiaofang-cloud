package com.diandian.dao.toc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diandian.entity.toc.Role;

import java.util.Set;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2019-12-16 16:09
 * @last modify by [shengxiaohua 2019-12-16 16:09]
 **/
public interface RoleMapper extends BaseMapper<Role> {
    Set<String> findRoles(Long userId);
}
