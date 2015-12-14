package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage{
	private By searchField= By.id("main-search-box");
	
	private WebDriver driver;
	public MainPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public SearchPage search(String query){
		System.out.println("Searching: "+query);
		driver.findElement(searchField).sendKeys(query);
		driver.findElement(searchField).sendKeys(Keys.RETURN);
		System.out.println("Navigating to the search results");
		return new SearchPage(driver);
	}
}
