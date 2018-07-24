package com.aztec.Testcases.receiptPosting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aztec.ObjectRepository.ReceiptPosting.ReceiptBatchManagement;
import com.aztec.ObjectRepository.ReceiptPosting.ReceiptPosting;
import com.aztec.ObjectRepository.ReceiptPosting.ReceiptReceivables;
import com.aztec.dataReader.XlsDataReader;

public class DealReceiptPostingTest  {
	WebDriver driver;

	@BeforeTest(alwaysRun = true)
	public void testSetup() {
		// App Login code
		System.setProperty("webdriver.ie.driver","D:\\AztecAutomationTest\\AztecFinancial\\Resource\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.navigate().to("http://otbperftest:155/LogOnPage.aspx?CompanyName=BBNTFASB_Test&Culture=en-US");
		//http://otbapsrv:451/LogOnPage.aspx?CompanyName=BBNT&Culture=en-US
		//driver.findElement(By.id("ctl00_ContentPlaceHolder_lnkCompany"));
		//http://otbperftest:155/LogOnPage.aspx?CompanyName=BBNTFASB_Test&Culture=en-US
		driver.findElement(By.id("ctl00_ContentPlaceHolder_loginControl_txtUserName")).clear();
		driver.findElement(By.id("ctl00_ContentPlaceHolder_loginControl_txtUserName")).sendKeys("Prabhat.k");
		driver.findElement(By.id("ctl00_ContentPlaceHolder_loginControl_txtPassword")).clear();
		driver.findElement(By.id("ctl00_ContentPlaceHolder_loginControl_txtPassword")).sendKeys("Samsung-321");
		driver.findElement(By.id("ctl00_ContentPlaceHolder_loginControl_cmdLogin1_cmdButtonControl")).click();
		try {
			driver.switchTo().alert().accept();
		}catch (Exception e) {
			e.getMessage();
		}

	}

	@Test(groups= {"Group-1"},enabled=true,dataProvider="getReceiptPostingData")
	public void doReceiptPosting(Hashtable<String, String> excelSheetData) throws NumberFormatException, ParseException, InterruptedException {
		String batchName = null;
		ReceiptBatchManagement batch;
		ReceiptPosting receiptBatch;
		ReceiptReceivables receiptReceivables;

		receiptReceivables=new ReceiptReceivables(driver);
		receiptReceivables.navigateToReceiptPostingPage();
		//Call Method based on Receipt Posting Mode
		if(excelSheetData.get("PaymentMode").equals("ReceiptPostAgainstInvoice")) {
			receiptReceivables.navigateToReceiptPostByInvoice(excelSheetData.get("SearchEntity"),excelSheetData.get("SearchParameter"));
			receiptBatch=new ReceiptPosting(driver);
			batchName=receiptBatch.receiptPostByInvoice(//excelSheetData.get("InputData").toString(),
					excelSheetData.get("ReceiptType"),excelSheetData.get("SearchParameter"));

		} else if(excelSheetData.get("PaymentMode").equals("ReceiptPostForSpecifiedAmount")) {
			receiptReceivables.navigateToReceiptPostByReceivable(excelSheetData.get("SearchEntity"),excelSheetData.get("SearchParameter"));
			receiptBatch=new ReceiptPosting(driver);
			batchName=receiptBatch.receiptPostByReceivables(excelSheetData.get("InputData").toString(),excelSheetData.get("ReceiptType"));

		} else if(excelSheetData.get("PaymentMode").equals("ReceiptPostTillDueDate")) {
			Date dueDate = null;
			receiptReceivables.navigateToReceiptPostByReceivable(excelSheetData.get("SearchEntity"),excelSheetData.get("SearchParameter"));
			receiptBatch=new ReceiptPosting(driver);
			DateFormat format= new SimpleDateFormat("mm/dd/yyyy");
			try {
				dueDate = format.parse(excelSheetData.get("InputData").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			batchName=receiptBatch.receiptPostByReceivables(excelSheetData.get("ReceiptType"), dueDate);
		}
        // Batch Posting
		batch=new ReceiptBatchManagement(driver);
		batch.processReceiptBatch(batchName);

	}

	@AfterTest(alwaysRun = true)
	public void cleanUp() {
		driver.close();
	}

	@DataProvider 
	public Object[][] getReceiptPostingData(){
		return  XlsDataReader.dataReader();
	}

}
