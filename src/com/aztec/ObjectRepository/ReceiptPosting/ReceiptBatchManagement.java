package com.aztec.ObjectRepository.ReceiptPosting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceiptBatchManagement {
	WebDriver driver;
	public ReceiptBatchManagement(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ctl00ctl04WebMenuControl_5")
	private WebElement receiptReceivableMenu;
	
	@FindBy(id="ctl00ctl04WebMenuControl_5_11")
	private WebElement ReceiptBatchList;

	@FindBy(id="ctl00_FC_PH_schSearchControl_bshBasicSearch_BatchNameUC0_txtBatchName")
	private WebElement BatchName;
	
	@FindBy(id="ctl00_FC_PH_schSearchControl_cmdSearch_cmdButtonControl")
	private WebElement batchListSearch;
	
	@FindBy(id="ctl00_FT_PH_cmdPost_cmdButtonControl")
	private WebElement batchPostBtn;
	
	@FindBy(id="ctl00_FT_PH_cmdClose_cmdButtonControl")
	private WebElement closeBtn;

	public void processReceiptBatch(String receiptBatchName) {
		receiptReceivableMenu.click();
		ReceiptBatchList.click();
		BatchName.sendKeys(receiptBatchName);
		batchListSearch.click();
		//batchPostBtn.click();
		closeBtn.click();
		
	}
}
