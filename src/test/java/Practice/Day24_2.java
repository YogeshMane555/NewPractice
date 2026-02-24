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

public class Day24_2 {

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
		
		int noTg=tg.size();
		
		System.out.println("Total Number of Tag :"+noTg);
		
		for(WebElement tgt:tg)
		{
			String tagTxt=tgt.getText();
			
			System.out.println("Tags are :"+tagTxt);
		}
	}
	
	@Test
	public void tc2()
	{
		WebElement r2=km.findElement(By.xpath("//input[contains(@value,'radio2')]"));
		
		r2.click();
		
		boolean b=r2.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("24_2Radio");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Madagascar";
		
		WebElement autoTxt= km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		autoTxt.sendKeys("As");
		
		Thread.sleep(2000);
		
		List<WebElement> auto =km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Total Number of Autosuggestion :"+noAuto);
		
		for(WebElement autoT:auto)
		{
			String autoText=autoT.getText();
			
			System.out.println("Autosuggestions are :"+autoText);
			
			if(autoText.contentEquals("Madagascar"))
			{
				autoT.click();
				
				String acutal=autoTxt.getAttribute("value");
				
				System.out.println("Selected Autosuggestions are :"+acutal);
				
				Assert.assertEquals(acutal, expected);
				
				screenShot("24_2Autosuggestion");
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
		
		screenShot("24_2Dropdown");
	}
	
	@Test
	public void tc5()
	{
		WebElement c2=km.findElement(By.xpath("//input[contains(@value,'option2')]"));
		
		c2.click();
		
		boolean b=c2.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("24_2Checkbox");
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expected="QAClick Academy - A Testing Academy to Learn, Earn and Shine";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s =km.getWindowHandles();
		
		Iterator<String> ss =s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(2000);
		
		String actual=km.getTitle();
		
		Assert.assertEquals(actual, expected);
		
		screenShot("24_2NewTab");
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
		WebElement dispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispTxt.sendKeys("Kaju");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=dispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		screenShot("24_2Hide");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=dispTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		screenShot("24_2Show");
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
		
		screenShot("24_2MouseHover");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
        JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1500)");
		
		WebElement frame= km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
		JavascriptExecutor js2=(JavascriptExecutor)km;
			
		js2.executeScript("window.scrollBy(0,1500)");
			
		Thread.sleep(2000);
		
		screenShot("24_2Frmae");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
