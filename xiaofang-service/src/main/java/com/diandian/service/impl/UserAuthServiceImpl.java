package com.diandian.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diandian.dao.toc.UserAuthMapper;
import com.diandian.entity.toc.UserAuth;
import com.diandian.service.IUserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService{


}
