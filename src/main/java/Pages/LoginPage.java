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
		String Puser=(System.getProperty("loginUser")),
		Ppass=(System.getProperty("loginPass"));
		driver.findElement(user).sendKeys(Puser);
		driver.findElement(pass).sendKeys(Ppass);
		driver.findElement(button).click();
		System.out.println("Navigating to the Main Page");
		return new MainPage(driver);
	}
}
