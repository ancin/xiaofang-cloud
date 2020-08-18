package com.diandian.common.utils.poi.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取excel数据listener
 * @param <T>
 */
public class DataListener<T> extends AnalysisEventListener<T>{

	private List<T> dataList = new ArrayList();

	@Override
	public void invoke(T data, AnalysisContext analysisContext) {
		this.dataList.add(data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
	}

	public List<T> getDataList() {
		return this.dataList;
	}
}
