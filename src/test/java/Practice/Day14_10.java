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

public class Day14_10 {
	
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
	
		wait=new WebDriverWait(km, Duration.ofSeconds(10));
		
		km=new ChromeDriver(co);
		
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
		String currentTitle="Practice Page";
		
		String urll="https://rahulshettyacademy.com/AutomationPractice/";
		
		List<WebElement> tag =km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("Total Number of Tags :"+noTag);
		
		for(WebElement tagt:tag)
		{
			String tagTxt=tagt.getText();
			
			System.out.println("Tags are :"+tagTxt);
		}
		
		String title=km.getTitle();
		
		System.out.println("Title :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL :"+url);
		
		Assert.assertEquals(title, currentTitle);
		
		Assert.assertEquals(url, urll);
	}
	
	
	
	@Test
	public void tc2() throws InterruptedException
	{
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		sugTxt.sendKeys("Ga");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Number of Autosuggestion :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autoTxt=autot.getText();
			
			System.out.println("Autosuggestion :"+autoTxt);
			
			if(autoTxt.contentEquals("Uganda"))
			{
				autot.click();
				
				wait.until(ExpectedConditions.textToBePresentInElementValue(sugTxt, "Uganda"));
				
				getScreenshot("14_10AutoSuggestion");
				
				String selectedAuto=sugTxt.getAttribute("value");
				
				System.out.println("Selected From Autosuggestion :"+selectedAuto);
			}
		}
	}
	
	@Test
	public void tc3()
	{
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option1");
		
		wait.until(ExpectedConditions.elementToBeClickable(drpdwn));
		
		getScreenshot("14_10DropDown");
	}
	
	
	
	@Test
	public void tc4()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b,"Radio 1 Selected");
		
		wait.until(ExpectedConditions.elementToBeSelected(r1));
		
		getScreenshot("14_10Radio");
		
	}
	
	
	
	@Test
	public void tc5()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@value,'option1')]"));
		
		c1.click();
		
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b, "Checkbox 1 Selected");
		
		wait.until(ExpectedConditions.elementToBeSelected(c1));
		
		getScreenshot("14_10Checkbox");
	}
	
	
	@Test
	public void tc6() throws InterruptedException
	{
		List<WebElement> aa= km.findElements(By.tagName("link"));
		
		String tile="QAClick Academy - A Testing Academy to Learn, Earn and Shine";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String>ss =s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String tie=km.getTitle();
		
		Assert.assertEquals(tile, tie);
		
		Thread.sleep(2000);
		
		getScreenshot("14_10NewTab");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		Thread.sleep(2000);
		
		alTxt.sendKeys("YOGESH");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		Thread.sleep(2000);
		
		alTxt.sendKeys("Mane");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
		
		Thread.sleep(2000);
	}
	
	@Test
	public void tc8()
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,500)");
		
		WebElement eleTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		eleTxt.sendKeys("MAA DURGA");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(eleTxt));
		
		boolean b=eleTxt.isDisplayed();
		
		Assert.assertFalse(b,"Hidden");
		
		getScreenshot("14_10Hidden");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		wait.until(ExpectedConditions.visibilityOf(eleTxt));
		
		boolean b2=eleTxt.isDisplayed();
		
		Assert.assertTrue(b2, "SHOW");
		
		getScreenshot("14_10SHOW");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1100)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.id("mousehover"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).click().build().perform();
		
		a.click(top).build().perform();
		
		getScreenshot("14_10Actions");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1400)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
		Thread.sleep(2000);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,3000)");
		
		getScreenshot("14_10Frame");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
