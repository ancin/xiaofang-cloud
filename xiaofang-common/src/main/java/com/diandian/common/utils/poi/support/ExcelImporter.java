package com.diandian.common.utils.poi.support;

import java.util.List;

/**
 * 导入数据接口
 * @param <T>
 */
@Deprecated
public interface ExcelImporter<T> {
	void save(List<T> data);
}
