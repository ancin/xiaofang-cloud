package com.diandian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.diandian.entity.toc.Role;

import java.util.Set;

/**
 * @author shengxiaohua
 * @Description: 角色表 服务类
 * @create 2019-12-16 16:03
 * @last modify by [shengxiaohua 2019-12-16 16:03]
 **/
public interface IRoleService extends IService<Role> {
    Set<String> findRoles(Long userId);
}
