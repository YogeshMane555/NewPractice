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

public class Day3_9 {

	public WebDriver km;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C://Users//Yogesh//git//NewPractice//ChromeDriver//chromedriver.exe");

		km = new ChromeDriver();

		km.manage().window().maximize();

		km.manage().deleteAllCookies();

		km.get("https://rahulshettyacademy.com/AutomationPractice/");

	}

	@Test
	public void tc1() {
		List<WebElement> link = km.findElements(By.tagName("a"));

		int noLink = link.size();

		System.out.println("Number of Links are :" + noLink);

		for (WebElement txt : link) {
			String linkText = txt.getText();

			System.out.println("URL is :" + linkText);
		}

	}

	@Test
	public void tc2() {
		WebElement r1 = km.findElement(By.xpath("//input[contains(@value,'radio2')]"));

		r1.click();

		boolean b1 = r1.isSelected();

		System.out.println("Is Radio button 2 selected ?" + b1);

		TakesScreenshot tk1 = (TakesScreenshot) km;

		File src1 = tk1.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src1, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9RadioButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc3() {
		WebElement drpdwn = km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));

		Select s = new Select(drpdwn);

		s.selectByVisibleText("Option2");

		TakesScreenshot tk3 = (TakesScreenshot) km;

		File src3 = tk3.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9DropDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc4() {
		WebElement c2 = km.findElement(By.xpath("//input[contains(@id,'checkBoxOption2')]"));

		c2.click();

		boolean b4 = c2.isSelected();

		System.out.println("Is Checkbox 2 Selected ?" + b4);

		TakesScreenshot tk4 = (TakesScreenshot) km;

		File src4 = tk4.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9CheckBox.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc5() throws InterruptedException {
		WebElement txt = km.findElement(By.xpath("//input[contains(@id,'name')]"));

		txt.sendKeys("Yogesh");

		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();

		Alert a = km.switchTo().alert();

		a.accept();

		txt.clear();

		txt.sendKeys("Mane");

		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();

		Alert aa = km.switchTo().alert();

		aa.dismiss();
	}

	@Test
	public void tc6() {
		JavascriptExecutor js6 = (JavascriptExecutor) km;

		js6.executeScript("window.scrollBy(0,500)");

		WebElement textbox = km.findElement(By.xpath("//input[contains(@id,'displayed-text')]"));

		textbox.sendKeys("YOGG");

		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();

		boolean b6 = textbox.isDisplayed();

		System.out.println("After clicking on Hide Button Is Textbox Displayed ?" + b6);

		TakesScreenshot tk6 = (TakesScreenshot) km;

		File src6 = tk6.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src6, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9HideTextBox.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();

		boolean b7 = textbox.isDisplayed();

		System.out.println("After clicking on Show button Is Textbox Displayed ?" + b7);

		TakesScreenshot tk7 = (TakesScreenshot) km;

		File src7 = tk7.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src7, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9ShowTextBox.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc7()
	{
		JavascriptExecutor js7=(JavascriptExecutor)km;
		
		js7.executeScript("window.scrollBy(0,1400)");
		
		WebElement frame= km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));
		
		km.switchTo().frame(frame);
		
		JavascriptExecutor js8=(JavascriptExecutor)km;
		
		js8.executeScript("window.scrollBy(0,1000)");
		
		TakesScreenshot tk8=(TakesScreenshot)km;
		
		File src8=tk8.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9Frame.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc8() throws InterruptedException
	{
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();
	
		Set<String> s =km.getWindowHandles();
		
		Iterator<String>ss= s.iterator();
		
		String parent=ss.next();
		
		String child=ss.next();
		
		km.switchTo().window(child);
		
		Thread.sleep(2000);
		
		String newTabTitleName=km.getTitle();
	
		System.out.println("New Tab Title name is :"+newTabTitleName);
		
		String newTabUrl=km.getCurrentUrl();
		
		System.out.println("New Tab URL is :"+newTabUrl);
		
		km.findElement(By.xpath("//a[contains(text(),'Access all our Courses')]")).click();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk9=(TakesScreenshot)km;
		
		File src9=tk9.getScreenshotAs(OutputType.FILE);
	
		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9NewTab.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void tc9() throws InterruptedException
	{
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Ne");
		
		Thread.sleep(2000);
		
		List<WebElement> auto=km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));
		
		int noAuto=auto.size();
		
		System.out.println("Number of Autosuggestion are :"+noAuto);
		
		for(WebElement autotxt:auto)
		{
			String textAutoSuggestion=autotxt.getText();
			
			if(textAutoSuggestion.contentEquals("Nepal"))
			{
				autotxt.click();
			}
		}
		
		TakesScreenshot tk10=(TakesScreenshot)km;
		
		File src10=tk10.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9AutoSuggestion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tc10() throws InterruptedException
	{
		JavascriptExecutor js11=(JavascriptExecutor)km;
		
		js11.executeScript("window.scrollBy(0,1200)");
		
		WebElement mouseHover=km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		Actions aaa=new Actions(km);
		
		aaa.moveToElement(mouseHover).build().perform();
		
		Thread.sleep(2000);
		
		WebElement mouseHoverTop=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		aaa.click(mouseHoverTop).build().perform();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk11=(TakesScreenshot)km;
		
		File src11=tk11.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src11, new File("C://Users//Yogesh//git//NewPractice//Screenshot//3_9Action.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		km.quit();
	}

}
