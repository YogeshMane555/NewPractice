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

public class Day24_9 {

	public WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//ChromeDriver//chromedriver.exe");
		
		km=new ChromeDriver();
		
		km.manage().window().maximize();
		
		km.manage().deleteAllCookies();
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	@Test
	public void tc1()
	{
		List<WebElement> tag =km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("No of Tags are :"+noTag);
		
		for(WebElement tagT:tag)
		{
			String tagText=tagT.getText();
			
			System.out.println("Tag Name are :"+tagText);
		}
		
		String url=km.getCurrentUrl();
		
		System.out.println("Current URL :"+url);
		
		String title=km.getTitle();
		
		System.out.println("Title is :"+title);
	}
	
	@Test
	public void tc2()
	{
		WebElement r3=km.findElement(By.xpath("//input[contains(@value,'radio3')]"));
		
		r3.click();
		
		boolean b=r3.isSelected();
		
		System.out.println("Radio button 3 Selected ?:"+b);
		
		TakesScreenshot tk2=(TakesScreenshot)km;
		
		File src2=tk2.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//24_9RADIOBUTTON.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Sa");
		
		Thread.sleep(2000);
		
		List<WebElement> auto =km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noauto=auto.size();
		
		System.out.println("No of Autosuggestion :"+noauto);
		
		for(WebElement autot:auto)
		{
			String autotx=autot.getText();
			
			System.out.println("Autosuggestion are :"+autotx);
			
			if(autotx.contentEquals("Saudi Arabia"))
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
		
		s.selectByVisibleText("Option3");
		
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
	public void tc5()
	{
		WebElement c3=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption3')]"));
		
		c3.click();
		
		boolean b5=c3.isSelected();
		
		System.out.println("Checkbox 2 Selected ?:"+b5);
		
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
		km.findElement(By.xpath("//button[contains(@id,'openwindow')]")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String> ss=s.iterator();
		
		String parent =ss.next();
		
		String child=ss.next();
		
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
		WebElement alertTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alertTxt.sendKeys("Yogesh");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Alert a=km.switchTo().alert();
		
		Thread.sleep(2000);
		
		a.accept();
		
		Thread.sleep(2000);
		
		alertTxt.sendKeys("MANE");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
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
		
		WebElement txt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		Thread.sleep(2000);
		
		txt.sendKeys("YOGESHHH");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b8=txt.isDisplayed();
		
		System.out.println("After clicking on Hide button :"+b8);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9HIDE.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b9=txt.isDisplayed();
		
		System.out.println("After Clicking on Show button :"+b9);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
				
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9SHOW.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
        JavascriptExecutor js8=(JavascriptExecutor)km;
		
		js8.executeScript("window.scrollBy(0,1100)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).build().perform();
		
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
        JavascriptExecutor js8=(JavascriptExecutor)km;
		
		js8.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
        JavascriptExecutor js9=(JavascriptExecutor)km;
		
		js9.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk10=(TakesScreenshot)km;
		
		File src10=tk10.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//23_9FRAME.png"));
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
