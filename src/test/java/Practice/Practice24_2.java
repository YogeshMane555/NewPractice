package Practice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Practice24_2 {

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
	public void tc1() throws InterruptedException
	{
		WebElement radio=rm.findElement(By.xpath("//*[contains(@value,'radio1')]"));
		
		radio.click();
		
		Thread.sleep(3000);
		
		boolean b=radio.isSelected();
		
		System.out.println("Is Radio button 1 selected ? :"+b);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//s1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc2() throws InterruptedException
	{
		WebElement text=rm.findElement(By.xpath("//*[contains(@id,'autocomplete')]"));
		
		text.sendKeys("Go");
		
		Thread.sleep(3000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//s2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> list= rm.findElements(By.xpath("//*[contains(@class,'ui-menu-item-wrapper')]"));
		
		int size=list.size();
	
		System.out.println("Total Autosuggestion list are :"+size);
		
		for(WebElement auto:list)
		{
			String textAuto=auto.getText();
		
			System.out.println(textAuto);
			
			if(textAuto.equalsIgnoreCase("Togo"))
			{
				auto.click();
			}
		}
		
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		
		JavascriptExecutor js=(JavascriptExecutor)rm;
		
		js.executeScript("window.scrollBy(0,1200)");
		
		Thread.sleep(3000);
		
		rm.findElement(By.xpath("//*[contains(@id,'mousehover')]")).click();
		
		Actions a=new Actions(rm);
		
		a.click();
		
		WebElement top =rm.findElement(By.xpath("//*[contains(text(),'Top')]"));
		
		a.click(top);
		
		a.build().perform();
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		rm.close();
	}
}
