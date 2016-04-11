package Pages;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MainPage{
	private By searchField= By.id("main-search-box");
	private By pincode=By.id("verification-code");
	private boolean retry=true;
	private WebDriver driver;
	public MainPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public SearchPage search(String query) throws Exception{
		try {
			driver.findElement(searchField).sendKeys(query);
			System.out.println("Searching: "+query);
			driver.findElement(searchField).sendKeys(Keys.RETURN);
			System.out.println("Navigating to the search results");
			return new SearchPage(driver);
		} catch (NoSuchElementException e) {
			if(driver.findElement(pincode).isDisplayed()){
				System.out.println("Pin code was requested");
				Thread.sleep(3600000);
				String code=getCode();
				driver.findElement(pincode).sendKeys(code+Keys.ENTER);
			}else{
				System.out.println("Field wasn't found");
				if(retry){
					System.out.println("Re-trying the search");
					retry=false;
					search(query);
				}
				else{ 
					throw new Exception("Field can not be found in two different options, tool stoped");
				}				
			}
		}
		return null;
	}
	
	
	private String getCode() throws IOException{
		String cur=System.getProperty("user.home")+"\\code.txt";
		BufferedReader bufferedReader =new BufferedReader(
		        new InputStreamReader(
		                new FileInputStream(cur), "UTF-8"));
		 
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if(!line.isEmpty()){
				System.out.println("Introducing code: "+line);
				bufferedReader.close();
				return line;
			}
		}
		bufferedReader.close();
		return null;
	}
}
