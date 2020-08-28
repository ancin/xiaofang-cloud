package com.diandian.dao.toc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diandian.dto.DeviceDTO;
import com.diandian.entity.toc.Device;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 设备表 Mapper 接口
 * </p>
 *
 * @author ancin
 * @since 2020-08-28
 */
public interface DeviceMapper extends BaseMapper<Device> {
    /***
     * Query by page
     * @param page
     * @param deviceDTO
     * @return Device list.
     */
    IPage<Device> queryByPage(Page page, @Param("deviceDTO") DeviceDTO deviceDTO);
}
