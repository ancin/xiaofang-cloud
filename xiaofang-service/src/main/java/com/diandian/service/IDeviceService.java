package com.diandian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.diandian.dto.DeviceDTO;
import com.diandian.entity.toc.Device;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * @author ancin
 * @since 2020-08-28
 */
public interface IDeviceService extends IService<Device> {
    IPage<Device> queryByPage(DeviceDTO deviceDTO);
}
