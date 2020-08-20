package com.diandian.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diandian.dao.toc.UserMapper;
import com.diandian.entity.toc.User;
import com.diandian.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User getByUsername(String username) {
        return baseMapper.getByUserName(username);
    }

    @Override
    public int updateMobile(User user) {
        if (user == null || user.getUserId() == null){
            return 0;
        }
        return baseMapper.updateById(user);
    }

    @Override
    public User getUserByOpenId(String openId) {
        return baseMapper.getUserByOpenId(openId);
    }

    @Override
    public String getOpenIdById(Long userId) {
        return baseMapper.getOpenIdById(userId);
    }

    @Override
    public User generateUser(String openId,String userImg,String nickName, Integer subscribe) {
        User user = getUserByOpenId(openId);
        if(user==null){
            log.info("创建新用户");
            //openId绑定用户
            user = new User();
            user.setUserId(System.currentTimeMillis());//后面改成发号器获取
            user.setNickName(nickName);
            user.setWxId(openId);
            user.setHeadImg(userImg);

            baseMapper.insert(user);
        }else{
            log.info("更新用户,{},{},{}",userImg,nickName,subscribe);
            if(StringUtils.isNoneEmpty(userImg)){
                user.setHeadImg(userImg);
            }
            if(StringUtils.isNoneEmpty(nickName)){
                user.setNickName(nickName);
            }
            baseMapper.updateById(user);
        }

        log.info("生成/更新用户账号：userId：{}",user.getId());

        return user;
    }

    @Override
    public User getUserByPhoneNumber(String mobile) {
        return baseMapper.getUserByPhoneNumber(mobile);
    }
}
