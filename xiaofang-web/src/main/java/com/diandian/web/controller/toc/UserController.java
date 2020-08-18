package com.diandian.web.controller.toc;

import com.diandian.common.config.JsonResult;
import com.diandian.common.dto.ResultBase;
import com.diandian.common.dto.WeChatToken;
import com.diandian.service.IUserService;
import com.diandian.vo.UserVO;
import com.diandian.web.common.controller.WebBaseController;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class  UserController extends WebBaseController {
    @Autowired
    private IUserService userService;

    @Value("${diandian.env}")
    private String env;

    @ApiOperation(value = "用户信息接口",notes = "用户信息接口")
    @GetMapping("get")
    public ResultBase<UserVO> getUser(String enviroment){
        log.info("获取用户信息接口开始");
        if("test".equals(env) || "test".equals(enviroment)){
            //开发环境使用默认测试账号
            SecurityUtils.getSubject().login(new WeChatToken("oq0iR0gr83rWeJTpIowatrRoOHjA"));
        }
        Subject subject = SecurityUtils.getSubject();
        Object userObject = subject.getPrincipal();
        UserVO userVO = (UserVO) userObject;
        log.info("获取用户信息接口结束，{}",userVO.toString());
        return new ResultBase<>(userVO);
    }

    @PostMapping("/getByName")
    public JsonResult get(String userName) {
        System.out.print(getUserIdByShiro());
        return JsonResult.ok("成功");
    }
}