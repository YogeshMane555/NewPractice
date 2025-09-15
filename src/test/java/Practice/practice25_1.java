package Practice;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

public class practice25_1 {

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
	
	@Test(priority=0)
	public void tc1()
	{
	
		List<WebElement> link= rm.findElements(By.tagName("a"));
		
		int l=link.size();
		
		System.out.println("URL count are :"+l);
		
		for(WebElement URL :link)
		{
			String URLName=URL.getText();
			
			System.out.println("URL name are :"+URLName);
		}
		
	}
	
	@Test(priority=1)
	public void tc2() throws InterruptedException
	{
		JavascriptExecutor j=(JavascriptExecutor)rm;
		
		j.executeScript("window.scrollBy(0,1200)");
		
		Thread.sleep(2000);
		
		WebElement hover=rm.findElement(By.xpath("//*[contains(@id,'mousehover')]"));
		
		Actions a=new Actions(rm);
		
		a.moveToElement(hover);
		
		a.build().perform();
		
		Thread.sleep(2000);
		
		rm.findElement(By.xpath("//*[contains(text(),'Top')]")).click();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//Actions_25_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=-1)
	public void tc3() throws InterruptedException
	{
		String Title=rm.getTitle();
		
		System.out.println("Parent Tab Title is :"+Title);
		
		rm.findElement(By.xpath("//*[contains(@id,'opentab')]")).click();
		
		Thread.sleep(2000);
		
		Set<String>s= rm.getWindowHandles();
		
		Iterator<String>ss= s.iterator();
		
		boolean b=ss.hasNext();
		
		Thread.sleep(2000);
		
		String NextPage=rm.getTitle();
		
		System.out.println("Child Tab Title is :"+NextPage);
		
		Thread.sleep(3000);
		
		TakesScreenshot tk=(TakesScreenshot)rm; 
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//NewTab25_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test(priority=0)
	public void tc4() throws InterruptedException
	{
		WebElement alrtbttn= rm.findElement(By.xpath("//*[contains(@id,'alertbtn')]"));
		
		org.openqa.selenium.Dimension d=alrtbttn.getSize();
		
		int height =d.height;
	
		System.out.println("Height of WebElement is :"+height);
		
		Thread.sleep(3000);
		
		int width=d.width;
		
		System.out.println("Width of WebElement is :"+width);
		
		String color=alrtbttn.getCssValue("background-color");
		
		System.out.println("Color of WebElement is :"+color);
		
		String fontSize=alrtbttn.getCssValue("font-size");
		
		System.out.println("Font Size is :"+fontSize);
		
		String padding=alrtbttn.getCssValue("padding");
		
		System.out.println("Padding of Text is :"+padding);
		
		org.openqa.selenium.Point p=alrtbttn.getLocation();
		
		int locationTop=p.x;
		
		System.out.println("Location from Top side :"+locationTop);
		
		int locationBottom=p.y;
	
		System.out.println("Location from Bottom side :"+locationBottom);
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
	}
	
}
