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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day19_6 {

	WebDriver km;
	
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//NewChromeDriver//chromedriver.exe");
	
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delete-all-cookies");
		
		km=new ChromeDriver(co);
	
	}
	
	@BeforeMethod
	public void ini()
	{
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	public String screenShot(String fileName)
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
		List<WebElement> tg=km.findElements(By.tagName("a"));	
	
		int noTg=tg.size();
		
		System.out.println("Number of Tags are :"+noTg);
		
		for(WebElement tgT:tg)
		{
			String Tags=tgT.getText();
			
			System.out.println("Tag Names are :"+Tags);
		}
		
	}
	
	@Test
	public void tc2()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
	
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("1_7RADIO");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Romania";
		
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		sugTxt.sendKeys("Man");
		
		Thread.sleep(3000);
		
		List<WebElement> sug=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int nosug=sug.size();
		
		System.out.println("Total Number of Autosuggestions are :"+nosug);
		
		for(WebElement sugT:sug)
		{
			String sugText=sugT.getText();
			
			System.out.println("Autosuggestions are :"+sugText);
			
			if(sugText.contains("Romania"))
			{
				sugT.click();
				
				String actual=sugTxt.getAttribute("value");
				
				Assert.assertEquals(actual, expected);
				
				screenShot("1_7AUTOSUGGESTION");
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String expected="option1";
		
		WebElement dropdown=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(dropdown);
		
		s.selectByVisibleText("Option1");
		
		String actual=dropdown.getAttribute("value");
		
		Assert.assertEquals(actual, expected);
		
		screenShot("1_7DROPDOWN");
	}
	
	@Test
	public void tc5()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption1')]"));
		
		c1.click();
		
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("1_7CHECKBOX");
	}
	
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expected="https://www.qaclickacademy.com/lander?oref=https%3A%2F%2Frahulshettyacademy.com%2F";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s =km.getWindowHandles();
		
		Iterator<String> ss =s.iterator();
		
		String parent= ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String actual=km.getCurrentUrl();
		
		Thread.sleep(2000);
		
		Assert.assertEquals(actual, expected);
		
		screenShot("1_7NEWTAB");
		
		km.switchTo().window(parent);
	}
	
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alTxt.sendKeys("Kajal Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		alTxt.sendKeys("Kajal Bhosale");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8()
	{
		WebElement dispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispTxt.sendKeys("Maa KAALI");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=dispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		screenShot("1_7HIDE");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b1=dispTxt.isDisplayed();
		
		Assert.assertTrue(b1);
		
		screenShot("1_7SHOW");
		
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,950)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).moveToElement(top).click(top).build().perform();
		
		screenShot("1_7ACTIONS");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,1500)");
		
		Thread.sleep(2000);
		
		screenShot("1_7FRAME");
	}
	
	@AfterClass
	public void tearDown()
	{
		km.quit();
	}
}
