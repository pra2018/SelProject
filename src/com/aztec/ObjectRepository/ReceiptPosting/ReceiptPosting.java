package com.aztec.ObjectRepository.ReceiptPosting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReceiptPosting {


	WebDriver driver;
	WebDriverWait wait;
	String dynBatchName;

	public ReceiptPosting(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="ctl00ctl04WebMenuControl_5")
	private WebElement receiptReceivableMenu;

	@FindBy(id="ctl00_FC_PH_cboPaymentType_cboComboBoxControl")
	private WebElement receiptType;

	@FindBy(id="igtxtctl00_FC_PH_txtCheckAmount_txtWebCurrencyEdit")
	private WebElement checkAmount;
	@FindBy(id="ctl00_FC_PH_txtCheckNo")
	private WebElement checkNumber;

	@FindBy(id="ctl00_FC_PH_grdPayment_lnkAll")
	private WebElement all;

	@FindBy(id="ctl00_FC_PH_grdPayment_lnkAllCP")
	private WebElement all_Current;

	@FindBy(id="ctl00_FC_PH_cmdDistribute_cmdButtonControl")
	private WebElement distributeBtn;


	@FindBy(name="ctl00$FC_PH$cboUnallocatedCashGLTemplate$cboGLTransaction")
	private WebElement UnallocatedCashGLTemplate;

	@FindBy(id="igtxtctl00_FC_PH_txtUnAllocatedCash_txtWebCurrencyEdit")
	private WebElement UnallocatedCash ;

	@FindBy(id="ctl00_FC_PH_txtComments")
	private WebElement UnallocatedCashComment;

	@FindBy(id="ctl00_FT_PH_cmdClose_cmdButtonControl")
	private WebElement closeBtn;

	@FindBy(id="ctl00_FT_PH_cmdPost_cmdButtonControl")
	private WebElement postBtn;

	//---------------------------------------Receipt Grid----------------------------
	@FindBy(id="ctl00xFCxPHxgrdPaymentxgrdList_rc_0_7")
	private WebElement firstUnpaidReceivableDate;

	@FindBy(id="ctl00_FC_PH_grdPayment_lblTotalNumberOfRecordsNumberPart")
	private WebElement totalNumberOfRecords;

	@FindBy(id="ctl00_FC_PH_txtRemainingBalance_txtWebCurrencyEdit_p")
	private WebElement remainingBalance;//igtxtctl00_FC_PH_txtRemainingBalance_txtWebCurrencyEdit


	// -----------------------------------------BATCH--------------------------------

	@FindBy(id="ctl00_FC_PH_cmdImportBatchId_cmdImportButton")
	private WebElement importBatch;

	@FindBy(id="ctl00_FT_PH_cmdAdd_cmdButtonControl")//ctl00_FT_PH_cmdAdd_cmdButtonControl

	private WebElement addBatch;

	@FindBy(name="ctl00$FC_PH$txtBatchName")
	private WebElement batchName;

	@FindBy(id="igtxtctl00_FC_PH_txtDeposTotal_txtWebCurrencyEdit")
	private WebElement batchAmount;//igtxtctl00_FC_PH_txtDeposTotal_txtWebCurrencyEdit

	@FindBy(id="ctl00_FC_PH_cboDepositSource_cboComboBoxControl")
	private WebElement depositeSource;
	@FindBy(id="ctl00_FT_PH_cmdSelectClient_cmdExportButton")
	private WebElement saveSelectBtnBatch;

	//--------------------------------------------Invoice Grid-------------------------------------------------------------------------------------

	@FindBy(id="ctl00_FC_PH_grdList_lblTotalNumberOfRecordsNumberPart")
	private WebElement TotalNumberofInvoice;

	@FindBy(id="ctl00_FT_PH_cmdRedirectTo_cmdButtonControl")
	private WebElement receiptPostbyInvoice;
	@FindBy(id="ctl00_FC_PH_grdList_lnkAll")
	private WebElement all_InvoiceList;
	@FindBy(id="ctl00_FC_PH_grdList_lnkAllCP")
	private WebElement allCurrent_InvoiceList;
	// ------------------------------------------------METHODS-------------------------------------------------------------------------------------------

	//  To Select receipt type
	public void selectReceiptType(String receiptTypename, WebElement selectDrpDwnWebElement) {
		Select sel=new Select(selectDrpDwnWebElement);
		sel.selectByVisibleText(receiptTypename);
		if(receiptTypename.equals("Check")) {
			String checkNum=Integer.toString(Math.abs(new Random().nextInt()));
			checkNumber.sendKeys(checkNum);
		}
	}

	//  To Select deposit receipt type
	public void selectDepositReceiptType(String receiptTypename, WebElement selectDrpDwnWebElement) {
		Select sel=new Select(selectDrpDwnWebElement);
		sel.selectByVisibleText(receiptTypename);
	}
	// Receipt Posting till a given Due Date
	public String receiptPostByReceivables(String receiptTypeName,Date date) throws NumberFormatException, ParseException, InterruptedException  {
		double receivableBalance = 0;
		String batchAmount = null; 

		DateFormat format= new SimpleDateFormat("mm/dd/yyyy");

		//InitialgridDate=;
		int i=0;
		int resetPageFlag=0;
		while (format.parse(driver.findElement(By.id("ctl00xFCxPHxgrdPaymentxgrdList_rc_"+i+"_7")).getText().toString()).compareTo(date)<=0 ) {
			String dollarValue=driver.findElement(By.id("ctl00xFCxPHxgrdPaymentxgrdList_rc_"+i+"_11")).getAttribute("uv").toString();
			double amount=Double.parseDouble(dollarValue);
			receivableBalance=receivableBalance+amount;
			driver.findElement(By.id("ctl00xFCxPHxgrdPaymentxgrdList_rc_"+i+"_0")).click();
			i++;
			/*resetPageFlag=resetPageFlag+1;
			if(resetPageFlag==49) {
				//code to click on page
				resetPageFlag=0;
				i=0;
			} */

		}
		if(receivableBalance!=0) {
			batchAmount=Double.toString(receivableBalance);
			selectReceiptType(receiptTypeName, receiptType);
			dynBatchName=createBatch(receiptTypeName,batchAmount, null );
			checkAmount.clear();
			checkAmount.sendKeys(batchAmount);
			distributeBtn.click();
			WebDriverWait wait=new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.elementToBeClickable(postBtn)).click();
			//postBtn.click();
			try {
				driver.switchTo().alert().accept();
			}catch(Exception e) {
				e.getMessage();

			}
			return dynBatchName;

		} else {
			closeBtn.click();
			return  "No due receivable exist till due date:"+ date;
		}


	}

	// Receipt Posting for Specified Amount
	public String receiptPostByReceivables(String amount,String receiptTypeName) throws InterruptedException {
		checkAmount.clear();
		checkAmount.sendKeys(amount);
		selectReceiptType(receiptTypeName, receiptType);
		createBatch(receiptTypeName, amount, null);
		selectReceiptChkBox();
		distributeBtn.click();
		//check for Unallocated Cash
		checkUnallocatedCash();

		postBtn.click();
		// check for Any alert
		try {
			driver.switchTo().alert().accept();
		}catch(Exception e) {
			e.getMessage();

		}

		return dynBatchName;
	}

	// Receipt posting for given Invoice Number
	public String receiptPostByInvoice(String receiptTypeName,String SearchParameter) throws InterruptedException { //String InvoiceNumber

	/*	String gridInvoiceNumber = null;
				int i=0;
				double Amount = 0.0;
				WebElement Invoice;
	do {
			//gridInvoiceNumber=driver.findElement(By.id("ctl00xFCxPHxgrdListxgrdList_rc_"+i+"_4")).getAttribute("uv");
			Invoice= driver.findElement(By.id("ctl00xFCxPHxgrdListxgrdList_rc_"+i+"_0"));
			Invoice.click(); 
			double Amount2=Double.parseDouble( driver.findElement(By.id("ctl00xFCxPHxgrdListxgrdList_rc_"+i+"_6")).getAttribute("uv").toString());
			Amount=Amount+Amount2;
			i++;
		}while(i<Integer.parseInt(TotalNumberofInvoice.getText().toString())); // && (!gridInvoiceNumber.equals(InvoiceNumber))
    */  
		selectInvoiceChkBox();
		receiptPostbyInvoice.click();
		String batchAmount=remainingBalance.getAttribute("value").toString();
		System.out.println(batchAmount);
	    selectReceiptType(receiptTypeName, receiptType);
		dynBatchName=createBatch(receiptTypeName, batchAmount, SearchParameter);
		checkAmount.clear();
		checkAmount.sendKeys(batchAmount);
		selectReceiptChkBox();
		distributeBtn.click();
		//check for Unallocated Cash
		checkUnallocatedCash();

		postBtn.click();
		// check for Any alert
		try {
			driver.switchTo().alert().accept();
		}catch(Exception e) {
			e.getMessage();

		}

		return dynBatchName;

	}


	//To Create receipt batch
	public String createBatch(String receiptTypeName,String amount,String SearchEntity) throws InterruptedException  {
		//create batch
		String parentWindow=driver.getWindowHandle();
		importBatch.click();
		for(String winHandel: driver.getWindowHandles()) {
			driver.switchTo().window(winHandel);
			
		}
		 Dimension d = new Dimension(2400,1200);
			//Resize current window to the set dimension
			        driver.manage().window().setSize(d);
			    	WebDriverWait wait=new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_FT_PH_cmdAdd_cmdButtonControl"))).click();
		/*try {
			waitForAWhile(2000);
		} 
		finally {
			driver.manage().window().maximize();
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_FT_PH_cmdAdd_cmdButtonControl"))).click();
		}
            */
		//checkButtonVisisbility();



		for(String winHandel: driver.getWindowHandles()) {
			driver.switchTo().window(winHandel);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		Date date= new Date();
		String currentDateTime=sdf.format(date);
		dynBatchName=SearchEntity+currentDateTime;
		batchName.sendKeys(dynBatchName);
		selectDepositReceiptType(receiptTypeName, depositeSource);
		batchAmount.clear();
		batchAmount.sendKeys(amount);
		saveSelectBtnBatch.click();
		// receipt Posting Page
		driver.switchTo().window(parentWindow);
		driver.manage().window().maximize();
		return dynBatchName;

	}

	// To check for Unallocated Cash
	public String checkUnallocatedCash() {
		String defalutValue="$0.00";
		if(!UnallocatedCash.getAttribute("value").toString().equals(defalutValue)) {
			Select sel= new Select(UnallocatedCashGLTemplate);
			sel.selectByVisibleText("UnallocatedCash EF");
			UnallocatedCashComment.sendKeys("ReceiptPost UC for :"+dynBatchName);
			return UnallocatedCash.getAttribute("value").toString();
		}else {
			return defalutValue;
		}

	}

	public void waitForAWhile(long timeInmillSecond) {

		try {
			Thread.sleep(timeInmillSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectReceiptChkBox(){
		try {
			WebDriverWait w =new WebDriverWait(driver, (long) 0.5);
			w.until(ExpectedConditions.visibilityOf(all));
			all.click();
		}catch(Exception e) {
			all_Current.click();
		}
	}
    public void selectInvoiceChkBox(){
			try {
				WebDriverWait w =new WebDriverWait(driver, (long) 0.5);
				w.until(ExpectedConditions.visibilityOf(all_InvoiceList));
				all_InvoiceList.click();
			}catch(Exception e) {
				allCurrent_InvoiceList.click();
			}
}

}
	
