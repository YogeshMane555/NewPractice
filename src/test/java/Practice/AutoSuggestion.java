package Practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
		
		rm.findElement(By.xpath("//*[contains(@class,'gLFyf')]")).sendKeys("Disha");
		
		Thread.sleep(4000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
	
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//GoogleAutosuggestionList.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
		
	}
	
	
}
