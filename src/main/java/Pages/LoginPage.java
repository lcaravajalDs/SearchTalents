package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage{

	private By user = By.id("session_key-login");
	private By pass = By.id("session_password-login");
	private By button = By.id("btn-primary");
	
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public MainPage login(String usernamer,String password){
		driver.findElement(user).sendKeys("queteimporta_301@hotmail.com");
		driver.findElement(pass).sendKeys("uno23456");
		driver.findElement(button).click();
		System.out.println("Navigating to the Main Page");
		return new MainPage(driver);
	}
}
