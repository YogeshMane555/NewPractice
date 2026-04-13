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

public class Day13_4 {

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
	
	public String Screenshot(String fileName)
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
		
		System.out.println("Total Number of Tags :"+noTag);
		
		for(WebElement tgT:tg)
		{
			String tagName=tgT.getText();
			
			System.out.println("Tags are :"+tagName);
		}
	}
	
	@Test
	public void tc2()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);
		
		Screenshot("13_4RADIO");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Taiwan, Province of China";
		
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		sugTxt.sendKeys("Ch");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Total Autosuggestions are :"+noAuto);
		
		for(WebElement autoT:auto)
		{
			String autoText=autoT.getText();
			
			System.out.println("Autosuggestion are :"+autoText);
			
			if(autoText.contentEquals("Taiwan, Province of China"))
			{
				autoT.click();
				
				String actual=sugTxt.getAttribute("value");
				
				System.out.println("Selected from Autosuggestion :"+actual);
				
				Assert.assertEquals(actual, expected);
				
				Screenshot("13_4AUTOSUGGESTION");
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String expected="option1";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option1");
		
		String actual=drpdwn.getAttribute("value");
		
		Assert.assertEquals(actual, expected);
		
		Screenshot("13_4DROPDOWN");
	}
	
	@Test
	public void tc5()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption1')]"));
		
		c1.click();
		
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b);
		
		Screenshot("13_4CHECKBOX");
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expected="https://www.dot-consulting.org/articles/foundations-of-modern-higher-education.html?psystem=PW&domain=www.qaclickacademy.com&oref=https%3A%2F%2Frahulshettyacademy.com%2F&trafficTarget=reseller";
				
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s= km.getWindowHandles();
		
		Iterator<String> ss= s.iterator();
		
		String parent =ss.next();
		
		String child=ss.next();

		km.switchTo().window(child);
		
		Thread.sleep(3000);
		
		String actual=km.getCurrentUrl();
		
		Assert.assertEquals(actual, expected);
		
		Screenshot("13_4NEWTAB");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alTxt.sendKeys("Kajal");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		alTxt.sendKeys("Yogesh");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8()
	{
		WebElement dispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispTxt.sendKeys("MAA KAALI");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=dispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		Screenshot("13_4HIDE");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=dispTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		Screenshot("13_4SHOW");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1300)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).moveToElement(top).click(top).build().perform();
		
		Screenshot("13_4ACTIONS");
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
		
		js2.executeScript("window.scrollBy(0,3000)");
		
		Thread.sleep(2000);
		
		Screenshot("13_4IFRAME");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
	
}
