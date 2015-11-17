package TestBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseTest {
	public WebDriver driver= new FirefoxDriver();
	
	@BeforeTest
	public void ini(){
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("https://www.linkedin.com/uas/login");
	}
	@AfterTest
	public void before(){
		driver.close();
	}
	
	public void openNewTab(){
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"t");
		driver.switchTo().activeElement();
	}
	
}
