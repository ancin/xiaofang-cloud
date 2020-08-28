package com.diandian.web.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diandian.dto.DeviceDTO;
import com.diandian.entity.toc.Device;
import com.diandian.service.IDeviceService;
import com.diandian.web.common.controller.AdminBaseController;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.diandian.common.config.BaseController;

import java.util.List;


/**
 * <p>
 * 设备表 前端控制器
 * </p>
 *
 * @author ancin
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/v2/admin/device/")
@Slf4j
public class DeviceController extends BaseController {

    @Autowired
    private IDeviceService deviceService;

    @ApiOperation(value = "获取认证列表",notes = "获取认证列表")

    @PostMapping("/list")
    public IPage<Device> list( @RequestBody DeviceDTO deviceDTO){
        log.info("设备查询:"+deviceDTO);
        long userId = AdminBaseController.getUserIdByShiro() == null ? getUserIdByShiro() : AdminBaseController.getUserIdByShiro();
        deviceDTO.setUserId(userId);
        IPage<Device> deviceIPage = deviceService.queryByPage(deviceDTO);

    return deviceIPage;
    }
}
