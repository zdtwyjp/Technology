package org.tech.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	
	/**
	 * 导出数据
	 * @param response
	 * @param sheetName
	 * @param sheetHeader
	 * @param rows
	 * @throws Exception
	 */
	public static void export(HttpServletResponse response, String sheetName, String sheetHeader, 
			List<Map<String, Object>> rows) throws Exception{
		String fileName = sheetName + ".xls";
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes("GBK"), "ISO8859_1") + "\"");
		ServletOutputStream outputStream = response.getOutputStream();
		// 头部数据
		List<String[]> arrs = parseSheetHeaders(sheetHeader);
		String[] headerNames = arrs.get(0);
		String[] headderStrs = arrs.get(1);
		// 内容数据
		List<Object[]> content = new ArrayList<Object[]>();
		if (rows != null && rows.size() > 0) {
			for (int i = 0; i < rows.size(); i++) {
				rows.get(i).put("xh",i+1);
				Map<String, Object> cols = rows.get(i);
				List<Object> tmp = new ArrayList<Object>();
				for (String str : headderStrs) {
						tmp.add(cols.get(str.toString()));
				}
				content.add(tmp.toArray());
			}
		}

		HSSFWorkbook worksheet = ExcelUtil.export(headerNames, content,
				sheetName);
		worksheet.write(outputStream);
		outputStream.flush();
	}
	
	public static HSSFWorkbook export(String[] header, List<Object[]> content,
			String sheetName) {
		HSSFWorkbook worksheet = new HSSFWorkbook();
		HSSFSheet sheet = worksheet.createSheet(sheetName);
		//标题
		HSSFRow title = sheet.createRow(0);
		title.setHeight((short) 550);
		//标题合并单元格
		CellRangeAddress range = new CellRangeAddress(0, (short)0,0,(short)header.length-1);
		sheet.addMergedRegion(range);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue(sheetName);
		HSSFCellStyle titleStyle = worksheet.createCellStyle(); 
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont titleFont = worksheet.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontHeightInPoints((short) 15);
		titleStyle.setFont(titleFont);
		titleCell.setCellStyle(titleStyle);
		//创建表头
		HSSFRow tileRow = sheet.createRow(1);
		HSSFCellStyle headStyle = worksheet.createCellStyle();
		headStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());  //前景色
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//表头边框
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		HSSFFont hfont = worksheet.createFont();
		hfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		hfont.setFontHeightInPoints((short) 11);
		headStyle.setFont(hfont);
		//内容格式
		HSSFCellStyle contentStyle = worksheet.createCellStyle(); 
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//内容边框
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		HSSFFont contentfont = worksheet.createFont();
		contentfont.setFontName("微软雅黑");
		contentfont.setFontHeightInPoints((short) 10);
		contentStyle.setFont(contentfont);
		//表头数据
		for (int i = 0; i < header.length; i++) {
			Cell cl = tileRow.createCell(i);
			cl.setCellValue(header[i]);
			cl.setCellStyle(headStyle);
			if(i==0){
			//为序号一列设置宽度
			sheet.setColumnWidth(i,(int)35.7*70);
			}else{
			sheet.setColumnWidth(i,(int)35.7*170);
			}
		}
       //内容数据
		if (content != null && content.size() > 0) {
			for (int i = 1; i < content.size()+1; i++) {
				Object[] c = content.get(i-1);
				HSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < c.length; j++) {
					String tmp = "";
					if(c[j] != null){
						tmp = c[j].toString();
					}
					Cell cell = row.createCell(j);
					cell.setCellValue(tmp);
					cell.setCellStyle(contentStyle);
				}
			}
		}
		return worksheet;
	}
	
	private static List<String[]> parseSheetHeaders(String sheetHeaders) {
		List<String[]> args = new ArrayList<String[]>();
		List<String> names = new ArrayList<String>();
		List<String> strs = new ArrayList<String>();
		String[] arrs = sheetHeaders.split(",");
		for (String arr : arrs) {
			String[] headerArr = arr.split(":");
			names.add(headerArr[0]);
			strs.add(headerArr[1]);
		}
		args.add(listToArray(names));
		args.add(listToArray(strs));
		return args;
	}

	private static String[] listToArray(List<String> list) {
		String[] arr = new String[list.size()];
		int i = 0;
		for (String str : list) {
			arr[i] = str;
			i++;
		}
		return arr;
	}
}
