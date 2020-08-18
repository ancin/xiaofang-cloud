package com.diandian.web.controller.toc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diandian.entity.toc.UserAuth;
import com.diandian.service.IUserAuthService;
import com.diandian.web.common.controller.WebBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/***
 * @author songkejun
 */
@Api(tags = "认证接口")
@RestController
@Slf4j
@RequestMapping("/v2/auth")
public class UserAuthController extends WebBaseController {

    @Resource
    private IUserAuthService userAuthService;

    @ApiOperation(value = "认证",notes = "认证")
    @PostMapping("/save")
    public void save(@RequestBody UserAuth userAuth){
        log.info("获取认证列表{}", userAuth.toString());
        Long userId = getUserIdByShiro();
        userAuth.setUserId(userId);
        userAuth.setAuthType(0);
        userAuth.setGmtModified(new Date());
        userAuth.setModifier(userId+"");
        if(null == userAuth.getId()){
            userAuth.setGmtCreated(new Date());
            userAuth.setCreator(userId+"");
            userAuthService.save(userAuth);
        }else{
            userAuthService.updateById(userAuth);
        }
    }

    @ApiOperation(value = "查询认证",notes = "查询认证")
    @GetMapping("/info")
    public UserAuth info(){
        Long userId = getUserIdByShiro();
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userAuthService.getOne(queryWrapper);
    }
}
