package Practice;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
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
		WebElement text=rm.findElement(By.xpath("//*[contains(@id,'autocomplete')]"));
		
		Boolean b1=text.isDisplayed();
		
		System.out.println("Is Textbox visible ? :" +b1);
		
		text.sendKeys("Ab");
		
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
	
	@Test
	public void ScrollDown() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)rm;
		
		js.executeScript("window.scrollBy(0,1200)");
		
		Thread.sleep(5000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//ScorllignDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	@Test
	public void dropdown() throws InterruptedException
	{
		WebElement drp=rm.findElement(By.xpath("//*[contains(@name,'dropdown-class-example')]"));
		
		Select s=new Select(drp);
		
		s.selectByIndex(3);
		
		Thread.sleep(5000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
	
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//SelectDropdown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void Checkbox() throws InterruptedException
	{
		WebElement chk3=rm.findElement(By.xpath("//*[contains(@id,'checkBoxOption3')]"));
	
		chk3.click();
		
		boolean b2=chk3.isSelected();
		
		System.out.println("Is Checkbox 3 selected ?" +b2);
		
		Thread.sleep(5000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//CheckboxSelected.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void OpenTab() throws InterruptedException
	{
		
		System.out.println(rm.getTitle());
		
		rm.findElement(By.xpath("//*[contains(text(),'Open Tab')]")).click();
		
		Thread.sleep(3000);
		
		Set<String> s= rm.getWindowHandles();
		
		Iterator<String>ss= s.iterator();
		
		ss.next();
		
		System.out.println(rm.getCurrentUrl());
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Admin//eclipse-workspace//NewPractice//Screenshot//OpenNewTab.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rm.switchTo().
		
	}
	
	@Test
	public void alertBox() throws InterruptedException
	{
		rm.findElement(By.xpath("//*[contains(@id,'name')]")).sendKeys("beyondwalls Digital");
		
		rm.findElement(By.xpath("//*[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(4000);
		
		Alert a=rm.switchTo().alert();
		
		a.accept();
		
		Thread.sleep(4000);
		
		rm.findElement(By.xpath("//*[contains(@id,'name')]")).sendKeys("SELL.do");
		
		rm.findElement(By.xpath("//*[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(4000);
		
		Alert a1=rm.switchTo().alert();
	
		a1.dismiss();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		rm.quit();
	}
	
}
