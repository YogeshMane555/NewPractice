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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Day12_1 {

	WebDriver km;
	
	@BeforeTest
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
	
	
	public String getScreenshot(String fileName)
	{
		TakesScreenshot tk=(TakesScreenshot)km;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C:\\Users\\Yogesh\\git\\NewPractice\\Screenshot\\"+fileName+".png"));
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
		
		int noSize=tg.size();
		
		System.out.println("Total Number of Tags :"+noSize);
		
		for(WebElement tgTxt:tg)
		{
			String Text=tgTxt.getText();
			
			System.out.println("Tags are :"+Text);
		}
	}
	
	@Test
	public void tc2()
	{		
		WebElement r1= km.findElement(By.xpath("//input[contains(text(),' Radio1\r\n"
				+ "                ')]"));
		
		r1.click();
		
		boolean b=r1.isSelected();
		
		Assert.assertTrue(b);

		getScreenshot("12_1_RADIOButton");
	
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		String expected="Singapore";
		
	    WebElement auto=km.findElement(By.xpath("//input[contains(@id,'autocomplete')]"));
	    
	    auto.sendKeys("Po");
	    
	    Thread.sleep(2000);
	    
	    List<WebElement> autot=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
	    
	    int noAuto=autot.size();
	    
	    System.out.println("Total Number of AutoSuggestion :"+noAuto);
	    
	    for(WebElement AutoTxt:autot)
	    {
	    	String Autosug=AutoTxt.getText();
	    	
	    	System.out.println("Autosuggestions are :"+Autosug);
	    	
	    	if(Autosug.contentEquals("Singapore"))
	    	{
	    		AutoTxt.click();
	    		
	    		String actual=auto.getAttribute("value");
	    		
	    		Assert.assertEquals(actual, expected);
	    		
	    		getScreenshot("12_1_AutoSuggestion");
	    	}
	    }
	}
	
	@Test
	public void tc4()
	{
		WebElement drp=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
	
		Select s=new Select(drp);
		
		s.selectByVisibleText("Option1");
		
		getScreenshot("12_1_Dropdown");
	}
	
	@Test
	public void tc5()
	{
		WebElement c1=km.findElement(By.xpath("//input[contains(@name,'checkBoxOption1')]"));
	
		c1.click();
	
		boolean b=c1.isSelected();
		
		Assert.assertTrue(b);
		
		getScreenshot("12_1_CheckBox");
			
	}
	
	@Test
	public void tc6()
	{
		String expTitle="QAClick Academy - A Testing Academy to Learn, Earn and Shine";
		
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String> s=km.getWindowHandles();
		
		Iterator<String>ss= s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		String actTitle=km.getTitle();
		
		Assert.assertEquals(expTitle, actTitle);
		
		getScreenshot("12_1_NewTab");
		
	}
	
	@Test
	public void tc7() throws InterruptedException
	{
		WebElement altTxt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		Thread.sleep(3000);
		
		altTxt.sendKeys("Gopal");
		
		Thread.sleep(3000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Thread.sleep(3000);
		
		km.switchTo().alert().accept();
		
		Thread.sleep(3000);
		
		altTxt.clear();
		
		Thread.sleep(3000);
		
		altTxt.sendKeys("Krishna");
		
		Thread.sleep(3000);
		
		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();
		
		Thread.sleep(3000);
		
		km.switchTo().alert().dismiss();
	}
	
	@Test
	public void tc8()
	{
		WebElement DispTxt=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		DispTxt.sendKeys("Krishna Gopal");
		
		km.findElement(By.id("hide-textbox")).click();
		
		boolean b=DispTxt.isDisplayed();
		
		Assert.assertFalse(b);
		
		getScreenshot("12_1-HideTextBox");
		
		km.findElement(By.id("show-textbox")).click();
		
		boolean b1=DispTxt.isDisplayed();
		
		Assert.assertTrue(b1);
		
		getScreenshot("12_1-ShowTextBox");
		
	}
	
	@Test
	public void tc9()
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,750)");
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions a=new Actions(km);
		
		a.moveToElement(mouseHover).build().perform();
		
		a.click(top).build().perform();
		
		getScreenshot("12_1_Mousehover");
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,950)");
		
		WebElement frame=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
	
		km.switchTo().frame(frame);
		
		Thread.sleep(2000);
		
		getScreenshot("12_1_IFrame");
	}
	
	@AfterTest
	public void tearDown()
	{
		km.quit();
	}
}
