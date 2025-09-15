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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day8_9 {
	
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
		List<WebElement> tg=km.findElements(By.tagName("a"));
		
		int totalNumberofLinks=tg.size();
		
		System.out.println("Total Number of Links are :"+totalNumberofLinks);
		
		for(WebElement tgtx:tg)
		{
			String LnkText=tgtx.getText();
			
			System.out.println("Link are :"+LnkText);
		}
		
		String title=km.getTitle();
		
		System.out.println("Title of Current Webpage is :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("Url of Current Webpage is :"+url);
	}
	
	@Test
	public void tc2()
	{
		WebElement r3=km.findElement(By.xpath("//input[contains(@value,'radio3')]"));
		
		r3.click();
		
		boolean b2=r3.isSelected();
		
		System.out.println("Is Radio button 3 Selected ?:"+b2);
		
		TakesScreenshot tk2=(TakesScreenshot)km;
		
		File src2=tk2.getScreenshotAs(OutputType.FILE);
		
      	try {
			FileUtils.copyFile(src2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9RadioButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Us");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int autoSize=auto.size();
		
		System.out.println("Total number of Autosuggestions are :"+autoSize);
		
		for(WebElement autotxt:auto)
		{
			String txtx=autotxt.getText();
			
			if(txtx.contentEquals("Cyprus"))
			{
				autotxt.click();
			}
			
			System.out.println("Autosuggestion texts are :"+txtx);
		}
		
		TakesScreenshot tk3=(TakesScreenshot)km;
		
		File src3=tk3.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9AutoSuggestion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc4()
	{
		WebElement select=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(select);
		
		s.selectByVisibleText("Option3");
		
		TakesScreenshot tk4=(TakesScreenshot)km;
		
		File src4=tk4.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9DropDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc5()
	{
		WebElement c3=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption3')]"));
		
		c3.click();
		
		boolean b3=c3.isSelected();
		
		System.out.println("Is checkbox 3 selected ? :"+b3);
		
		TakesScreenshot tk5=(TakesScreenshot)km;
		
		File src5=tk5.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src5, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9Checkbox.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		WebElement textbox=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		textbox.sendKeys("YOGESH");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Alert a=km.switchTo().alert();
		
		a.accept();
		
		Thread.sleep(2000);
		
		textbox.clear();
		
		textbox.sendKeys("Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Alert aa=km.switchTo().alert();
		
		aa.dismiss();
		
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String>s= km.getWindowHandles();
		
		Iterator<String>ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String childUrl=km.getCurrentUrl();
		
		System.out.println("New Tab URL is :"+childUrl);
		
		String childTitle=km.getTitle();
		
		System.out.println("New Tab Title is :"+childTitle);
		
		JavascriptExecutor js7=(JavascriptExecutor)km;
		
		js7.executeScript("window.scrollBy(0,2000)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk7=(TakesScreenshot)km;
		
		File src7=tk7.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src7, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9NewTab.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		JavascriptExecutor js8=(JavascriptExecutor)km;
		
		js8.executeScript("window.scrollBy(0,700)");
		
		km.findElement(By.xpath("//input[contains(@id,'displayed-text')]")).sendKeys("YOGGIIII MAHARAJ");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9AfterClickingHidebutton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9AfterClickingShowButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js9=(JavascriptExecutor)km;
		
		js9.executeScript("window.scrollBy(0,1500)");
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
		Thread.sleep(2000);
		
		JavascriptExecutor js10=(JavascriptExecutor)km;
		
		js10.executeScript("window.scrollBy(0,700)");
		
		TakesScreenshot tk10=(TakesScreenshot)km;
		
		File src10=tk10.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9Frame.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js11=(JavascriptExecutor)km;
		
		js11.executeScript("window.scrollBy(0,1200)");
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions aaa=new Actions(km);
		
		aaa.click(mouseHover).build().perform();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk11=(TakesScreenshot)km;
		
		File src11=tk11.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src11, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9ActionsMouseHover.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		aaa.click(top).build().perform();
		
        TakesScreenshot tk12=(TakesScreenshot)km;
		
		File src12=tk12.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src12, new File("C://Users//Yogesh//git//NewPractice//Screenshot//8_9ActionsTop.png"));
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
