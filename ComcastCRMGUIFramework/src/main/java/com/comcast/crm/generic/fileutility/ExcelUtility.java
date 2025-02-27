package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws Exception 
	{
		FileInputStream fis1 = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		return data;
	}
	public int getRowCount(String sheetName) throws Exception
	{
		FileInputStream fis1 = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
	}
	
	public void setDataIntoExcel(String sheetName,int rowNum,int celNum,String data) throws Exception
	{
		FileInputStream fis1 = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
		FileOutputStream fos=new FileOutputStream("./testdata/testScriptdata.xlsx");
		wb.write(fos);
		wb.close();
	}
	
	

}
