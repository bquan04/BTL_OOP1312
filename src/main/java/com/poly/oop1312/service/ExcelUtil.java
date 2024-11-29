package com.poly.oop1312.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poly.oop1312.model.Person;

public class ExcelUtil {
	public static void writeExcel(String filePath, List<Person> personList, String sheetName, String[] header)
			throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet(sheetName);

		Row headerRow = sheet.createRow(0);
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		for (int i = 0; i < header.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(header[i]);
			cell.setCellStyle(headerStyle);
		}

		int rowNum = 1;
		for (Person user : personList) {
			Row row = sheet.createRow(rowNum++);
			int stt = rowNum - 1;
			row.createCell(0).setCellValue(stt);
			row.createCell(1).setCellValue(user.getPersonID());
			row.createCell(2).setCellValue(user.getUsername());
			row.createCell(3).setCellValue(user.getFullName());
			row.createCell(4).setCellValue(user.getDateOfBirth().toString());
			row.createCell(5).setCellValue(user.getAvatar());
			row.createCell(6).setCellValue(user.getRegisterDay().toString());
			row.createCell(7).setCellValue(user.getEmail());
			row.createCell(8).setCellValue(user.getAdminRole() ? "Admin" : "User");
			row.createCell(9).setCellValue(user.getActive() ? "Đang sử dụng" : "Đã khóa");

		}

		for (int i = 0; i < 10; i++) {
			sheet.autoSizeColumn(i);
		}

		FileOutputStream outputStream = new FileOutputStream(filePath);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
