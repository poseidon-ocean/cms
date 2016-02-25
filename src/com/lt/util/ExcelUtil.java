package com.lt.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;
import org.apache.struts2.ServletActionContext;

/**
 * 
 *@Title:导出Excel文件
 *@Description:
 *@Author:think
 *@Since:2016年2月26日 上午4:07:19
 *@Version:1.0.0
 */
public class ExcelUtil {

	/**
	 * 将表格数据转换成字符串(小数只保留整数部分)
	 * 
	 * @param cell
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getStringValue(HSSFCell cell) {
		String value = "";
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
		case HSSFCell.CELL_TYPE_BLANK:
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			value = Long.valueOf(
					Double.valueOf(cell.getNumericCellValue()).longValue())
					.toString();// 只取整数部分
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			value = String.valueOf(cell.getErrorCellValue());
			break;
		}
		return value;
	}

	public static <ICell> HSSFWorkbook createExcelFile(List<String[]> list, String[] cellName,
			String sheetName, String fileName) throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		FileOutputStream fos = null;
		HSSFWorkbook workbook = null;
		workbook = new HSSFWorkbook();

		try {
			// 创建Excel表单
			HSSFSheet sheet = workbook.createSheet(sheetName);

			// 设置单元格格式为文本
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
			
			HSSFCellStyle centerStyle = workbook.createCellStyle();//设置为水平居中  
			centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			centerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
			HSSFCellStyle rightStyle = workbook.createCellStyle();//水平靠右  
			rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);  
			rightStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  

			HSSFFont font = workbook.createFont();    
			font.setFontName("黑体");    
			font.setFontHeightInPoints((short) 12);//设置字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //加粗
			centerStyle.setFont(font);
			
			sheet.addMergedRegion(new Region(0,(short)0,0,(short)(cellName.length-1))); 
			
			// 创建标题
			HSSFRow rowTitle = sheet.createRow(0);
			List<HSSFCell> listHSSFCellTitle = new ArrayList<HSSFCell>();
			HSSFCell cellT = rowTitle.createCell((short)0);

			cellT.setCellValue(sheetName);
			cellT.setCellStyle(centerStyle);

			// 创建第一行
			HSSFRow row0 = sheet.createRow(1);
			List<HSSFCell> listHSSFCell = new ArrayList<HSSFCell>();

			for (int i = 0; i < cellName.length; i++) {
				HSSFCell cell = row0.createCell((short) i);
				cell.setCellValue(cellName[i]);
			}
			
			int m = 0;
			// 在表格中填写数据 list为查询到的数据结果集O
			for (int i = 1; i <= list.size(); i++) {
				// HSSFRow row = sheet.createRow(i*list.size()+(j+1));
				HSSFRow row = sheet.createRow(i+1);
				String[] str = list.get(i-1);

				m = str.length;
				for (int j = 0; j < str.length; j++) {
					HSSFCell cell = row.createCell((short) j);
					cell.setCellValue(str[j]);
				}

			}
			
			//列宽自适应
			sheet = getAutoSizeColumnSheet(sheet,m);
			 
			fileName = getFileName(fileName);   
			response.setContentType("application/vnd.ms-excel");   
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);   
			OutputStream ouputStream = response.getOutputStream();   
			workbook.write(ouputStream);   
			ouputStream.flush();   
			ouputStream.close(); 

		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();

			throw e;

		}

		return workbook;
	}
	/**
	 * @Description:得到列宽自适应的HSSFSheet,内容需要填入
	 * @param sheet
	 * @param m
	 * @return 
	 * @ReturnType:HSSFSheet
	 * @exception 
	 * @Since:2015-9-15 上午11:22:58
	 * @Author:yleilei
	 */
	public static HSSFSheet getAutoSizeColumnSheet(HSSFSheet sheet, int m){
		// 列宽自适应，只对英文和数字有效
		for (int i = 0; i <= m; i++) {
			sheet.autoSizeColumn(i);
		}
		
		// 获取当前列的宽度，然后对比本列的长度，取最大值
		for (int columnNum = 0; columnNum <= m; columnNum++) {
			int columnWidth = sheet.getColumnWidth(columnNum) / 256;
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				HSSFRow currentRow;
				// 当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}

				if (currentRow.getCell(columnNum) != null) {
					HSSFCell currentCell = currentRow.getCell(columnNum);
					int length = (currentCell.toString()).getBytes().length;
					if (columnWidth < length) {
						columnWidth = length;
					}
				}
			}
			sheet.setColumnWidth(columnNum, columnWidth * 256);
		}
		return sheet;
	}
	
	/**
	 * 转码文件名
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName)
	{
		String file = "";
		try {
			file =  new String(fileName.getBytes("GBK"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file;
	}
	
	
	public static InputStream workbook2InputStream(HSSFWorkbook workbook,
			String fileName) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		baos.flush();
		byte[] aa = baos.toByteArray();
		InputStream excelStream = new ByteArrayInputStream(aa, 0, aa.length);
		baos.close();

		return excelStream;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("导出开始");
		// 封装数据并输出
		String sheetName = "测试列表";
		String fileName = "测试列表.xls";
		String[] cellName = { "测试1", "测试2", "测试3" };
		List<String[]> datalist = new ArrayList<String[]>();
		String[] strArr = new String[cellName.length];
		strArr[0] = "测试";
		strArr[1] = "测试";
		strArr[2] = "测试";
		datalist.add(strArr);
		ExcelUtil.createExcelFile(datalist, cellName, sheetName, fileName);
		System.out.println("导出结束");
	}

}
