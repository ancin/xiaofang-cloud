package com.diandian.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diandian.dao.toc.DeviceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diandian.dto.DeviceDTO;
import com.diandian.entity.toc.Device;
import com.diandian.service.IDeviceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * @author ancin
 * @since 2020-08-28
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    /***
     *  Query by page.
     * @param deviceDTO
     * @return
     */
    @Override
    public IPage<Device> queryByPage(DeviceDTO deviceDTO) {
        return baseMapper.queryByPage(deviceDTO.getPage(),deviceDTO);
    }
}
