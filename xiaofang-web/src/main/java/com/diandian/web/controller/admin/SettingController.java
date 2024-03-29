package com.diandian.web.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diandian.common.config.BaseController;
import com.diandian.dto.SettingDTO;
import com.diandian.entity.toc.Setting;
import com.diandian.service.ISettingService;
import com.diandian.web.common.controller.AdminBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户设置表格 前端控制器
 * </p>
 *
 * @author ancin
 * @since 2020-08-29
 */
@RestController
@RequestMapping("v2/admin/setting/")
@Slf4j
public class SettingController extends BaseController {

    @Autowired
    private ISettingService settingService;

    /***
     * <p>list setting</p>
     * @param settingDTO
     * @return
     */
    @PostMapping("/list")
    public IPage<Setting> list(@RequestBody SettingDTO settingDTO){
        log.info("设备setting:"+settingDTO);
        long userId = AdminBaseController.getUserIdByShiro() == null ? getUserIdByShiro() : AdminBaseController.getUserIdByShiro();
        settingDTO.setUserId(Integer.valueOf(userId+""));
        IPage<Setting> settingIPage = settingService.queryByPage(settingDTO);
        log.info("setting return.");
        return settingIPage;
    }
}
