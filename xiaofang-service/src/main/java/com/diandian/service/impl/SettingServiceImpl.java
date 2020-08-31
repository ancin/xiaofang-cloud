package com.diandian.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diandian.dao.toc.SettingMapper;
import com.diandian.dto.SettingDTO;
import com.diandian.entity.toc.Setting;
import com.diandian.service.ISettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户设置表格 服务实现类
 * </p>
 *
 * @author ancin
 * @since 2020-08-29
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {

    @Override
    public IPage<Setting> queryByPage(SettingDTO settingDTO) {
        return baseMapper.queryByPage(settingDTO.getPage(),settingDTO);
    }
}
