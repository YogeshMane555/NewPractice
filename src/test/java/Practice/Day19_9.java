package Practice;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day19_9 {

	public WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yogesh\\git\\NewPractice\\ChromeDriver\\chromedriver.exe");
		
		km=new ChromeDriver();
		
		km.manage().window().maximize();
		
		km.manage().deleteAllCookies();
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	@Test
	public void tc1()
	{
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("Total Number of Tags are:"+noTag);
		
		for(WebElement tagt:tag)
		{
			String tagText=tagt.getText();
			
			System.out.println("Tags are :"+tagText);
		}
		
		String title=km.getTitle();
		
		System.out.println("Title of Current WebPage is :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL of Current Webpage is :"+url);
	}
	
	@Test
	public void tc2()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b1=r1.isSelected();
		
		System.out.println("Radio Button 1 Selected ?:"+b1);
		
		TakesScreenshot tk2=(TakesScreenshot)km;
		
		File src2=tk2.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9RadioBUTTON.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Fe");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Total Number of Autosuggestions are :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autoTxt=autot.getText();
			
			System.out.println("Autosuggestio List are :"+autoTxt);
			
			if(autoTxt.contentEquals("Russian Federation"))
			{
				autot.click();
				
				Thread.sleep(2000);
			}
			
			TakesScreenshot tk3=(TakesScreenshot)km;
			
			File src3=tk3.getScreenshotAs(OutputType.FILE);
			
			try {
				FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9AutoSUGGESSION.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
	}
	
	@Test
	public void tc4()
	{
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option1");
		
		TakesScreenshot tk4=(TakesScreenshot)km;
		
		File src4=tk4.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9DROPDOWN.png"));
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
		
		System.out.println("CheckBox 1 Selected ?:"+b5);
		
		TakesScreenshot tk5=(TakesScreenshot)km;
		
		File src5=tk5.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src5, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9CHECKBOX.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s= km.getWindowHandles();
		
		Iterator<String> ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		JavascriptExecutor js6=(JavascriptExecutor)km;
		
		js6.executeScript("window.scrollBy(0,1500)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk6=(TakesScreenshot)km;
		
		File src6=tk6.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src6, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9NEWTAB.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
        JavascriptExecutor js6=(JavascriptExecutor)km;
		
		js6.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		
		WebElement dispText=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispText.sendKeys("YOGESHH");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		Thread.sleep(2000);
		
		boolean b7=dispText.isDisplayed();
		
		System.out.println("After Clicking Hide button Textbox Displayed ?"+b7);
		
		TakesScreenshot tk7=(TakesScreenshot)km;
		
		File src7=tk7.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src7, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9AfterClickingHide.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
        JavascriptExecutor js6=(JavascriptExecutor)km;
		
		js6.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frm=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frm);
		
        JavascriptExecutor js7=(JavascriptExecutor)km;
		
		js7.executeScript("window.scrollBy(0,3000)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9FRAME.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
        JavascriptExecutor js6=(JavascriptExecutor)km;
		
		js6.executeScript("window.scrollBy(0,1100)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top= km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).build().perform();
		
		a.click(top).build().perform();
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//19_9ACTIONS.png"));
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
