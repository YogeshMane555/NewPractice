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

public class Day18_9 {

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
		
		System.out.println("Total Number of Tags :"+noTag);
		
		for(WebElement tagt:tag)
		{
			String tagText=tagt.getText();
			
			System.out.println("Tags are :"+tagText);
		}
		
		String title=km.getTitle();
		
		System.out.println("Title of Current Title is :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL of Current URL is :"+url);
	}
	
	@Test
	public void tc2()
	{
		WebElement r3=km.findElement(By.xpath("//input[contains(@value,'radio3')]"));
		
		r3.click();
		
		boolean b2=r3.isSelected();
		
		System.out.println("Radio Button 3 Selected ?:"+b2);
		
		TakesScreenshot tk2=(TakesScreenshot)km;
		
		File src2=tk2.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9RADIOAButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Sh");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int autoNo=auto.size();
		
		System.out.println("Total Number of Autosuggestions are :"+autoNo);
		
		for(WebElement autot:auto)
		{
			String autotxt=autot.getText();
			
			System.out.println("Autosuggest Lists are :"+autotxt);
			
			if(autotxt.contentEquals("Virgin Islands (British)"))
			{
				autot.click();
				
				Thread.sleep(2000);
				
				TakesScreenshot tk3=(TakesScreenshot)km;
				
				File src3=tk3.getScreenshotAs(OutputType.FILE);
				
				try {
					FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9AUTOSUGGESTION.png"));
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
		
		s.selectByVisibleText("Option3");
		
		TakesScreenshot tk4=(TakesScreenshot)km;
		
		File src4=tk4.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9DROPDOWN.png"));
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
		
		System.out.println("Checkbox 3 Selected ?:"+b3);
		
		TakesScreenshot tk5=(TakesScreenshot)km;
		
		File src5=tk5.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src5, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9CHECKBOX.png"));
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
		
		Iterator<String>ss= s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(2000);
		
		JavascriptExecutor js6=(JavascriptExecutor)km;
		
		js6.executeScript("window.scrollBy(0,1500)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk6=(TakesScreenshot)km;
		
		File src6=tk6.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src6, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9NEWTAB.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alertTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alertTxt.sendKeys("YOGESHHHH");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		Alert a=km.switchTo().alert();
		
		Thread.sleep(2000);
		
		a.accept();
		
		Thread.sleep(2000);
		
		alertTxt.sendKeys("MANEEEE");
		
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
		
		WebElement dispText=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispText.sendKeys("RAHUL SHETTY");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b8=dispText.isDisplayed();
		
		System.out.println("After Clicking on Hide button TextBox Displayed ?:"+b8);
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9AfterClickingHIDEButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b9=dispText.isDisplayed();
		
		System.out.println("After Clicking on Show button TextBox Displayed ?:"+b9);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9AfterClickingSHOWButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js9=(JavascriptExecutor)km;
		
		js9.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frm=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frm);
		
		Thread.sleep(2000);
		
        JavascriptExecutor js10=(JavascriptExecutor)km;
		
		js10.executeScript("window.scrollBy(0,2000)");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9FRAME.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc10() throws InterruptedException, IOException
	{
		JavascriptExecutor js11=(JavascriptExecutor)km;
		
		js11.executeScript("window.scrollBy(0,1200)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions aaa=new Actions(km);
		
		aaa.moveToElement(mouseHover).build().perform();
		
		aaa.click(top).build().perform();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk10=(TakesScreenshot)km;
		
		File src10= tk10.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//18_9ACTIONS.png"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
	
}
