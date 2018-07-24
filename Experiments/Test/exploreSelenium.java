package Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class exploreSelenium extends Locator{
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver;
		System.setProperty("webdriver.ie.driver","D:\\AztecAutomationTest\\AztecFinancial\\Resource\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		try {
			driver.navigate().to("http://otbperftest:155/LogOnPage.aspx?CompanyName=BBNTFASB_Test&Culture=en-US");
			driver.findElement(MyLocator("ctl00_ContentPlaceHolder_loginControl_txtUserName")).clear();
			driver.findElement(MyLocator("ctl00_ContentPlaceHolder_loginControl_txtUserName")).sendKeys("Vicky");	
		}
		finally {
			driver.close();	
		}
		
	}

	@Override
	public List<WebElement> findElements(SearchContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
