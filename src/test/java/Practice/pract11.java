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

public class pract11 {

	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//chromedriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("incognito");
		
		rm=new ChromeDriver(co);
		
		rm.get("https://rahulshettyacademy.com/AutomationPractice/");

	}
	
	@Test
	public void tc1SS() throws InterruptedException
	{
		Thread.sleep(4000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//TakenScreenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void tc2() throws InterruptedException
	{
		WebElement radio3= rm.findElement(By.xpath("//*[contains(@value,'radio3')]"));
		
		radio3.click();
		
		Thread.sleep(3000);
		
		radio3.click();
		
		boolean b=radio3.isSelected();
		
		System.out.println("Is radio button 3 selected? :"+ b);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//RadioButtonScreenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		rm.findElement(By.xpath("//*[contains(@id,'autocomplete')]")).sendKeys("Ab");
		
		Thread.sleep(4000);
		
	 	List<WebElement> autosuggestion= rm.findElements(By.xpath("//*[contains(@class,'ui-menu-item')]"));
	 	
	 	int list=autosuggestion.size();
	 	
	 	System.out.println("Total number of autosuggestions are :" +list);

	 	TakesScreenshot tk=(TakesScreenshot)rm;
	 	
	 	File src=tk.getScreenshotAs(OutputType.FILE);
	 	
	 	try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//Autosuggestion.png"));
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
