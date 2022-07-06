package com.diandian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diandian.dto.DeviceDTO;
import com.diandian.dto.SettingDTO;
import com.diandian.entity.toc.Setting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户设置表格 服务类
 * </p>
 *
 * @author ancin
 * @since 2020-08-29
 */
public interface ISettingService extends IService<Setting> {
    /***
     * Query by page.
     * @param settingDTO
     * @return
     */
    IPage<Setting> queryByPage(SettingDTO settingDTO);
}
