package com.aztec.dataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataWriter {
	
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
	
	public void dataReader() throws IOException {
		
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
		 sheet=workbook.createSheet("Test");
		 Hashtable<String, Object> table=new  Hashtable<String, Object>();
		 table.put("1", new Object[] {"Prabhat", "Kumar"});
		 table.put("2", new Object[] {"Vicky","Singh"});
		 
		 Set<String> keySet=table.keySet();
		 int rowNum=0;
		 for(String key: keySet) {
			 
			 Row row=sheet.createRow(rowNum++);
			 Object [] rowData=(Object[]) table.get(key);
			 int cellNum=0;
			 for(Object obj: rowData) {
				 Cell cell=row.createCell(cellNum++);
				 if(obj instanceof Integer)
					 cell.setCellValue((Integer)obj);
				 else
					 cell.setCellValue((String)obj);
			 }
			 
		 }
		 try {
			FileOutputStream out=new FileOutputStream(file);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		
	}

}
