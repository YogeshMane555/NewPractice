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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day26_2 {

	public WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//NewChromeDriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delete-all-cookies");
		
		km=new ChromeDriver(co);
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	public String screenShotS(String fileName)
	{
		TakesScreenshot tk=(TakesScreenshot)km;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//"+fileName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
		
	}
	
	@Test
	public void tc1()
	{
		List<WebElement> tg =km.findElements(By.tagName("a"));
		
		int noTag=tg.size();
		
		System.out.println("Total Number of Tags :"+noTag);
		
		for(WebElement tgT:tg)
		{
			String tgTxt=tgT.getText();
			
			System.out.println("Tags are :"+tgTxt);
		}
	}
	
	@Test
	public void tc2()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio3')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);
		
		screenShotS("26_2Radio");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="South Georgia and the South Sandwich Islands";
		
		WebElement autoTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		autoTxt.sendKeys("Ge");
		
		Thread.sleep(2000);
		
		List<WebElement> auto =km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Total Number of Autosuggestion are :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autoText=autot.getText();
			
			System.out.println("Autosuggestions are :"+autoText);
			
			if(autoText.contentEquals("South Georgia and the South Sandwich Islands"))
			{
				autot.click();
				
				String actual=autoTxt.getAttribute("value");
				
				System.out.println("Selected from Autosuggestion :"+actual);
				
				Assert.assertEquals(actual, expected);
				
				screenShotS("26_2Autosuggestion");
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String expected="option3";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option3");
		
		String actual = drpdwn.getAttribute("value");
		
		System.out.println("Selected from Dropdown :"+actual);
		
		Assert.assertEquals(actual, expected);
		
		screenShotS("26_2Dropdown");
	}
	
	@Test
	public void tc5()
	{
		WebElement c3=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption3')]"));
		
		c3.click();
		
		boolean b=c3.isSelected();
		
		Assert.assertTrue(b);
		
		screenShotS("26_2Checkbox");
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expected="QAClick Academy - A Testing Academy to Learn, Earn and Shine";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s= km.getWindowHandles();
		
		Iterator<String>ss= s.iterator();
		
		String parent= ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(2000);
		
		String actual=km.getTitle();
		
		System.out.println("New Tab Title is :"+actual);
		
		Assert.assertEquals(actual, expected);
		
		screenShotS("26_2NewTab");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alTxt.sendKeys("Kalyani");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
	
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		alTxt.sendKeys("Mane");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8()
	{
		WebElement dispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispTxt.sendKeys("Yogesh Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=dispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		screenShotS("26_2Hide");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=dispTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		screenShotS("26_2Show");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1200)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions s=new Actions(km);
		
		s.moveToElement(mouseHover).moveToElement(top).click(top).build().perform();
		
		screenShotS("26_2Actions");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement ifrmae=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(ifrmae);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
