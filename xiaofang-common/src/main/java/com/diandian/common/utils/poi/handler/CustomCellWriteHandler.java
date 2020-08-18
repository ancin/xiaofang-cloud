package com.diandian.common.utils.poi.handler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class CustomCellWriteHandler implements CellWriteHandler {

	@Override
	public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean isHead) {

	}

	@Override
	public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean isHead) {

	}

	@Override
	public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean isHead) {
		if (!isHead) {
			Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
			CellStyle cellStyle = workbook.createCellStyle();
			Font cellFont = workbook.createFont();
			cellFont.setFontName("宋体");
			cellFont.setFontHeightInPoints((short) 11);
			cellStyle.setFont(cellFont);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
		}
	}

}
