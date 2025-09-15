package Practice;

import java.io.File;
import java.io.IOException;

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

public class Practice13_2 {

	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","C://Users//chromedriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("incognito");
		
		co.addArguments("start-maximized");
		
		rm=new ChromeDriver(co);
		
		rm.get("https://rahulshettyacademy.com/AutomationPractice/");
		
	}
	
	
	@Test
	public void tc1()
	{
		String url=rm.getCurrentUrl();
		
		System.out.println(url);
		
		String title=rm.getTitle();
	
		System.out.println(title);
		
		
	}
	
	@Test
	public void tc2() throws InterruptedException
	{
		WebElement r3=rm.findElement(By.xpath("//*[contains(@value,'radio3')]"));
	
		r3.click();
		
		Thread.sleep(3000);
		
		boolean b=r3.isSelected();
	
		System.out.println("Is Radio 3 button selected ?"+b);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
	
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//P13_2_Radio3.png"));
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
