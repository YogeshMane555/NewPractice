package Practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day15_10 {

	public WebDriver km;
	
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//NewChromeDriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delete-all-cookies");
		
		km=new ChromeDriver(co);
		
		wait=new WebDriverWait(km, Duration.ofSeconds(10));
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	
	public String getScreenshot(String image)
	{
		TakesScreenshot tk=(TakesScreenshot)km;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//"+image+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	
	
	@Test
	public void tc1()
	{
		
		String exptectedTitle="Practice Page";
		
		String exptectedUrl="https://rahulshettyacademy.com/AutomationPractice/";
		
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("Total Tags :"+noTag);
		
		for(WebElement tagt:tag)
		{
			String tagtxt=tagt.getText();
			
			System.out.println("Tags are :"+tagtxt);
		}
		
		String title=km.getTitle();
		
		System.out.println("Title :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL :"+url);
		
		Assert.assertEquals(title, exptectedTitle);
		
		Assert.assertEquals(url, exptectedUrl);
	}
	
	
	@Test
	public void tc2()
	{
		WebElement r2=km.findElement(By.xpath("//input[contains(@value,'radio2')]"));
		
		r2.click();
		
		boolean b=r2.isDisplayed();
		
		Assert.assertTrue(b,"Radio 2 Selected");
		
		wait.until(ExpectedConditions.elementToBeSelected(r2));
		
		getScreenshot("15_10Radio");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String exptected="Madagascar";
		
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		sugTxt.sendKeys("As");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noauto=auto.size();
		
		System.out.println("Total Autosuggestions :"+noauto);
		
		for(WebElement autot:auto)
		{
			String autotxt=autot.getText();
			
			System.out.println("Autosuggestions are :"+autotxt);
			
			if(autotxt.contentEquals("Madagascar"))
			{
				autot.click();
				
				wait.until(ExpectedConditions.elementToBeClickable(sugTxt));
				
				getScreenshot("15_10Autosuggestion");
				
				String actual=sugTxt.getAttribute("value");
				
				System.out.println("Selected from Autosuggestions :"+actual);
				
				Assert.assertEquals(actual, exptected);
			}
		}
	}
	
	
	@Test
	public void tc4()
	{
		String epxted="option2";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option2");
		
		String ac=drpdwn.getAttribute("value");
		
		System.out.println("Selected from Dropdown:"+ac);
		
		getScreenshot("15_10DROPDOWN");
		
		Assert.assertEquals(ac, epxted);
		
	}
	
	@Test
	public void tc5()
	{
		WebElement c2=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption2')]"));
		
		c2.click();
		
		boolean b=c2.isSelected();
		
		Assert.assertTrue(b);
		
		wait.until(ExpectedConditions.elementToBeSelected(c2));
		
		getScreenshot("15_10CHECKBOX");
	}
	
	
	@Test
	public void tc6() throws InterruptedException
	{
		String exptectURL="https://www.qaclickacademy.com/";
		
		WebElement openTab=km.findElement(By.xpath("//a[contains(@id,'opentab')]"));
				
	    openTab.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(openTab));
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String> ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String actualURL=km.getCurrentUrl();
		
		Assert.assertEquals(actualURL, exptectURL);
		
		Thread.sleep(2000);
		
		getScreenshot("15_10NEWTAB");
	}
	
	@Test
	public void tc7()
	{
		WebElement alTxt=km.findElement(By.id("name"));
		
		alTxt.sendKeys("YOGESH");
		
		km.findElement(By.id("alertbtn")).click();
		
		km.switchTo().alert().accept();
		
		alTxt.sendKeys("MANE");
		
		km.findElement(By.id("confirmbtn")).click();
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,500)");
		
		WebElement elTxt=km.findElement(By.id("displayed-text"));
		
		elTxt.sendKeys("YOGESHSS");
		
		Thread.sleep(2000);
		
		km.findElement(By.id("hide-textbox")).click();

		Thread.sleep(2000);
		
		boolean b=elTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		getScreenshot("15_10HIDE");
		
		km.findElement(By.id("show-textbox")).click();
		
		Thread.sleep(2000);
		
		boolean b2=elTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		getScreenshot("15_10SHOW");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1100)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.id("mousehover"));
		
		WebElement reload=km.findElement(By.xpath("//a[contains(text(),'Reload')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).click().build().perform();
		
		a.click(reload).build().perform();
		
		getScreenshot("15_10ACTIONS");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
        JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.id("courses-iframe"));
		
		km.switchTo().frame(frame);
		
		JavascriptExecutor js2=(JavascriptExecutor)km;
			
		js2.executeScript("window.scrollBy(0,5000)");
			
		Thread.sleep(2000);
		
		getScreenshot("15_10FRAME");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
