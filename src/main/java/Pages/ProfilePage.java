package Pages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage{
	private By sendMessageButton= By.id("tc-actions-send-message");
	private By fullName=By.className("full-name");
	List<String> visited= new ArrayList<String>();
	private WebDriver driver;
	public ProfilePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void waitForSendMessageButton(){
		driver.findElement(sendMessageButton);
		String name=driver.findElement(fullName).getText();
		System.out.println("Visiting "+name+" profile");
		visited.add(name);
	}
	
	public void addProfile() throws IOException{
		try{
			String cur=System.getProperty("user.dir")+"\\src\\main\\resources\\Visitados.txt";
			List<String> backup= new ArrayList<String>();
			BufferedReader bufferedReader =new BufferedReader(
			        new InputStreamReader(
			                new FileInputStream(cur), "UTF-8"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				backup.add(line);
			}
			bufferedReader.close();
			System.out.println("Backup finished");
			backup.addAll(visited);
			Writer writer = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(cur), "UTF-8"));
			for (String string : backup) {
				writer.append(string);
			    writer.append('\n');
			}
		    writer.flush();
		    writer.close();
			System.out.println("Profiles visited save successfully");
		}
		catch(Exception ex){
			System.out.println("Profiles can't be saved, somehting went wrong");
			System.out.println(ex.getMessage());
		}

	}
}
