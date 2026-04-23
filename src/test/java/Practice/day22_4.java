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

public class day22_4 {
	
	public WebDriver km;
	
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

	@Test
	public void tc1()
	{
		List<WebElement> tag=km.findElements(By.tagName("a"));
		
		int noTag=tag.size();
		
		System.out.println("Total Number of Tags :"+noTag);
		
		for(WebElement tagT:tag)
		{
			String tagName=tagT.getText();
		
			System.out.println("Tags are :"+tagName);
		}
		
	}
	
	public String takeScreenshot(String fileName)
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
	public void tc2()
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);
		
		takeScreenshot("22_4RADIO");
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Singapore";
		
		WebElement autoTxt=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
		
		autoTxt.sendKeys("Po");
		
		Thread.sleep(2000);
		
		List<WebElement> sugTxt=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=sugTxt.size();
		
		System.out.println("Autosuggestion are :"+noAuto);
		
		for(WebElement txt:sugTxt)
		{
			String AutoText=txt.getText();
			
			System.out.println("Autosuggestion are :"+AutoText);
			
			if(AutoText.contentEquals("Singapore"))
			{
				txt.click();
				
				String actual=autoTxt.getAttribute("value");
				
				System.out.println("Selected from AutoSuggestion :"+actual);
				
				Assert.assertEquals(actual, expected);
				
				takeScreenshot("22_4AutoSuggestion");
			}
		}
	}
	
	@Test
	public void tc4()
	{
		String expected="option1";
		
		WebElement drpdwn=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drpdwn);
		
		s.selectByVisibleText("Option1");
		
		String actual=drpdwn.getAttribute("value");
		
		Assert.assertEquals(actual, expected);
		
		takeScreenshot("22_4DropDOWN");
		
	}
	
	@Test
	public void tc5()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@id,'checkBoxOption1')]"));
		
		c1.click();
		
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b);
		
		takeScreenshot("22_4CHECKbox");
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		String expected="Foundations of Modern Higher Education | .Consulting";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s =km.getWindowHandles();

		Iterator<String> ss= s.iterator();
		
		String parent =ss.next();
		
		String child= ss.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(3000);
		
		String actual=km.getTitle();
	
		System.out.println("Newly opened Title Name is :"+actual);
		
		Assert.assertEquals(actual, expected);
		
		takeScreenshot("22_4NEWtab");
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement alTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		alTxt.sendKeys("Kaju Katli");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		km.switchTo().alert().accept();
		
		alTxt.sendKeys("Yogi");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		km.switchTo().alert().dismiss();
		
	}
	
	@Test
	public void tc8()
	{
		WebElement elDisp=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		elDisp.sendKeys("Maa DURGA");
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		boolean b=elDisp.isDisplayed();
		
		Assert.assertFalse(b);
		
		takeScreenshot("22_4HIDE");
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		elDisp.click();
		
		boolean b2=elDisp.isDisplayed();
	
		Assert.assertTrue(b2);
		
		takeScreenshot("22_4SHOW");
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
		
		takeScreenshot("22_4ACtions");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,1450)");
		
		Thread.sleep(2000);
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
        JavascriptExecutor js2=(JavascriptExecutor)km;
		
		js2.executeScript("window.scrollBy(0,2500)");
		
		Thread.sleep(2000);
		
		takeScreenshot("22_4IframE");
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}
}
