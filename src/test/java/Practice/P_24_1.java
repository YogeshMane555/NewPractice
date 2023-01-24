package Practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

public class P_24_1 {

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
		WebElement radio2=rm.findElement(By.xpath("//*[contains(@value,'radio2')]"));
	
		radio2.click();
		
		Thread.sleep(2000);
		
		boolean b=radio2.isSelected();
		
		System.out.println("Is Radio 2 button Selected ?" +b);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//radio224_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc2() throws InterruptedException
	{
		WebElement drpdwn=rm.findElement(By.xpath("//*[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option2");
		
		Thread.sleep(2000);
		
		boolean b2=drpdwn.isSelected();
		
		System.out.println("Is Dropdown Option2 value selected ?"+b2);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src1=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src1, new File("C://Users//Yogesh//git//NewPractice//Screenshot//DropDown24_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		WebElement chk=rm.findElement(By.xpath("//*[contains(@id,'checkBoxOption2')]"));
		
		chk.click();
		
		Thread.sleep(2000);
		
		Boolean b3=chk.isSelected();
		
		System.out.println("Is Checkbox2 Selected ?"+b3);
		
		TakesScreenshot tk=(TakesScreenshot)rm;
		
		File src4=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//Checkbox24_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc4() throws InterruptedException
	{
		WebElement aa=rm.findElement(By.xpath("//*[contains(@id,'name')]"));
		
		aa.sendKeys("Yogesh");
		
		Thread.sleep(2000);
		
		rm.findElement(By.xpath("//*[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		Alert a=rm.switchTo().alert();
		
		a.accept();
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		
		rm.quit();
	}
	
}
