package com.aztec.ObjectRepository.ReceiptPosting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ReceiptReceivables {
	WebDriver driver;
	public ReceiptReceivables(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="ctl00ctl04WebMenuControl_5")
	private WebElement receiptReceivableMenu;

	@FindBy(id="ctl00ctl04WebMenuControl_5_9") 
	private WebElement postReceiptSubMenu;

	@FindBy(id="ctl00ctl04WebMenuControl_5_9_1")
	private WebElement cashSubmenu;

	@FindBy(id="ctl00_NB_PH_InvoiceListNavBarUserControl_cmdPostByCharge")
	private WebElement receiptPostByReceivable;

	@FindBy(id="ctl00_FC_PH_ReceivableSearchCriteriaUserControl_chkAccountNo")
	private WebElement checkAccountno;

	@FindBy(id="ctl00_FC_PH_ReceivableSearchCriteriaUserControl_txtAccountNo")
	private WebElement AccountNo;

	@FindBy(id="ctl00_FC_PH_ReceivableSearchCriteriaUserControl_rdnLoanSequenceNo")
	private WebElement chkLoan;

	@FindBy(id="ctl00_FC_PH_ReceivableSearchCriteriaUserControl_txtLoanSequenceNo")
	private WebElement LoanSeqNo;

	@FindBy(id="ctl00_FC_PH_ReceivableSearchCriteriaUserControl_txtLeaseSequenceNo")
	private WebElement searchByLeaseSequencenumber;

	@FindBy(id="ctl00_FT_PH_cmdPostReceipts_cmdButtonControl")
	private WebElement postReceiptButton;

	@FindBy(id="ctl00_FT_PH_cmdClose_cmdButtonControl")
	private WebElement CloseBtn;

	// Invoice posting

	@FindBy(id="ctl00_FC_PH_InvoiceSearchCriteriaUserControl_rdnLeaseSequenceNo")
	private WebElement chkInvoiceLeaseSeqNo;

	@FindBy(id="ctl00_FC_PH_InvoiceSearchCriteriaUserControl_txtLeaseSequenceNo")
	private WebElement InvoiceSearchByLease;

	@FindBy(id="ctl00_FC_PH_InvoiceSearchCriteriaUserControl_chkAccountNo")
	private WebElement InvoiceSearchchkAccountNo;

	@FindBy(id="ctl00_FC_PH_InvoiceSearchCriteriaUserControl_txtAccountNo")
	private WebElement InvoiceSearchByAccountNo;

	@FindBy(id="ctl00_FC_PH_InvoiceSearchCriteriaUserControl_rdnLoanSequenceNo")
	private WebElement chkInvoiceLoanSeqNo;

	@FindBy(id="ctl00_FC_PH_InvoiceSearchCriteriaUserControl_txtLoanSequenceNo")
	private WebElement InvoiceSearchLoanSequenceNo;

	@FindBy(id="ctl00_FT_PH_cmdSearch_cmdButtonControl")
	private WebElement InvoiceSearchBtn;

	@FindBy(id="ctl00_FT_PH_cmdClose_cmdButtonControl")
	private WebElement InvoiceCloseBtn;

	@FindBy(xpath=".//*[@id='ctl00_GlobalInstructionBox_DisplayArea']/font/b") 
	////table[@id='ctl00_GlobalInstructionBox_errorTable']/tbody/tr[2]/td/div/div/font/br
	private WebElement NoInvoiceError;

	@FindBy(tagName="br") 
	////table[@id='ctl00_GlobalInstructionBox_errorTable']/tbody/tr[2]/td/div/div/font/br
	private WebElement ErrorMsg;

	public void clickReceiptReceivableMenu() {
		
	}
	
	
	
	public void navigateToReceiptPostingPage() {
		receiptReceivableMenu.click();
		postReceiptSubMenu.click();
		cashSubmenu.click();
	}

	//receipt post by receivables
	public void navigateToReceiptPostByReceivable(String searchFilter, String SearchParaMeter) {
		receiptPostByReceivable.click();
		if(searchFilter.toLowerCase().equals("leasesequencenumber")) {
			searchByLeaseSequencenumber.sendKeys(SearchParaMeter);
		}else if(searchFilter.toLowerCase().equals("loansequencenumber")) {
			chkLoan.click();
			LoanSeqNo.sendKeys(SearchParaMeter);
		}else if(searchFilter.toLowerCase().equals("customeraccountnumber")){
			AccountNo.sendKeys(SearchParaMeter);
		}

		postReceiptButton.click();
		
		}
	// receipt posting against invoices

	public void navigateToReceiptPostByInvoice(String searchFilter, String SearchParaMeter) {
		if(searchFilter.toLowerCase().equals("customeraccountnumber")) {
			InvoiceSearchchkAccountNo.click();
			InvoiceSearchByAccountNo.sendKeys(SearchParaMeter);
		}else if(searchFilter.toLowerCase().equals("leasesequencenumber")) {
			InvoiceSearchchkAccountNo.click();
			chkInvoiceLeaseSeqNo.click();
			InvoiceSearchByLease.sendKeys(SearchParaMeter);
		}else if(searchFilter.toLowerCase().equals("loansequencenumber")){
			InvoiceSearchchkAccountNo.click();
			chkInvoiceLoanSeqNo.click();
			InvoiceSearchLoanSequenceNo.sendKeys(SearchParaMeter);
		}
		InvoiceSearchBtn.click();
	
	}






}
