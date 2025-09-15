package Practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Pract27_1 {

	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//chromedriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("Incognito");
		
		rm=new ChromeDriver(co);
		
		rm.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		
	}
	
	@Test
	public void tc1() throws InterruptedException
	{
		JavascriptExecutor tk=(JavascriptExecutor)rm;
		
		tk.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(3000);
		
		TakesScreenshot tk1=(TakesScreenshot)rm;
		
		File src=tk1.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//WebTable27_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement td=rm.findElement(By.xpath("//*[contains(text(),'Master Selenium')]/parent::tr/preceding-sibling::tr[1]/td[2]"));
	
		String text=td.getText();
		
		System.out.println(text);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
	}
	
	
}
