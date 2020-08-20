package com.diandian.web.controller.toc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diandian.common.bean.ResponseHelper;
import com.diandian.common.bean.ResponseModel;
import com.diandian.common.dto.JwtToken;
import com.diandian.common.enums.UserEnum;
import com.diandian.common.utils.JwtUtil;
import com.diandian.entity.toc.User;
import com.diandian.entity.toc.UserAuth;
import com.diandian.service.IUserAuthService;
import com.diandian.service.IUserService;
import com.diandian.vo.UserVO;
import com.diandian.web.common.controller.WebBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * @author songkejun
 */
@Api(tags = "认证接口")
@RestController
@Slf4j
@RequestMapping("/v2/admin/user")
public class UserAuthController extends WebBaseController {

    @Resource
    private IUserAuthService userAuthService;
    @Resource
    private IUserService userService;

    @ApiOperation(value = "认证", notes = "认证")
    @PostMapping("/save")
    public void save(@RequestBody UserAuth userAuth) {
        log.info("获取认证列表{}", userAuth.toString());
        Long userId = getUserIdByShiro();
        userAuth.setUserId(userId);
        userAuth.setAuthType(0);
        userAuth.setGmtModified(new Date());
        userAuth.setModifier(userId + "");
        if (null == userAuth.getId()) {
            userAuth.setGmtCreated(new Date());
            userAuth.setCreator(userId + "");
            userAuthService.save(userAuth);
        } else {
            userAuthService.updateById(userAuth);
        }
    }

    @ApiOperation(value = "查询认证", notes = "查询认证")
    @GetMapping("/info")
    public UserAuth info() {
        Long userId = getUserIdByShiro();
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userAuthService.getOne(queryWrapper);
    }


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserVO userVO, HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        User user = userService.getUserByPhoneNumber(userVO.getMobile());
        if (user == null) {
            result.put("errorMsg", "用户请先关注公众号,请进入公众号：敬书课堂，完成认证");
            result.put("code", 1);
            return result;
        }
        request.getSession().setAttribute("phone", userVO.getMobile());
        if (user.getFinishAuth() == null || UserEnum.FINISH_AUTH_PASS.getValue() != user.getFinishAuth().intValue()) {
            result.put("errorMsg", "用户未完成实名认证,请进入公众号：敬书课堂，完成认证");
            result.put("code", 1);
            return result;
        }

        String token = JwtUtil.sign(user.getId(), "123456");
        result.put("token", token);

        UsernamePasswordToken passwordToken = new UsernamePasswordToken(user.getLoginName(), user.getLoginPassword());
        try {
            SecurityUtils.getSubject().login(passwordToken);
        } catch (Exception e) {
            result.put("errorMsg", "用户登录失败");
            result.put("code", 1);
        }


        SecurityUtils.getSubject().getSession().setTimeout(-1000l);
        result.put("user", user);
        result.put("code", 200);
        log.info("返回:" + result);

        return result;
    }

    @ApiOperation(value = "logout", notes = "logout")
    @PostMapping("logout")
    public void logout() {
        log.info("logout");
        SecurityUtils.getSubject().logout();
    }

}
