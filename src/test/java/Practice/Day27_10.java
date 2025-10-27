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

public class Day27_10 {

	WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("--incognito");
		
		co.addArguments("delete-all-cookies");
		
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
		String exptectTitle="Practice Page";
		
		String exptectUrl="https://rahulshettyacademy.com/AutomationPractice/";
		
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int no=tag.size();
		
		System.out.println("Total no of Tags :"+no);
		
		for(WebElement tagt:tag)
		{
			String tagTxt=tagt.getText();
			
			System.out.println("Tags are :"+tagTxt);
		}
		
		String title=km.getTitle();
		
		System.out.println("Title is :"+title);
		
		String url=km.getCurrentUrl();
		
		System.out.println("URL is :"+url);
		
		Assert.assertEquals(title, exptectTitle);
		
		Assert.assertEquals(url, exptectUrl);
	}
	
	@Test
	public void tc2() 
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);
		
		getScreenshot("27_10RadioButton");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expectedTxt="Indonesia";
		
		WebElement sugTxt=km.findElement(By.id("autocomplete"));
		
		sugTxt.sendKeys("Ind");
		
		Thread.sleep(2000);
		
		List<WebElement> sugg =km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noSugg=sugg.size();
		
		System.out.println("Total Autosuggestion are :"+noSugg);
		
		for(WebElement suggTxt:sugg)
		{
			String AutoTxt=suggTxt.getText();
			
			System.out.println("Autosuggestion Text are :"+AutoTxt);
			
			if(AutoTxt.contentEquals("Indonesia"))
			{
				suggTxt.click();
				
				String actualTxt=sugTxt.getAttribute("value");
				
				Assert.assertEquals(actualTxt, expectedTxt);
				
				getScreenshot("27_10Autosuggestion");
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String exptectOption="option1";
		
		WebElement drpdwn=km.findElement(By.id("dropdown-class-example"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option1");
		
		String actualOption=drpdwn.getAttribute("value");
		
		Assert.assertEquals(actualOption, exptectOption);
		
		getScreenshot("27_10DropDown");
	}
	
	@Test
	public void tc5()
	{
		WebElement c1=km.findElement(By.id("checkBoxOption1"));
		
		c1.click();
		
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b);
		
		getScreenshot("27_10CheckBox");
	}
	
	
	@Test
	public void tc6() throws InterruptedException
	{
		
		String exptectTitlle="QAClick Academy - A Testing Academy to Learn, Earn and Shine";
	
		km.findElement(By.id("opentab")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String> ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String actualTitle=km.getTitle();
		
		Assert.assertEquals(actualTitle, exptectTitlle);
		
		Thread.sleep(2000);
		
		getScreenshot("27_10NEWTAB");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.id("name"));
		
		Thread.sleep(2000);
		
		alTxt.sendKeys("Yogesh");
		
		Thread.sleep(2000);
		
		km.findElement(By.id("alertbtn")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		Thread.sleep(2000);
		
		alTxt.clear();
		
		alTxt.sendKeys("Mane");
		
		Thread.sleep(2000);
		
		km.findElement(By.id("confirmbtn")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
		
		Thread.sleep(2000);
	}
	
	@Test
	public void tc8()
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,500)");
		
		WebElement disTxt=km.findElement(By.id("displayed-text"));
		
		disTxt.sendKeys("Maa Durga");
		
		km.findElement(By.id("hide-textbox")).click();
		
		boolean b=disTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		getScreenshot("27_10Hidden");
		
		km.findElement(By.id("show-textbox")).click();
		
		boolean b2=disTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		getScreenshot("27_10Show");
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
		
		getScreenshot("27_10Actions");
				
	}
	
	@Test
	public void tc10()
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1400)");
		
		WebElement frame=km.findElement(By.id("courses-iframe"));
		
		km.switchTo().frame(frame);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,5000)");
		
		getScreenshot("27_10Frame");
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
