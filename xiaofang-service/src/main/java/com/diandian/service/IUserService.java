package com.diandian.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.diandian.entity.toc.User;
import org.springframework.transaction.annotation.Transactional;

/***
 * @author ancin
 */
public interface IUserService extends IService<User> {
    /***
     *
     * @param username
     * @return
     */
    User getByUsername(String username);

    /***
     *
     * @param user
     * @return
     */
    int updateMobile(User user);

    /**
     * 根据openId查询用户
     * @param openId
     * @return
     */
    User getUserByOpenId(String openId);

    /***
     * get by ID
     * @param userId
     * @return
     */
    String getOpenIdById(Long userId);

    /***
     * Generate user.
     * @param openId
     * @param userImg
     * @param nickName
     * @param subscribe
     * @return
     */
    @Transactional
    User generateUser(String openId,String userImg,String nickName, Integer subscribe);

    User getUserByPhoneNumber(String mobile);
}
