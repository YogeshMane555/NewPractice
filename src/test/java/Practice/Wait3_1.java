package Practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Wait3_1{
	
	public WebDriver rm;
	
	@BeforeMethod
	public void setUp()
	{
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("incognito");
		
		co.addArguments("start-maximized");
		
		rm=new ChromeDriver(co);
		
		rm.get("https://rahulshettyacademy.com/AutomationPractice/");
				
	}
	

	@Test(priority=1)
	public void tc1()
	{
		WebElement radio3=rm.findElement(By.xpath("//*[contains(@value,'radio3')]"));
		
		radio3.click();
		
		WebDriverWait ww=new WebDriverWait(rm, Duration.ofSeconds(100));
		
		ww.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@value,'radio3')]")));
		
		boolean b=radio3.isSelected();
		
		System.out.println("Is Radio button3 Selected ? :" +b);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//eclipse-workspace//TDD//Screenshot//Radio3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc2() throws InterruptedException
	{
		WebElement autolist=rm.findElement(By.xpath("//*[contains(@id,'autocomplete')]"));
		
		autolist.sendKeys("YO");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=rm.findElements(By.xpath("//*[contains(@class,'ui-menu-item')]"));
	
		int a=auto.size();
		
		System.out.println("AutoSuggestion List are :"+a);
	
		for(WebElement autosuggestion:auto)
		{
			String Text=autosuggestion.getText();
			
			if(Text.equalsIgnoreCase("Mayotte"))
			{
				Thread.sleep(2000);
				
				autosuggestion.click();
			}
			
		}
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C:\\Users\\Yogesh\\git\\NewPractice\\Screenshot//AutosuggestionList.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void tc3() throws InterruptedException 
	{
		WebElement DropDown= rm.findElement(By.xpath("//*[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(DropDown);
		
		s.selectByVisibleText("Option3");
		
		Thread.sleep(3000);
		
		Boolean b1=DropDown.isSelected();
		
		System.out.println("Is Option3 Value Selected ?:"+b1);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//DropDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tc4() throws InterruptedException
	{
		WebElement checkbox=rm.findElement(By.xpath("//*[contains(@id,'checkBoxOption3')]"));
		
		checkbox.click();
		
		Thread.sleep(3000);
		
		Boolean b3=checkbox.isSelected();
		
		System.out.println("Is Checkbox 3 value Selected ?;"+b3);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//Checkbox.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tc5() throws InterruptedException
	{
		System.out.println("Current URL :"+rm.getCurrentUrl());
		
		WebElement opentab= rm.findElement(By.xpath("//*[contains(@id,'opentab')]"));
		
		Thread.sleep(3000);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
	
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//ParentPage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		opentab.click();
		
		Set<String> s= rm.getWindowHandles();
		
		Iterator<String>ss= s.iterator();
		
		ss.next();
		
		System.out.println("Current URL :"+rm.getCurrentUrl());
		
		Thread.sleep(3000);
		
		TakesScreenshot tk1=(TakesScreenshot)rm;
		
		File src1=tk1.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src1,new File("C://Users//Yogesh//git//NewPractice//Screenshot//ChildPage.png"));
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
