package com.aztec.ObjectRepository;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Login  {
	
	public Login(WebDriver driver) {
		// TODO Auto-generated constructor stub
	PageFactory.initElements(driver, this);
	
	}
	@FindBy(id="ctl00_ContentPlaceHolder_lnkCompany")
	private WebElement rideLeaseWave;
	@FindBy(id="ctl00_ContentPlaceHolder_loginControl_txtUserName")
	private WebElement userName;
	
	@FindBy(id="ctl00_ContentPlaceHolder_loginControl_txtPassword")
	private WebElement password;
	
	@FindBy(id="ctl00_ContentPlaceHolder_loginControl_cmdLogin1_cmdButtonControl")
	private WebElement submit;
	
	public void rideleaseWave(WebDriver driver) {
		rideLeaseWave.click();
		Set<String> NewWindow =driver.getWindowHandles();
		Iterator<String> itr=NewWindow.iterator();
		while(itr.hasNext())
		driver.switchTo().window(itr.next());
	}
	
	
	
	public void enterUsername() {
		userName.clear();
		userName.sendKeys("Amith");
		}
	public void enterPassword() {
		password.clear();
		password.sendKeys("Samsung-1234");
	}
	
	public void clickSubmitButton() {
		submit.click();
	}
}
