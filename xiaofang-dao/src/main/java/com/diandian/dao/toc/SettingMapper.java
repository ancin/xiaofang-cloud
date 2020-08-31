package com.diandian.dao.toc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diandian.dto.SettingDTO;
import com.diandian.entity.toc.Setting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户设置表格 Mapper 接口
 * </p>
 *
 * @author ancin
 * @since 2020-08-29
 */
public interface SettingMapper extends BaseMapper<Setting> {
    IPage<Setting> queryByPage(Page page, @Param("settingDTO") SettingDTO settingDTO);
}
