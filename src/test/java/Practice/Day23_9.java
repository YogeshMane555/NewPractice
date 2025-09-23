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

public class Day23_9 {

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
		List<WebElement> tags=km.findElements(By.tagName("a"));
		
		int noTags=tags.size();
		
		System.out.println("Total Number of Tags :"+noTags);
		
		for(WebElement tagst:tags)
		{
			String tagText=tagst.getText();
			
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
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio2')]"));
		
		r1.click();
		
		boolean b1=r1.isSelected();
		
		System.out.println("RADIO BUTTON 2 Selected ?:"+b1);
		
		TakesScreenshot tk2=(TakesScreenshot)km;
		
		File src2=tk2.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9RadioButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Ye");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//div[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Total Number of Autosuggestions are :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autotxt=autot.getText();
			
			System.out.println("Autosuggestion list are :"+autotxt);
			
			if(autotxt.contentEquals("Yemen"))
			{
				autot.click();
				
				Thread.sleep(2000);
				
				TakesScreenshot tk3=(TakesScreenshot)km;
				
				File src3=tk3.getScreenshotAs(OutputType.FILE);
				
				try {
					FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9AUTOSUGGESTION.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void tc4() throws InterruptedException
	{
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option2");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk4=(TakesScreenshot)km;
		
		File src4=tk4.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9DROPDOWN.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc5() throws InterruptedException
	{
		WebElement c2=km.findElement(By.xpath("//input[contains(@value,'option2')]"));
		
		c2.click();
		
		boolean b2=c2.isSelected();
		
		System.out.println("CHECKBOX 2 SELECTED ?:"+b2);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk5=(TakesScreenshot)km;
		
		File src5=tk5.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src5, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9CHECKBOX.png"));
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
		
		String parent =ss.next();
		
		String child =ss.next();
		
		km.switchTo().window(child);
		
		JavascriptExecutor js6=(JavascriptExecutor)km;
		
		js6.executeScript("window.scrollBy(0,1500)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk6=(TakesScreenshot)km;
		
		File src6=tk6.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src6, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9NEWTAB.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alText=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alText.sendKeys("YOGESH");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Alert a=km.switchTo().alert();
		
		Thread.sleep(2000);
		
		a.accept();
		
		Thread.sleep(2000);
		
		alText.sendKeys("MANE");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Alert aa=km.switchTo().alert();
		
		aa.dismiss();
		
		Thread.sleep(2000);
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		JavascriptExecutor js8=(JavascriptExecutor)km;
		
		js8.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		
		WebElement text=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		text.sendKeys("KAJAL");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b8=text.isDisplayed();
		
		System.out.println("After Clicking on Hide button Text box displayed ?:"+b8);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9HIDETEXTBOX.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b9=text.isDisplayed();
		
		System.out.println("After Clicking on Show button Text box displayed ?:"+b9);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9SHOWTEXTBOX.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js10=(JavascriptExecutor)km;
		
		js10.executeScript("window.scrollBy(0,1100)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).build().perform();
		
		Thread.sleep(2000);
		
		a.click(top).build().perform();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9ACTIONS.png"));
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
		
		WebElement iframe=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(iframe);
		
        JavascriptExecutor js11=(JavascriptExecutor)km;
		
		js11.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk11=(TakesScreenshot)km;
		
		File src11=tk11.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src11, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9FRAME.png"));
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
