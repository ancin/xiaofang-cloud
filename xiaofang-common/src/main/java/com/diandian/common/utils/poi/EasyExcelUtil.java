package com.diandian.common.utils.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.diandian.common.utils.poi.listener.DataListener;
import com.diandian.common.utils.poi.listener.ImportListener;
import com.diandian.common.utils.poi.support.ExcelImporter;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * EasyExcel工具类
 */
public class EasyExcelUtil {

	/**
	 * 读取excel数据（无表头）
	 * @param excel
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> read(MultipartFile excel, Class<T> clazz) {
		DataListener<T> dataListener = new DataListener();
		ExcelReaderBuilder builder = getReaderBuilder(excel, dataListener, clazz);
		if (builder == null) {
			return null;
		} else {
			builder.doReadAll();
			return dataListener.getDataList();
		}
	}

	/**
	 * 读取excel数据（设置sheetNo为0）
	 * @param excel
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> readExcel(MultipartFile excel, Class<T> clazz) {
		return read(excel, 0, 1, clazz);
	}

	/**
	 * 根据sheet读取excel数据（默认表头为1行）
	 * @param excel
	 * @param sheetNo
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> read(MultipartFile excel, int sheetNo, Class<T> clazz) {
		return read(excel, sheetNo, 1, clazz);
	}

	/**
	 * 根据sheet、表头行数读取excel数据
	 * @param excel
	 * @param sheetNo
	 * @param headRowNumber
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> read(MultipartFile excel, int sheetNo, int headRowNumber, Class<T> clazz) {
		DataListener<T> dataListener = new DataListener();
		ExcelReaderBuilder builder = getReaderBuilder(excel, dataListener, clazz);
		if (builder == null) {
			return null;
		} else {
			builder.sheet(sheetNo).headRowNumber(headRowNumber).doRead();
			return dataListener.getDataList();
		}
	}

	/**
	 * 读取excel并调用importer保存
	 * @param excel
	 * @param importer
	 * @param clazz
	 * @param <T>
	 */
	public static <T> void save(MultipartFile excel, ExcelImporter<T> importer, Class<T> clazz) {
		ImportListener<T> importListener = new ImportListener(importer);
		ExcelReaderBuilder builder = getReaderBuilder(excel, importListener, clazz);
		if (builder != null) {
			builder.doReadAll();
		}
	}

	/**
	 * 获取 ReaderBuilder
	 * @param excel
	 * @param readListener
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> ExcelReaderBuilder getReaderBuilder(MultipartFile excel, ReadListener<T> readListener, Class<T> clazz) {
		String filename = excel.getOriginalFilename();
		if (StringUtils.isEmpty(filename)) {
			throw new RuntimeException("请上传文件!");
		} else if (!StringUtils.endsWithIgnoreCase(filename, ".xls") && !StringUtils.endsWithIgnoreCase(filename, ".xlsx")) {
			throw new RuntimeException("请上传正确的excel文件!");
		} else {
			try {
				InputStream inputStream = new BufferedInputStream(excel.getInputStream());
				return EasyExcel.read(inputStream, clazz, readListener);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 导出
	 *
	 * @param list      数据
	 * @param fileName  文件名
	 * @param sheetName sheet名称
	 * @param clazz     类
	 * @param response
	 * @throws Exception
	 */
	public static void writeExcel(List<?> list, String fileName, String sheetName, Class clazz, HttpServletResponse response) {
		try {
			EasyExcel.write(getOutputStream(fileName, response), clazz).sheet(sheetName).doWrite(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出（自定义注册处理类）
	 *
	 * @param list      数据
	 * @param fileName  文件名
	 * @param sheetName sheet名称
	 * @param clazz     类
	 * @param response
	 * @throws Exception
	 */
	public static void writeExcel(List<?> list, String fileName, String sheetName, Class clazz, HttpServletResponse response, ExcelWriterBuilder excelWriterBuilder) {
		try {
			excelWriterBuilder.file(getOutputStream(fileName, response));
			if (clazz != null) {
				excelWriterBuilder.head(clazz);
			}
			excelWriterBuilder.sheet(sheetName).doWrite(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出文件时为Writer生成OutputStream
	 *
	 * @param fileName
	 * @param response
	 * @return
	 */
	private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
		try {
			fileName = URLEncoder.encode(fileName, Charsets.UTF_8.name());
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding(Charsets.UTF_8.name());
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
			return response.getOutputStream();
		} catch (IOException e) {
			throw new Exception("导出excel表格失败!", e);
		}
	}

	/**
	 * 自定义样式
	 *
	 * @return
	 */
	private static WriteHandler createTableStyle() {
		// 头的策略
		WriteCellStyle headWriteCellStyle = new WriteCellStyle();
		// 背景设置为红色
		headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		WriteFont headWriteFont = new WriteFont();
		headWriteFont.setFontHeightInPoints((short) 20);
		headWriteCellStyle.setWriteFont(headWriteFont);
		// 内容的策略
		WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
		// 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
		contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
		// 背景绿色
		contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		WriteFont contentWriteFont = new WriteFont();
		// 字体大小
		contentWriteFont.setFontHeightInPoints((short) 20);
		contentWriteCellStyle.setWriteFont(contentWriteFont);
		// 设置边框的样式
		contentWriteCellStyle.setBorderBottom(BorderStyle.DASHED);
		contentWriteCellStyle.setBorderLeft(BorderStyle.DASHED);
		contentWriteCellStyle.setBorderRight(BorderStyle.DASHED);
		contentWriteCellStyle.setBorderTop(BorderStyle.DASHED);
		// 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
		return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
	}

	/**
	 * 单元格合并
	 *
	 * @return
	 */
	private static LoopMergeStrategy getLoopMergeStrategy() {
		// 每隔2行会合并。当然其他合并策略也可以自己写
		return new LoopMergeStrategy(2, 0);
	}

	/**
	 * 自定义列宽
	 *
	 * @return
	 */
	private static LongestMatchColumnWidthStyleStrategy getLongestMatchColumnWidthStyleStrategy() {
		LongestMatchColumnWidthStyleStrategy strategy = new LongestMatchColumnWidthStyleStrategy();
		return strategy;
	}

	/**
	 * 下拉，超链接
	 */
	private static CellWriteHandler getCustomCellWriteHandler() {
		class CustomCellWriteHandler implements CellWriteHandler {
			@Override
			public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
										 Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
			}

			@Override
			public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
										Head head, Integer relativeRowIndex, Boolean isHead) {
			}

			@Override
			public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
										 List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
				// 这里可以对cell进行任何操作
				if (isHead && cell.getColumnIndex() == 0) {
					CreationHelper createHelper = writeSheetHolder.getSheet().getWorkbook().getCreationHelper();
					Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.URL);
					hyperlink.setAddress("https://github.com/alibaba/easyexcel");
					cell.setHyperlink(hyperlink);
				}
			}

		}
		return new CustomCellWriteHandler();
	}

	private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-Type", "application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> void defaultExport(List<T> dataList, Class<?> clz, String fileName, HttpServletResponse response, ExportParams exportParams) {
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, clz, dataList);
		if (workbook != null) {
			downLoadExcel(fileName, response, workbook);
		}
	}

	public static <T> void exportExcel(List<T> dataList, String title, String sheetName, Class<?> clz, String fileName, boolean isCreateHeader, HttpServletResponse response) {
		ExportParams exportParams = new ExportParams(title, sheetName);
		exportParams.setCreateHeadRows(isCreateHeader);
		defaultExport(dataList, clz, fileName, response, exportParams);
	}

	public static <T> void exportExcel(List<T> dataList, String title, String sheetName, Class<?> clz, String fileName, HttpServletResponse response) {
		defaultExport(dataList, clz, fileName, response, new ExportParams(title, sheetName));
	}

	private static void defaultExport(List<Map<String, Object>> dataList, String fileName, HttpServletResponse response) {
		Workbook workbook = ExcelExportUtil.exportExcel(dataList, ExcelType.HSSF);
		if (workbook != null) {
			downLoadExcel(fileName, response, workbook);
		}
	}

	public static void exportExcel(List<Map<String, Object>> dataList, String fileName, HttpServletResponse response) {
		defaultExport(dataList, fileName, response);
	}

	public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> clz) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}

		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);

		try {
			return ExcelImportUtil.importExcel(new File(filePath), clz, params);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> clz) {
		if (file == null) {
			return null;
		}

		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);

		try {
			return ExcelImportUtil.importExcel(file.getInputStream(), clz, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 导入数据
	 * userEnity 你自己新建的实体类 实体类代码在下面
	 * @param file
	 * @param clz
	 * @return
	 */
	public static List<T> importExcel(MultipartFile file, Class<T> clz) {
		if (file == null) {
			return null;
		}

		ImportParams params = new ImportParams();
		params.setTitleRows(0);
		params.setHeadRows(1);
		try {
			return ExcelImportUtil.importExcel(file.getInputStream(), clz, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
