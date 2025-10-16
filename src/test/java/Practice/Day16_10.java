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

public class Day16_10 {

	WebDriver km;
	
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yogesh\\git\\NewPractice\\NewChromeDriver\\chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delet-all-cookies");
		
		km=new ChromeDriver(co);
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
		
	    wait=new WebDriverWait(km, Duration.ofSeconds(10));
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
		String titl="Practice Page";
		
		String url="https://rahulshettyacademy.com/AutomationPractice/";
		
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("Number of Tags :"+noTag);
		
		for(WebElement tagt:tag)
		{
			String tagtxt=tagt.getText();
			
			System.out.println("Tag are :"+tagtxt);
		}
		
		String currentTitle=km.getTitle();
		
		System.out.println("Current Title :"+currentTitle);
		
		String currentURL=km.getCurrentUrl();
		
		System.out.println("URL is :"+currentURL);
		
		Assert.assertEquals(titl, currentTitle);
		
		Assert.assertEquals(url, currentURL);
	}
	
	@Test
	public void tc2()
	{
		WebElement r3=km.findElement(By.xpath("//input[contains(@value,'radio3')]"));
		
		r3.click();
		
		boolean b=r3.isSelected();
		
		Assert.assertTrue(b, "Radio 3 Selected");
		
		wait.until(ExpectedConditions.elementToBeSelected(r3));
		
		getScreenshot("16_10Radio");
	}
	
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expTxt="Singapore";
		
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		sugTxt.sendKeys("Po");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Number of Autosuggestion :"+noAuto);
		
		for(WebElement autot:auto)
		{
			String autoTxt=autot.getText();
			
			System.out.println("AutoSuggestion :"+autoTxt);
			
			if(autoTxt.contentEquals("Singapore"))
			{
				autot.click();
				
				String actTxt=sugTxt.getAttribute("value");
				
				System.out.println("Selected from Autosuggestion :"+actTxt);
				
				Thread.sleep(2000);
				
				Assert.assertEquals(actTxt, expTxt);
				
				getScreenshot("16_10Autosuggstion");
	
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String expTxt="option3";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option3");
		
		wait.until(ExpectedConditions.elementToBeClickable(drpdwn));
		
		String actTxt=drpdwn.getAttribute("value");
		
		Assert.assertEquals(expTxt,actTxt);
		
		getScreenshot("16_10Dropdown");
	}
	
	@Test
	public void tc5()
	{
		WebElement c3=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption3')]"));
		
		c3.click();
		
		boolean b=c3.isSelected();
		
		Assert.assertTrue(b, "Chekcbox3 Selected");
		
		wait.until(ExpectedConditions.elementToBeSelected(c3));
		
		getScreenshot("16_10Checkbox");
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expTitle="QAClick Academy - A Testing Academy to Learn, Earn and Shine";
		
		km.findElement(By.id("opentab")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String> ss=s.iterator();
		
		String parent =ss.next();
		
		String child= ss.next();
		
		km.switchTo().window(child);
		
		String actTitle=km.getTitle();
		
		System.out.println("Title of Child Window :"+actTitle);
		
		Assert.assertEquals(actTitle, expTitle);
		
		Thread.sleep(2000);
		
		getScreenshot("16_10NewTab");
	}
	
	@Test
	public void tc7()
	{
		WebElement alTxt=km.findElement(By.id("name"));
		
		alTxt.sendKeys("Yogesh");
		
		km.findElement(By.id("alertbtn")).click();
		
		km.switchTo().alert().accept();
		
		alTxt.clear();
		
		alTxt.sendKeys("Mane");
		
		km.findElement(By.id("confirmbtn")).click();
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		
		WebElement elTxt=km.findElement(By.id("displayed-text"));
		
		elTxt.sendKeys("Yogesh");
		
		km.findElement(By.id("hide-textbox")).click();
		
		boolean b=elTxt.isDisplayed();
		
		Assert.assertFalse(b, "After Clicking Hide Button Textbox is Hidden");
		
		Thread.sleep(2000);
		
		getScreenshot("16_10Hide");
		
		km.findElement(By.id("show-textbox")).click();
		
		boolean b2=elTxt.isDisplayed();
		
		Assert.assertTrue(b2, "After Clicking Show button Textbox is Visible");
		
		Thread.sleep(2000);
		
		getScreenshot("16_10Show");
	}
	
	@Test
	public void tc9()
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1100)");
		
		WebElement mouseHover=km.findElement(By.id("mousehover"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).click().build().perform();
		
		a.click(top).build().perform();
		
		getScreenshot("16_10Actions");
	}
	
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1450)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.id("courses-iframe"));
		
		km.switchTo().frame(frame);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,5000)");
		
		Thread.sleep(2000);
		
		getScreenshot("16_10Frame");
		
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}

