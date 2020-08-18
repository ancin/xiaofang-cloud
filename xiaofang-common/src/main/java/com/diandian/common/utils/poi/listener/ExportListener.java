package com.diandian.common.utils.poi.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.diandian.common.utils.poi.support.ExcelImporter;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储处理Excel数据的listener
 * ConverterDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 *
 * @param <T>
 */
public class ExportListener<T> extends AnalysisEventListener<T> {

	//每隔3000条存储数据库，然后清理list ，方便内存回收
	private static final int batchCount = 3000;
	private List<T> list = new ArrayList();
	private final ExcelImporter<T> importer;

	/**
	 * 这个每一条数据解析都会来调用
	 *
	 * @param data            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
	 * @param analysisContext
	 */
	@Override
	public void invoke(T data, AnalysisContext analysisContext) {
		this.list.add(data);
		// 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
		if (this.list.size() >= this.batchCount) {
			this.importer.save(this.list);
			// 存储完成清理 list
			this.list.clear();
		}
	}

	/**
	 * 所有数据解析完成了 都会来调用
	 *
	 * @param analysisContext
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		this.importer.save(this.list);
		this.list.clear();
	}

	public ExportListener(ExcelImporter<T> importer) {
		this.importer = importer;
	}

}
