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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Day22_1 {

	public WebDriver km;
	
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C://Users//Yogesh//git//NewPractice//NewChromeDriver//chromedriver.exe");
		
		ChromeOptions co=new ChromeOptions();
		
		co.addArguments("start-maximized");
		
		co.addArguments("delete-all-cookies");
		
		co.addArguments("-incognito");
		
		km=new ChromeDriver(co);
		
		km.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	
	public String takesScreenshot(String fileName)
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
	   List<WebElement> tg =km.findElements(By.tagName("a"));
   
	   int noTag=tg.size();
	   
	   System.out.println("Number of Tags are :"+noTag);
   
	   for(WebElement tgt:tg)
	   {
		   String tgTxt=tgt.getText();
		   
		   System.out.println("Tags are :"+tgTxt);
	   }
   }
	
	@Test
	public void tc2()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);
		
		takesScreenshot("12_2RadioButton");
	}
	
	@Test
	public void tc3()
	{
		String expected="option1";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option1");
		
		String actual=drpdwn.getAttribute("value");
		
		System.out.println("Selected Options is :"+actual);
		
		Assert.assertEquals(actual, expected);
		
		takesScreenshot("12_2Dropdown");
	}
	
	@Test
	public void tc4()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption1')]"));
		
		c1.click();
		
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b);
		
		takesScreenshot("12_2Checkbox");
	}
	
	@Test
	public void tc5() throws InterruptedException
	{
		WebElement altTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		altTxt.sendKeys("Yogesh");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
		
		Thread.sleep(2000);
		
		altTxt.sendKeys("Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expected="Honduras";
		
		WebElement autoSugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
				
	    autoSugTxt.sendKeys("As");
	    
	    Thread.sleep(2000);
	    
	    List<WebElement> autoTxt=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
	    
	    int noAuto=autoTxt.size();

	    System.out.println("Total Number of AutoSuggestions are :"+noAuto);
	    
	    for(WebElement auto:autoTxt)
	    {
	    	String AutosuggTxt=auto.getText();
	    	
	    	System.out.println("Autosuggestion are :"+AutosuggTxt);
	    	
	    	if(AutosuggTxt.contentEquals("Honduras"))
	    	{
	    		auto.click();
	    		
	    		String actual=autoSugTxt.getAttribute("value");
	    		
	    		Assert.assertEquals(actual, expected);
	    		
	    		takesScreenshot("12_2Autosuggestion");
	    	}
	    }
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		String expectedUrl="https://www.qaclickacademy.com/";
		
		km.findElement(By.xpath("//a[contains(text(),'Open Tab')]")).click();
		
		Set<String> s =km.getWindowHandles();
		
		Iterator<String> ss= s.iterator();
		
		String parent =ss.next();
		
		String child= ss.next();
		
		km.switchTo().window(child);
	
		Thread.sleep(2000);
		
		String actualUrl=km.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);
		
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,850)");
		
		takesScreenshot("12_2NewTab");
	
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		WebElement dispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispTxt.sendKeys("Kajal Mane");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=dispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		takesScreenshot("12_2HideTextBox");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=dispTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		takesScreenshot("12_2ShowTextBox");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,950)");
		
		Thread.sleep(2000);
		
		WebElement button=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(button).click().build().perform();
		
		a.click(top).build().perform();
		
		takesScreenshot("12_2Actions");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1300)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
		Thread.sleep(2000);
		
        JavascriptExecutor js1=(JavascriptExecutor)km;
		
		js1.executeScript("window.scrollBy(0,1300)");
		
		Thread.sleep(2000);
		
		takesScreenshot("12_2Iframe");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
	
}
