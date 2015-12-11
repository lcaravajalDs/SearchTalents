package Pages;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage{
	
	private By filterByPersons=By.cssSelector("#search-types a[data-li-vertical-type=people]");
	private By results=By.cssSelector(".search-results .title.main-headline");
	private By nextPage= By.cssSelector(".next a");
	
	private WebDriver driver;
	public SearchPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void filterByPersons(){
	  driver.findElement(filterByPersons).click();
	}
	public List<String> getResults() throws IOException{
		 List<String> profilesLinks= new ArrayList<String>();
		 List<WebElement> listedResults = driver.findElements(results);
		 for (WebElement webElement : listedResults) {
			 if(!readed(webElement.getText())){ //Validate if we ever visited
				 profilesLinks.add(webElement.getAttribute("href"));
			 }
		 }
		 return profilesLinks;
  }
	public void goToNextPage() throws InterruptedException {
		System.out.println("Navigating to next page");
		driver.findElement(nextPage).click();
		Thread.sleep(2000);
	}
	private Boolean readed(String plink) throws IOException{
		try{
			Boolean flag=false;;
			String cur=System.getProperty("user.home")+"\\Visitados.txt";
			System.out.println(cur);
			BufferedReader bufferedReader =new BufferedReader(
			        new InputStreamReader(
			                new FileInputStream(cur), "UTF-8"));
			 
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.equals(plink)){
					flag=true;
					System.out.println(plink+" was already visited");
				}
			}
			bufferedReader.close();
			return flag;
		}
		catch(FileNotFoundException ex){
			return false;
		}
	}
}
