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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day1_6 {

	WebDriver km;
	
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yogesh\\git\\NewPractice\\NewChromeDriver\\chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delete-all-cookies");
		
		km=new ChromeDriver(co);
					
	}
	
	@BeforeMethod
	public void inti()
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
		List<WebElement> link=km.findElements(By.tagName("a"));
		
		int noLink=link.size();
		
		System.out.println("Number of Links :"+noLink);
		
		for(WebElement linkT:link)
		{
			String LinkText=linkT.getText();
			
			//System.out.println("Links are :"+LinkText);
			
			if(LinkText.contains(" "))
			{
				System.out.println("Broken Links :"+LinkText);
			}
		}
	}
	
	@Test
	public void tc2()
	{
		WebElement r2=km.findElement(By.xpath("//input[contains(@value,'radio2')]"));
		
		r2.click();
		
		boolean b=r2.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("RADIO2");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Uruguay";
		
		WebElement autoTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		autoTxt.sendKeys("Ru");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Autosuggestions are :"+noAuto);
		
		for(WebElement autoT:auto)
		{
			String text=autoT.getText();
		
			System.out.println("Autosuggestions are :"+text);
			
			if(text.contentEquals("Uruguay"))
			{
				autoT.click();
				
				String actual=autoTxt.getAttribute("value");
				
				System.out.println("Selected from Autosuggestions :"+actual);
				
				Assert.assertEquals(actual, expected);
				
				screenShot("AUTOSUGGESTION");
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String expected="option2";
				
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option2");
		
		String actual=drpdwn.getAttribute("value");
		
		Assert.assertEquals(actual, expected);
		
		screenShot("DROPDOWN");
	}
	
	@Test
	public void tc5()
	{
		WebElement ch2=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption2')]"));
	
		ch2.click();
		
		boolean b=ch2.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("CHECKBOX");
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expectd="https://www.qaclickacademy.com/lander?oref=https%3A%2F%2Frahulshettyacademy.com%2F";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String>ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(2000);
		
		String actual=km.getCurrentUrl();
		
		Assert.assertEquals(actual, expectd);
		
		screenShot("NEWTAB");
		
		km.switchTo().window(parent);
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement altTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		altTxt.sendKeys("KAJAL MANE");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		Thread.sleep(2000);
		
		altTxt.sendKeys("KAJAL JAGTAP");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();

		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
		
		Thread.sleep(2000);
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		WebElement dispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispTxt.sendKeys("YOGESH");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=dispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		screenShot("HIDE");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=dispTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		screenShot("SHOW");
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
		
		screenShot("ACTIONS");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
        JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1450)");
		
		Thread.sleep(2000);
		
		WebElement iframe=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(iframe);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,3000)");
		
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void tearDown()
	{
		km.quit();
	}
}
