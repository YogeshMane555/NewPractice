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

public class Day18_4 {
	
	WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("Webdriver.chrome.driver", "C:\\Users\\Yogesh\\git\\NewPractice\\NewChromeDriver\\chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delete-all-ccokies");
		
		km=new ChromeDriver(co);
		
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
	
		int noTag=tg.size();
	
		System.out.println("Total Tag Numbers are :"+noTag);
		
		for(WebElement tgNo:tg)
		{
			String tagName=tgNo.getText();
			
			System.out.println("Tag are :"+tagName);
		}
	}
	
	@Test
	public void tc2()
	{
		WebElement r2=km.findElement(By.xpath("//input[contains(@value,'radio2')]"));
		
		r2.click();
		
		boolean b=r2.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("18_4RADIO");
		
	}

	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Trinidad and Tobago";
		
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		sugTxt.sendKeys("Ad");
		
		Thread.sleep(2000);
		
		List<WebElement> sug=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noSug=sug.size();
		
		System.out.println("Total Number of Autosuggestion are :"+noSug);
		
		for(WebElement sugT:sug)
		{
			String sugText=sugT.getText();
			
			System.out.println("Autosuggetion are :"+sugText);
			
			if(sugText.contentEquals("Trinidad and Tobago"));
			{
				sugT.click();
				
				String actual=sugTxt.getAttribute("value");
				
				System.out.println("Selected from Autosuggestion :"+actual);
				
				Assert.assertEquals(actual, expected);
				
				screenShot("18_4Autosuggestion");
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
	
		System.out.println("Selected from Dropdown :"+actual);

		Assert.assertEquals(actual, expected);
		
		screenShot("18_4Dropdown");
	}
	
	@Test
	public void tc5()
	{
		WebElement c2=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption2')]"));
		
		c2.click();
		
		boolean b=c2.isDisplayed();
		
		Assert.assertTrue(b);
		
		screenShot("18_4CheckBox");
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expected="https://www.dot-consulting.org/articles/foundations-of-modern-higher-education.html?psystem=PW&domain=www.qaclickacademy.com&oref=https%3A%2F%2Frahulshettyacademy.com%2F&trafficTarget=reseller";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String>ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(3000);
		
		String actual=km.getCurrentUrl();
		
		Assert.assertEquals(actual, expected);
		
		screenShot("18_4NewTab");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alTxt.sendKeys("Kajal Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		alTxt.sendKeys("Kajal Japtap");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8()
	{
		WebElement elDisp=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		elDisp.sendKeys("MAA KAALI");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=elDisp.isDisplayed();
		
		Assert.assertFalse(b);
		
		screenShot("18_4HDIE");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=elDisp.isDisplayed();
		
		Assert.assertTrue(b2);
		
		screenShot("18_4SHOW");
	}
	
	@Test
	public void tc9()
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1300)");
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).moveToElement(top).click(top).build().perform();
		
		screenShot("18_4Actions");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
