package Practice;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day30_08 {
	
	public WebDriver km;
	
	@BeforeMethod
	public void setUp()
	{
	    System.setProperty("webdriver.chrome.driver", "C://Users//chromedriver//chromedriver.exe");
	    
	    km=new ChromeDriver();
	    
	    km.manage().window().maximize();
	    
	    km.manage().deleteAllCookies();
	    
	    km.get("https://rahulshettyacademy.com/AutomationPractice/");
		
	}
	
	@Test
	public void tc1() 
	{
		String currentUrl=km.getCurrentUrl();
		
		System.out.println("Current URL is :"+currentUrl);
		
		String title=km.getTitle();
		
		System.out.println("Title of Website is :"+title);
		
		String pageSource=km.getPageSource();
		
		System.out.println("Page Source is :"+pageSource);
		
	
		TakesScreenshot tk=(TakesScreenshot)km;
		
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8TC1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc2() throws InterruptedException
	{
		WebElement r1=km.findElement(By.xpath("//input[contains(@value,'radio1')]"));
		
		r1.click();
		
		Boolean b1=r1.isSelected();
		
		System.out.println("Is Radio1 button selected ?"+b1);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk2=(TakesScreenshot)km;
		
		File src2=tk2.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src2, new File("C:\\Users\\Yogesh\\git\\NewPractice\\Screenshot//30_8RadioButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc3() throws InterruptedException
	{
		WebElement drp=km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));
		
		Select s=new Select(drp);
		
		s.selectByVisibleText("Option1");
		
		Thread.sleep(2000);
		
		TakesScreenshot tk3=(TakesScreenshot)km;
		
		File src3=tk3.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8DropDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc4() throws InterruptedException
	{
		WebElement chckbx1=km.findElement(By.xpath("//input[contains(@value,'option1')]"));
		
		chckbx1.click();
		
		boolean b4=chckbx1.isSelected();
		
		System.out.println("Is Checkbox1 selected ?:"+b4);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk4=(TakesScreenshot)km;
		
		File src4=tk4.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8CheckBox.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc5() throws InterruptedException
	{
		WebElement txt=km.findElement(By.xpath("//input[contains(@id,'name')]"));
		
		txt.sendKeys("Yogesh");
		
		Thread.sleep(3000);
		
		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();
		
		Alert a=km.switchTo().alert();
		
		a.accept();
		
		Thread.sleep(2000);
		
		txt.sendKeys("MANE");
		
		Thread.sleep(3000);
		
		Alert ab=km.switchTo().alert();
		
		ab.accept();
	}
	
	@Test
	public void tc6() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)km;
		
		js.executeScript("window.scrollBy(0,90)");
		
		Thread.sleep(2000);
		
		WebElement txbx=km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));
		
		txbx.sendKeys("YOGESHH");
		
		Thread.sleep(2000);
		
		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();
		
		Boolean b6=txbx.isDisplayed();
		
		System.out.println("After clicking on Hide button is Textbox displayed?:"+b6);
		
		Thread.sleep(2000);
		
		TakesScreenshot tk6=(TakesScreenshot)km;
		
		File src6=tk6.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src6, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8Hide.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();
		
		Boolean b7=txbx.isDisplayed();
		
		System.out.println("After clickng on Show button is Textbox displayed?:"+b7);
		
		TakesScreenshot tk7=(TakesScreenshot)km;
		
		File src7=tk7.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src7, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8Show.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc8()
	{
		List<WebElement> tagName =km.findElements(By.tagName("a"));
		
		int size=tagName.size();
		
		System.out.println("Number of Tags are :"+size);
		
		for (WebElement textTagName : tagName) {
			
			String txt=textTagName.getText();
			
			System.out.println("Link Name are :"+txt);
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		JavascriptExecutor js9=(JavascriptExecutor)km;
		
		js9.executeScript("window.scrollBy(0,1500)");
		
		Thread.sleep(2000);
		
		WebElement frm=km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frm);
		
		Thread.sleep(2000);
		
		JavascriptExecutor js10=(JavascriptExecutor)km;
		
		js10.executeScript("window.scrollBy(0,1500)");
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8Frame.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("In");
		
		Thread.sleep(2000);
		
		List<WebElement> txt=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int totalNumber=txt.size();
		
		System.out.println("Number of Autosuggestions :"+totalNumber);
		
		for(WebElement auto:txt) {
			
			String autotxt=auto.getText();
			
			System.out.println("Autosuggestions are :"+autotxt);
			
			if(autotxt.contentEquals("India"));
			{
					
				String selectedText=auto.getText();
				
				auto.click();
				
				System.out.println("Selected Autosuggestion Text :"+selectedText);
			}
			
		}
		
		TakesScreenshot tk10=(TakesScreenshot)km;
		
		File src10=tk10.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8Autosuggestion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc11() throws InterruptedException
	{
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
		
		Set<String>s= km.getWindowHandles();	
		
		Iterator<String> i=s.iterator();
		
		String parent=i.next();
		
		String child=i.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(2000);
		
		JavascriptExecutor js11=(JavascriptExecutor)km;
		
		js11.executeScript("window.scrollBy(0,1600)");
		
		String newTitleName=km.getTitle();
		
		System.out.println("New Tab title is :"+newTitleName);
		
		TakesScreenshot tk11=(TakesScreenshot)km;
		
		File src11=tk11.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src11, new File("C://Users//Yogesh//git//NewPractice//Screenshot//30_8NewTab.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc12() throws InterruptedException
	{
		JavascriptExecutor js12=(JavascriptExecutor)km;
		
		js12.executeScript("window.scrollBy(0,1300)");
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		Actions aa=new Actions(km);
		
		aa.click(mouseHover);
		
		aa.click(top).build().perform();
		
		Thread.sleep(2000);
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		km.quit();
	}

}
