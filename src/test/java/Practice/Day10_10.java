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

public class Day10_10 {

	public WebDriver km;;
	
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
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		wait=new WebDriverWait(km, Duration.ofSeconds(10));
		
		
	}
	
	public String getScreenshot(String fileName)
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
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("Total Tags :"+noTag);
		
		for(WebElement tagt:tag)
		{
			String tagText=tagt.getText();
			
			System.out.println("Tags are :"+tagText);

		}
		
		String title=km.getTitle();
		
		System.out.println("Title of Current page :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL :"+url);
	}
	
	@Test
	public void tc2()
	{
		WebElement r3=km.findElement(By.xpath("//input[contains(@value,'radio3')]"));
		
		r3.click();
		
		boolean b=r3.isSelected();
		
		Assert.assertTrue(b, "RADIO 3 Selected:");
		
		wait.until(ExpectedConditions.elementToBeSelected(r3));
		
		getScreenshot("10_10Radio");
	}
	
	
	@Test
	public void tc3() throws InterruptedException
	{
		WebElement autotxx=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
				
		autotxx.sendKeys("Su");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("No of Autosuggestion :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autotxt=autot.getText();
			
			System.out.println("Autosuggestions are :"+autotxt);
			
			if(autotxt.contentEquals("Suriname"))
			{
				autot.click();
				
				wait.until(ExpectedConditions.textToBePresentInElementValue(autotxx,"Suriname"));
				
				getScreenshot("10_10AUTOSUGGESTION");
			}
		}
		
		System.out.println("Selected from Autosuggestion :"+autotxx.getAttribute("value"));
	}
	
	@Test
	public void tc4()
	{
		String expected="option3";
		
		WebElement select=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(select);
		
		s.selectByVisibleText("Option3");
		
		// Wait for the dropdown value to be 'option3' after selection
		wait.until(ExpectedConditions.attributeToBe(select, "value", "option3"));
	
		Assert.assertEquals(select.getAttribute("value"), expected);
		
		getScreenshot("10_10DROPDOWN");
	}
	
	@Test
	public void tc5()
	{
		WebElement c3=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption3')]"));
		
		c3.click();
		
		boolean b=c3.isSelected();
		
		Assert.assertTrue(b,"Checkbox 3 Selected :");
		
		wait.until(ExpectedConditions.elementToBeSelected(c3));
		
		getScreenshot("10_10CHECKBOX");
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
		
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1500)");
		
		Thread.sleep(2000);
		
		getScreenshot("10_10NEWTAB");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alrTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alrTxt.sendKeys("Navnath");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		Thread.sleep(2000);
		
		alrTxt.sendKeys("GURU");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
		
		Thread.sleep(2000);
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		
		WebElement eleTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		eleTxt.sendKeys("DURGA");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b8=eleTxt.isDisplayed();
		
		Assert.assertFalse(b8,"After Clicking on hide button Textbox Hidden :");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'show-textbox')]")));
		
		getScreenshot("10_10HIDE");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=eleTxt.isDisplayed();
		
		Assert.assertTrue(b2, "After Clicking on Show button Textbox Displayed :");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'show-textbox')]")));
		
		getScreenshot("10_10SHOW");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1100)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).click().build().perform();
		
		a.click(top).build().perform();
		
		getScreenshot("10_10ACTIONS");
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
		
		js2.executeScript("window.scrollBy(0,3500)");
		
		Thread.sleep(2000);
		
		getScreenshot("10_10FRAME");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
	
}