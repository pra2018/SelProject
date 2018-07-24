package com.aztec.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Setup {
	WebDriver driver;
	public Setup(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	@FindBy (id="lnkMenuCS")
	private WebElement abc;

	public void clickSetup() {
		abc.click();
	}
}
