package Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public  class Locator extends By  {
	static WebDriver driver;
	public static By MyLocator(final String text) {
		return new By() {

			@Override
			public List<WebElement> findElements(SearchContext arg0) {
				// TODO Auto-generated method stub
				List<WebElement> element = null;
				if(arg0.findElements(name(text)) != null)
					element=arg0.findElements(name(text));
				else if(arg0.findElements(id(text)) != null)
					element=arg0.findElements(id(text));

				return element;
			}	
		};
	}
	@Override
	public List<WebElement> findElements(SearchContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}     





}
