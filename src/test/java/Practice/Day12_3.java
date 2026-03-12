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

public class Day12_3 {
	
	WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yogesh\\git\\NewPractice\\NewChromeDriver\\chromedriver.exe");
		
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
		List<WebElement> tg =km.findElements(By.tagName("a"));
		
		int noTag=tg.size();
		
		System.out.println("Total Number of Tags are :"+noTag);
		
		for(WebElement tgT:tg)
		{
			String tagText=tgT.getText();
			
			System.out.println("Tags are :"+tagText);
		}
	}
	
	@Test
	public void tc2()
	{
		WebElement r3=km.findElement(By.xpath("//input[contains(@value,'radio3')]"));
		
		r3.click();
		
		boolean b3=r3.isSelected();
		
		Assert.assertTrue(b3);
		
		screenShot("12_4Radio");
		
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Turkey";
		
		WebElement sugTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		sugTxt.sendKeys("Ey");
		
		Thread.sleep(2000);
		
		List<WebElement> auto =km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Total Number of Autosuggestion :"+noAuto);
		
		for(WebElement autoT:auto)
		{
			String autoText=autoT.getText();
			
			System.out.println("Autosuggestions are :"+autoText);
			
			if(autoText.contentEquals("Turkey"))
			{
				autoT.click();
				
				String actual=sugTxt.getAttribute("value");
				
				System.out.println("Selected from Autosuggestion :"+actual);
				
				Assert.assertEquals(actual, expected);
				
				screenShot("12_4Autosuggestion");
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String expected="option3";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option3");
		
		String actual=drpdwn.getAttribute("value");
		
		Assert.assertEquals(actual, expected);
		
		screenShot("12_4DropDown");
	}
	
	@Test
	public void tc5()
	{
		WebElement c3=km.findElement(By.xpath("//input[contains(@value,'option3')]"));
		
		c3.click();
		
		boolean b=c3.isSelected();
		
		Assert.assertTrue(b);
		
		screenShot("12_4Checkbox");
	}
	
	@Test
	public void tc6()
	{
		String expected="https://www.dot-consulting.org/articles/foundations-of-modern-higher-education.html?psystem=PW&domain=www.qaclickacademy.com&oref=https%3A%2F%2Frahulshettyacademy.com%2F";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s= km.getWindowHandles();
		
		Iterator<String> ss=s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String actual=km.getCurrentUrl();
		
		//Assert.assertEquals(actual, expected);
		
		screenShot("12_4NewTab");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alTxt.sendKeys("Shiva");
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().accept();
				
		alTxt.sendKeys("SHIVA");
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(2000);
		
		km.switchTo().alert().dismiss();
				
	}
	
	@Test
	public void tc8()
	{
		WebElement dispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		dispTxt.sendKeys("DattaGuru");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=dispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		screenShot("12_4Hide");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		boolean b2=dispTxt.isDisplayed();
		
		Assert.assertTrue(b2);
		
		screenShot("12_4Show");
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1300)");
		
		Thread.sleep(2000);
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a= new Actions(km);
		
		a.moveToElement(mouseHover).moveToElement(top).click(top).build().perform();
		
		screenShot("12_4Actions");
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
		
		js2.executeScript("window.scrollBy(0,1700)");
		
		Thread.sleep(2000);
		
		screenShot("12_4Frame");
	}
	
	@AfterMethod
	public void teadDown()
	{
		km.quit();
	}
	
	
}
