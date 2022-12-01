package Practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TotalNumberOfLink {

	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//chromedriver//chromedriver.exe");
	
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("incognito");
		
		co.addArguments("start-maximized");
		
		rm=new ChromeDriver(co);		
		
		rm.get("https://rahulshettyacademy.com/AutomationPractice/");
				
	}
	
	@Test
	public void tc1() throws InterruptedException
	{
		Thread.sleep(4000);
		
		List<WebElement> list= rm.findElements(By.tagName("a"));
		
		int size=list.size();
		
		Thread.sleep(4000);
	
		System.out.println("Total Number of Hyperlink present in Webpage :"+size);
		
		for(WebElement llist:list)
		{
			System.out.println("Hyperlink Text are as follows :"+llist.getText());
		}

	}
	
	@Test
	public void tc2() throws InterruptedException
	{
		Thread.sleep(4000);
		
		List<WebElement> imglist= rm.findElements(By.tagName("img"));
		
		int img=imglist.size();
		
		System.out.println("Total Number of Images are :"+img);
		
		for(WebElement limglist:imglist)
		{
			System.out.println("Images name are :"+limglist.getText());
		}
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
	}
	
	
}
