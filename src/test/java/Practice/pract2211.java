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

public class pract2211 {

	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
	
		System.setProperty("webdriver.chrome.driver", "C://Users//chromedriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("incognito");
		
		rm=new ChromeDriver(co);
		
		rm.get("https://www.google.com/");
		
	}
	
	@Test
	public void tc() throws InterruptedException
	{
		rm.findElement(By.xpath("//*[contains(@name,'q')]")).sendKeys("automation");
		
		Thread.sleep(4000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//SuggestList.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> auto= rm.findElements(By.xpath("//*[contains(@class,'sbct')]"));
		
		int totallist=auto.size();
		
		System.out.println("Total Number of Auto suggestion list are:" +totallist);
		
		for(WebElement list:auto)
		{
			String ls=list.getText();
			
			System.out.println("Autosuggestion Test are :" + ls);
			
			if(ls.equalsIgnoreCase("automation testing interview questions"))
			{
				Thread.sleep(4000);
				
				list.click();
				
			}
		}
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
	}
}
