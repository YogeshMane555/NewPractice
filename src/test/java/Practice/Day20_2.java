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

import net.bytebuddy.asm.Advice.AssignReturned;

public class Day20_2 {

	public WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//NewChromeDriver//chromedriver.exe");
		
		ChromeOptions co= new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delete-all-cookies");
		
		km=new ChromeDriver(co);
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	
      public String screenshot(String fileName)
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
		
		int noTag=tg.size();
		
		System.out.println("Number of Tags :"+noTag);
		
		for(WebElement tgTxt:tg)
		{
			String TagText=tgTxt.getText();
			
			System.out.println("Tags are :"+TagText);
		}
	}
	
	@Test
	public void tc2() throws InterruptedException
	{
		String expected="Tunisia";
		
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
			
		sugTxt.sendKeys("Si");
		
		Thread.sleep(2000);
		
		List<WebElement> autoSug=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=autoSug.size();
		
		System.out.println("Total Number of Autosuggestions are :"+noAuto);
		
		for(WebElement autoSugTxt:autoSug)
		{
			String autoText=autoSugTxt.getText();
			
			System.out.println("Autosuggestions are :"+autoText);
			
			if(autoText.contentEquals("Tunisia"))
			{
				autoSugTxt.click();
				
				String actual=sugTxt.getAttribute("value");
				
				Assert.assertEquals(actual, expected);
				
				screenshot("20_2Autosuggestion");
			}
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="https://www.qaclickacademy.com/";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String>ss= s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String actual=km.getCurrentUrl();
		
		Assert.assertEquals(actual, expected);
		
		Thread.sleep(2000);
		
		screenshot("20_2NewTab");
	}
	
	@Test
	public void tc4()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);
		
		screenshot("20_2RadioButton");
	}
	
	@Test
	public void tc5()
	{
		String expected="option1";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option1");
		
		String actual=drpdwn.getAttribute("value");
		
		Assert.assertEquals(actual, expected);
		
		screenshot("20_2Dropdown");
	}
	
	@Test
	public void tc6()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption1')]"));
		
		c1.click();
		
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b);
		
		screenshot("20_2Checkbox");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alTxt.sendKeys("Yogesh");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		alTxt.sendKeys("Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8()
	{
		WebElement elDispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		elDispTxt.sendKeys("Yogesh Sunil Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=elDispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		screenshot("20_2Hide");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=elDispTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		screenshot("20_2Show");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1200)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).moveToElement(top).click(top).build().perform();
		
		screenshot("20_2Actions");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		screenshot("20_2IFrame");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
	
}
