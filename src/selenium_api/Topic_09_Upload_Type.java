package selenium_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Upload_Type {
	WebDriver driver;
	String filePath = "C:\\WEBDRIVER_API_04_KHOATT\\test.jpg";
	String fileName = "test.jpg";

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	// @Test
	public void TC_01_uploadBySendKeys() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		WebElement addFile = driver.findElement(By.xpath("//input[@type='file']"));
		addFile.sendKeys(filePath);
		sleepIn3S();

		WebElement checkImageUploaded = driver.findElement(By.xpath("//p[@class='name' and contains(text(),'" + fileName + "')]"));
		Assert.assertTrue(checkImageUploaded.isDisplayed());

		WebElement startUpload = driver.findElement(By.xpath("//span[text()='Start']"));
		startUpload.click();
		sleepIn3S();

		// WebElement verifyUploaded = driver.findElement(By.xpath("//a[@download='\" +
		// fileName + \"']"));
		// Assert.assertTrue(verifyUploaded.isDisplayed());
		// sleepIn3S();

	}

	// @Test
	public void TC_02_uploadByAutoIT() throws Exception {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadChrome.click();
		sleepIn3S();
		Runtime.getRuntime().exec(new String[] { ".\\upload\\chrome.exe", filePath });
		sleepIn3S();
	}

	 @Test
	public void TC_03() throws Exception {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//Specify the file location with extension
		StringSelection select = new StringSelection("C:\\\\WEBDRIVER_API_04_KHOATT\\\\test.jpg");

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		driver.findElement(By.className("fileinput-button")).click();

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void sleepIn3S() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}