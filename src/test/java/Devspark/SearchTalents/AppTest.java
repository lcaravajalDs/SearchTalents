package Devspark.SearchTalents;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.MainPage;
import Pages.ProfilePage;
import Pages.SearchPage;
import TestBase.BaseTest;

public class AppTest extends BaseTest 
{
	List<String> results; 
	@Test
	public void findPersons() throws IOException, InterruptedException{
		LoginPage login= new LoginPage(driver);
		MainPage mainPage = login.login("queteimporta_301@hotmail.com", "uno23456");
		SearchPage search = mainPage.search("Devspark");
		search.filterByPersons();
		results = search.getResults();
		while(results.size()<10){
			search.goToNextPage();
			results.addAll(search.getResults());
		}
	}
	@Test(dependsOnMethods="findPersons")
	public void NavigateProfiles() throws IOException{
		if(results.size()>=1){
			openNewTab();
			ProfilePage profile=new ProfilePage(driver);
			for (String string : results) {
				driver.navigate().to(string);
				profile.waitForSendMessageButton();
			}
			profile.addProfile();
		}
		else
			System.out.println("No result found");
	}
}