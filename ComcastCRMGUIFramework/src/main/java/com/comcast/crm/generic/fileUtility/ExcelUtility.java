package com.comcast.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String  getDataFromExcel(String sheetname, int rowNum, int celNum) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./testscriptData/testscript.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		
		String data = wb.getSheet(sheetname).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		return data;
		
	}
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./testscriptData/testscript.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
	}
	public void setDataIntoExcel(String sheetName,int rowNum, int celNum, String data) throws Throwable {
		FileInputStream fis = new FileInputStream("./testscriptData/testscript.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.close();
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
		
		FileOutputStream fos= new FileOutputStream ("./testscriptData/testscript.xlsx");
		wb.write(fos);
		wb.close();
		}
	}
	
	


