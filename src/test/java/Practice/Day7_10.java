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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day7_10 {

	public WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//NewChromeDriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("deleteAllCookies");
		
		km=new ChromeDriver(co);
				
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	@Test
	public void tc1()
	{
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("No of Tags are :"+noTag);
		
		for(WebElement tagt:tag)
		{
			String tagtxt=tagt.getText();
			
			System.out.println("Tags are :"+tagtxt);
		}
		
		String title=km.getTitle();
		
		System.out.println("Tilte :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL :"+url);
	}
	
	
	@Test
	public void tc2()
	{
		WebElement r2=km.findElement(By.xpath("//input[contains(@value,'radio2')]"));
		
		r2.click();
		
		boolean b2=r2.isSelected();
		
		System.out.println("RADIO 2 Selected ?:"+b2);
		
		TakesScreenshot tk2=(TakesScreenshot)km;
		
		File sr2=tk2.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(sr2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10RADIO.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("As");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Number of Autosuggestions :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autotxt=autot.getText();
			
			System.out.println("Autosuggestion :"+autotxt);
			
			if(autotxt.contentEquals("Madagascar"))
			{
				autot.click();
				
				Thread.sleep(2000);
				
				TakesScreenshot tk3=(TakesScreenshot)km;
				
			    File src3= tk3.getScreenshotAs(OutputType.FILE);
			    
			    try {
					FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10AUTO.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		String autosuggSelected=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).getAttribute("value");
		
		System.out.println("Autosuggestion selected :"+autosuggSelected);
	}
	
	@Test
	public void tc4()
	{
		WebElement select=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(select);
		
		s.selectByVisibleText("Option2");
		
		TakesScreenshot tk4=(TakesScreenshot)km;
		
		File src4=tk4.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10DROPDOWN.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String selectedDropdown=select.getAttribute("value");
		
		System.out.println("Selected Dropdowm :"+selectedDropdown);
	}
	
	@Test
	public void tc5()
	{
		WebElement c2=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption2')]"));
		
		c2.click();
		
		boolean b2=c2.isSelected();
		
		System.out.println("Checkbox 2 Selected ?:"+b2);
		
		TakesScreenshot tk5=(TakesScreenshot)km;
		
		File src5=tk5.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src5, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10CHECKBOX.png"));
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
		
		String title=km.getTitle();
		
		System.out.println("Title of child window :"+title);
		
		TakesScreenshot tk6=(TakesScreenshot)km;
		
		File src6=tk6.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src6, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10NEWTAB.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alerTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alerTxt.sendKeys("YOGESH");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		Thread.sleep(2000);
		
		alerTxt.clear();
		
		Thread.sleep(2000);
		
		alerTxt.sendKeys("MANE");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
		
		Thread.sleep(2000);
	
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		JavascriptExecutor js8=(JavascriptExecutor)km;
		
		js8.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		
		WebElement tx=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		tx.sendKeys("YOGESH MANE");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=tx.isDisplayed();
		
		System.out.println("Textbox Displayed :?"+b);
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10HIDE.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=tx.isDisplayed();
		
		System.out.println("Textbox displayed after clicking show button :?"+b2);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10SHOW.png"));
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
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).moveToElement(top).click().build().perform();
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10ACTIONS.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
        JavascriptExecutor js9=(JavascriptExecutor)km;
		
		js9.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
		String titl=km.getTitle();
		
		System.out.println("Title of Iframe :"+titl);
		
		TakesScreenshot tk10=(TakesScreenshot)km;
		
		File src10=tk10.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//7_10IFRAME.png"));
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
