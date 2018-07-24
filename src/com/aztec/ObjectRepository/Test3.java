package com.aztec.ObjectRepository;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aztec.dataReader.XlsDataReader;
import com.aztec.dataReader.dataWriter;
import com.graphbuilder.math.func.RandFunction;

public class Test3 {
	
	//@Test(dataProvider="excelData")
	public void xlstest(Hashtable<String, String> data) {
	
			//System.out.println(data.entrySet());
		System.out.println(data.get("SearchEntity")+"---"+data.get("SearchParameter")
		+"----"+data.get("ReceiptType")+"-----"+data.get("PaymentMode")+"---"+data.get("InputData"));
	}
		
	
  //@DataProvider
  //public Object[][] excelData(){
	//return XlsDataReader.dataReader();
	  
  //}
	public  static void main (String args[]) {
		dataWriter r=new dataWriter();
		try {
			r.dataReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 }
 

 
		 
	


