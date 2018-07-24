package com.aztec.dataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsDataReader {

  public static Object [][] dataReader() {
		XSSFWorkbook workbook = null;
		XSSFSheet sheet;
		FileInputStream fis = null;
		int lastRowNum;
		int sheetIndex;
		Object [][]data;
		Hashtable<String, String>eachRowData;
		int rowDataCount=0;
		String filePath="D:\\AztecAutomationTest\\AztecFinancial\\TestDataFiles\\ReceiptPostingData.xlsx";
		String sheetName="Receipts";
		
		File file=new File(filePath);
		try {
			fis=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook= new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheetIndex= workbook.getSheetIndex(sheetName);
		sheet=workbook.getSheetAt(sheetIndex);
		lastRowNum=sheet.getLastRowNum();
		data =new Object[lastRowNum][1];
		
		for(int i=1 ;i<=lastRowNum;i++) {
			eachRowData=new Hashtable<String, String>();
			int lastColNum=sheet.getRow(i).getLastCellNum();
			for(int j=0;j<lastColNum;j++) {
				CellType formatOfCell = sheet.getRow(i).getCell(j).getCellTypeEnum();
				if(formatOfCell.equals(CellType.NUMERIC) ) {
					String str1=NumberToTextConverter.toText(sheet.getRow(i).getCell(j).getNumericCellValue());
					eachRowData.put(sheet.getRow(0).getCell(j).getStringCellValue(), str1);
				}else {
					String str2=sheet.getRow(i).getCell(j).getStringCellValue();
					eachRowData.put(sheet.getRow(0).getCell(j).getStringCellValue(), str2);
				}
			}
			data[rowDataCount][0]=eachRowData;
			rowDataCount++;
		}
		return data;
	}
}
