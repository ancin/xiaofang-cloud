package com.diandian.web.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diandian.common.constants.WxTemplateMsgConstant;
import com.diandian.common.enums.UserAuthEnum;
import com.diandian.common.enums.UserEnum;
import com.diandian.common.utils.DateUtil;
import com.diandian.dto.UserAuthDTO;
import com.diandian.dto.WxTemplateMsgDTO;
import com.diandian.entity.toc.User;
import com.diandian.entity.toc.UserAuth;
import com.diandian.service.IUserAuthService;
import com.diandian.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: song
 *
 */
@Api(tags = "认证接口")
@RestController
@Slf4j
@RequestMapping("/v2/admin/auth")
public class UserAuthAdminController {

    @Resource
    private IUserAuthService userAuthService;

    @Resource
    private IUserService userService;


    @ApiOperation(value = "获取认证列表",notes = "获取认证列表")
    @PostMapping("/list")
    public IPage<UserAuth> list(@RequestBody UserAuthDTO userAuthDTO){
        log.info("获取认证列表{}", userAuthDTO.toString());
        log.info("List all auth.");
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "N");
        if(StringUtils.isNotEmpty(userAuthDTO.getRealName())){
            queryWrapper.eq("real_name", userAuthDTO.getRealName());
        }
        if(null != userAuthDTO.getAuthType()){
            queryWrapper.eq("auth_type", userAuthDTO.getAuthType());
        }
        queryWrapper.orderByDesc("gmt_modified");
        return userAuthService.page(userAuthDTO.getPage(),queryWrapper);
    }
    @ApiOperation(value = "获取详细",notes = "获取vip统计")
    @PostMapping("/info")
    public UserAuth info(Long id){
        log.info("获取认证详细{}", id);
        return userAuthService.getById(id);
    }

    @ApiOperation(value = "审核",notes = "审核")
    @PostMapping("/update")
    public void update(@RequestBody UserAuth userAuth){
        log.info("获取认证列表{}", userAuth.toString());
        log.info("get all update.");
        userAuthService.updateById(userAuth);
        UserAuth oldUserAuth = userAuthService.getById(userAuth.getId());
        if(1 == userAuth.getAuthType()){
            User user = new User();
            user.setId(oldUserAuth.getUserId());
            user.setFinishAuth(UserEnum.FINISH_AUTH_PASS.getValue());
            user.setMobile(oldUserAuth.getMobile());
            user.setRealName(oldUserAuth.getRealName());
            userService.updateById(user);
        }

        Integer authStatus = userAuth.getAuthType();
        if(authStatus!=null && (UserAuthEnum.AUDIT_PASS_YES.getValue()==authStatus.intValue() || UserAuthEnum.AUDIT_PASS_NO.getValue()==authStatus.intValue())){
            Long toUserId = oldUserAuth.getUserId();
            User oldUser = userService.getById(toUserId);
            String oldRealName = oldUser.getRealName();
            String userAuthDesc = UserAuthEnum.getDescByValue(authStatus.intValue());
            WxTemplateMsgDTO wxTemplateMsgDTO = new WxTemplateMsgDTO();
            wxTemplateMsgDTO.setToUser(oldUser.getWxId());
            wxTemplateMsgDTO.setTemplateId( WxTemplateMsgConstant.COMMOM_AUTH_TEMPLATE_ID);
            wxTemplateMsgDTO.setUrl("");
            wxTemplateMsgDTO.setFirst(oldRealName+"您好，您的实名认证审核"+userAuthDesc+",请及时查看");
            wxTemplateMsgDTO.setRemark("");
            Map<String,String> keyMap = new HashMap<>();
            String applyDate = DateUtil.formatDate(oldUserAuth.getGmtCreated(),"yyyy-MM-dd HH:mm:ss");
            keyMap.put("keyword1",applyDate);//发起时间
            keyMap.put("keyword2","实名认证审批");//工单类型
            String authDate =  DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
            keyMap.put("keyword3",authDate);//审批时间
            keyMap.put("keyword4",userAuthDesc);//审批结果
            wxTemplateMsgDTO.setKeyMap(keyMap);
            if(UserAuthEnum.AUDIT_PASS_NO.getValue()==authStatus.intValue()){
                wxTemplateMsgDTO.setRemark(userAuth.getRemark());
            }

        }
    }
}
