package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Zoopla {

	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","C://Users//chromedriver//chromedriver.exe");
		
		rm=new ChromeDriver();
		
		rm.manage().window().maximize();
				
		rm.get("https://www.zoopla.co.uk");
	}
	
	@Test
	public void tc() throws InterruptedException
	{
		rm.findElement(By.xpath("//*[contains(@id,'downshift-0-input')]")).sendKeys("London");
		
		rm.findElement(By.xpath("//*[contains(@class,'x8jo56f')]")).click();
		
		Thread.sleep(3000);
	}
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
	}
	
}
