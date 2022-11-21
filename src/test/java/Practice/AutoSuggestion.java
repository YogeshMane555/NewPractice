package Practice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutoSuggestion {

	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
		
		System.setProperty("webdriver.chrome.driver", "C://Users//chromedriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
	    co.addArguments("start-maximized");
		
		co.addArguments("Incognito");
		
		//co.addArguments("--headless");
		
		rm=new ChromeDriver(co);
		
		rm.get("https://www.google.com/");
	
				
	}
	
	@Test
	public void tc1() throws InterruptedException
	{
		Thread.sleep(4000);
		
		System.out.println(rm.getCurrentUrl());
		
		System.out.println(rm.getTitle());
		
		rm.findElement(By.xpath("//*[contains(@class,'gLFyf')]")).sendKeys("Dubai");
		
		Thread.sleep(4000);
		
		List<WebElement> le= rm.findElements(By.xpath("//*[contains(@class,'sbct')]"));
		
	
		int TotalNumber=le.size();
		
		System.out.println("Total number of Autosuggestions are :" +TotalNumber);

		Thread.sleep(3000);
		
		for(WebElement ele:le)
		{
			
			String Text= ele.getText();
			
			System.out.println(Text);
			
			if(Text.equalsIgnoreCase("dubai time right now"))
			{
				Thread.sleep(3000);
				
				TakesScreenshot tk=(TakesScreenshot)rm;
				
				File src=tk.getScreenshotAs(OutputType.FILE);
			
				try {
					FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//GoogleAutosuggestionList.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				ele.click();
				
				break;
			}
			
		}
		
		}
	
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
		
	}
	
	
}
