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

public class Day4_9 {

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
		String url = km.getCurrentUrl();

		System.out.println("Current URL is :" + url);

		String title = km.getTitle();

		System.out.println("Current Title is :" + title);

		List<WebElement> tg = km.findElements(By.tagName("a"));

		int noOfTag = tg.size();

		System.out.println("Total Number of Tags :" + noOfTag);

		for (WebElement tgtx : tg) {
			String tagText = tgtx.getText();

			System.out.println("Tag name are :" + tagText);
		}
	}

	@Test
	public void tc2() {
		WebElement r3 = km.findElement(By.xpath("//input[contains(@value,'radio3')]"));

		r3.click();

		boolean b2 = r3.isSelected();

		System.out.println("Is Radio button 2 Selected ?" + b2);

		TakesScreenshot tk2 = (TakesScreenshot) km;

		File src2 = tk2.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src2, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9RadioButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc3() {
		WebElement drpdwn = km.findElement(By.xpath("//select[contains(@id,'dropdown-class-example')]"));

		Select s = new Select(drpdwn);

		s.selectByVisibleText("Option3");

		TakesScreenshot tk3 = (TakesScreenshot) km;

		File src3 = tk3.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src3, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9DropDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc4() {
		WebElement c3 = km.findElement(By.xpath("//input[contains(@id,'checkBoxOption3')]"));

		c3.click();

		boolean b3 = c3.isSelected();

		System.out.println("Is checkbox 3 selected ?:" + b3);

		TakesScreenshot tk4 = (TakesScreenshot) km;

		File src4 = tk4.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src4, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9CheckBox.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc5() throws InterruptedException {
		km.findElement(By.xpath("//a[contains(@id,'opentab')]")).click();

		Set<String> s = km.getWindowHandles();

		Iterator<String> ss = s.iterator();

		String parent = ss.next();

		String child = ss.next();

		km.switchTo().window(child);

		String newTabTitle = km.getTitle();

		System.out.println("New Tab title name is :" + newTabTitle);

		String newTabUrl = km.getCurrentUrl();

		System.out.println("New Tab URL is :" + newTabUrl);

		Thread.sleep(2000);

		TakesScreenshot tk5 = (TakesScreenshot) km;

		File src5 = tk5.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src5, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9NewTab.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc6() throws InterruptedException {
		km.findElement(By.xpath("//input[contains(@id,'autocomplete')]")).sendKeys("Ko");

		Thread.sleep(2000);

		List<WebElement> auto = km.findElements(By.xpath("//li[contains(@class,'ui-menu-item')]"));

		int autoNo = auto.size();

		System.out.println("Total number of Autosuggestion are :" + autoNo);

		for (WebElement autotxt : auto) {
			String txt = autotxt.getText();

			if (txt.contentEquals("Hong Kong")) {
				autotxt.click();
			}
		}

		TakesScreenshot tk6 = (TakesScreenshot) km;

		File src6 = tk6.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src6,
					new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9AutoSuggestion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc7() {
		WebElement alertText = km.findElement(By.xpath("//input[contains(@id,'name')]"));

		alertText.sendKeys("KALYANI");

		km.findElement(By.xpath("//input[contains(@id,'alertbtn')]")).click();

		Alert a = km.switchTo().alert();

		a.accept();

		alertText.sendKeys("MANE");

		km.findElement(By.xpath("//input[contains(@id,'confirmbtn')]")).click();

		Alert aa = km.switchTo().alert();

		aa.dismiss();
	}

	@Test
	public void tc8() {
		JavascriptExecutor js8 = (JavascriptExecutor) km;

		js8.executeScript("window.scrollBy(0,500)");

		km.findElement(By.xpath("//input[contains(@id,'displayed-text')]")).sendKeys("MANUSHREE");

		km.findElement(By.xpath("//input[contains(@id,'hide-textbox')]")).click();

		TakesScreenshot tk8 = (TakesScreenshot) km;

		File src8 = tk8.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src8, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9Hide.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		km.findElement(By.xpath("//input[contains(@id,'show-textbox')]")).click();

		TakesScreenshot tk9 = (TakesScreenshot) km;

		File src9 = tk9.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src9, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9Show.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc9() throws InterruptedException {

		JavascriptExecutor js9 = (JavascriptExecutor) km;

		js9.executeScript("window.scrollBy(0,1500)");

		WebElement frame = km.findElement(By.xpath("//iframe[contains(@id,'courses-iframe')]"));

		km.switchTo().frame(frame);

		km.findElement(By.xpath("//a[contains(text(),'Login')]")).click();

		Thread.sleep(2000);

		TakesScreenshot tk10 = (TakesScreenshot) km;

		File src10 = tk10.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src10, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9Frame.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tc10() throws InterruptedException {
		
		JavascriptExecutor js10 = (JavascriptExecutor) km;

		js10.executeScript("window.scrollBy(0,1300)");

		WebElement mouseHover = km.findElement(By.xpath("//button[contains(@id,'mousehover')]"));
		
		Actions aaa=new Actions(km);
		
		aaa.moveToElement(mouseHover).build().perform();
		
		Thread.sleep(2000);
		
		WebElement top=km.findElement(By.xpath("//a[contains(text(),'Top')]"));
		
		aaa.click(top).build().perform();
		
		TakesScreenshot tk11=(TakesScreenshot)km;
		
		File src11=tk11.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src11, new File("C://Users//Yogesh//git//NewPractice//Screenshot//4_9Actions.png"));
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
