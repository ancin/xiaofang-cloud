package com.diandian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diandian.dao.toc.RoleMapper;
import com.diandian.entity.toc.Role;
import com.diandian.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author shengxiaohua
 * @Description: 角色表服务实现类
 * @create 2019-12-16 16:06
 * @last modify by [shengxiaohua 2019-12-16 16:06]
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /***
     * Find roles by user id.
     * @param userId
     * @return
     */
    @Override
    public Set<String> findRoles(Long userId) {
        return baseMapper.findRoles(userId);
    }
}
