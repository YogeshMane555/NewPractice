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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day6_10 {

	WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//NewChromeDriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("--incognito");
		
		co.addArguments("start-maximized");
		
		km=new ChromeDriver(co);
		
		km.manage().deleteAllCookies();
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	@Test
	public void tc1()
	{
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int noAuto=tag.size();
		
		System.out.println("Total Tags :"+noAuto);
		
		for(WebElement tagt:tag)
		{
			String tagtext=tagt.getText();
			
			System.out.println("Tags :"+tagtext);
		}
		
		String title=km.getTitle();
		
		System.out.println("Title :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL :"+url);
	}
	
	@Test
	public void tc2()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b1=r1.isSelected();
		
		System.out.println("Radio 1 Selected ?:"+b1);
		
		TakesScreenshot tk2=(TakesScreenshot)km;

		File src2=tk2.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10RADIO.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Ka");
		
		Thread.sleep(2000);
		
		List<WebElement>auto = km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Total Autosuggestion :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autotxt=autot.getText();
			
			System.out.println("Autosuggestion :"+autotxt);
			
			if(autotxt.contentEquals("Sri Lanka"))
			{
				autot.click();
				
				Thread.sleep(2000);
				
				TakesScreenshot tk3=(TakesScreenshot)km;
				
				File src3=tk3.getScreenshotAs(OutputType.FILE);
				
				try {
					FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10AUTOSUGGESTION.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void tc4()
	{
		WebElement select=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(select);
		
		s.selectByVisibleText("Option1");
		
		TakesScreenshot tk4=(TakesScreenshot)km;
		
		File src4=tk4.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10DROPDOWN.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc5()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption1')]"));
		
		c1.click();
		
		boolean b5=c1.isSelected();
		
		System.out.println("Checkbox 1 Selected ?:"+b5);
		
		TakesScreenshot tk5=(TakesScreenshot)km;
		
		File src5=tk5.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src5, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10CHECKBOX.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String> ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		JavascriptExecutor js6=(JavascriptExecutor)km;
		
		js6.executeScript("window.scrollBy(0,3000)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk6=(TakesScreenshot)km;
		
		File src6=tk6.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src6, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10NEWTAB.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alertText=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alertText.sendKeys("Yogesh");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		Alert a=km.switchTo().alert();
		
		Thread.sleep(2000);
		
		a.accept();
		
		Thread.sleep(2000);
		
		alertText.sendKeys("Mane");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		Alert aa=km.switchTo().alert();
		
		Thread.sleep(2000);
		
		aa.dismiss();
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		JavascriptExecutor js8=(JavascriptExecutor)km;
	
		js8.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		
		WebElement elmText=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		elmText.sendKeys("RAM");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		Thread.sleep(2000);
		
		boolean b=elmText.isDisplayed();
		
		System.out.println("Hidden :"+b);
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10HIDE.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		Thread.sleep(2000);
		
		boolean b2=elmText.isDisplayed();
		
		System.out.println("SHOW :"+b2);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10SHOW.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js9=(JavascriptExecutor)km;
		
		js9.executeScript("window.scrollBy(0,1100)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement reload=km.findElement(By.xpath("//a[contains(text(),'Reload')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).build().perform();
		
		a.click(reload).build().perform();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10ACTIONS.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
        JavascriptExecutor js10=(JavascriptExecutor)km;
		
		js10.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
        JavascriptExecutor js11=(JavascriptExecutor)km;
		
		js11.executeScript("window.scrollBy(0,3000)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk10=(TakesScreenshot)km;
		
		File src10=tk10.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//6_10FRAME.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
