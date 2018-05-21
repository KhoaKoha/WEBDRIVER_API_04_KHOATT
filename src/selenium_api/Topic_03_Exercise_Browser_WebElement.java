package selenium_api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.UUID;
import org.openqa.selenium.WebElement;

public class Topic_03_Exercise_Browser_WebElement {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
	}

	@Test
	public void TC_01_Login_Empty() {

		WebElement emailForm = driver.findElement(By.xpath("//label[@for='mail']"));
		if (emailForm.isDisplayed()) {
			driver.findElement(By.xpath(".//*[@id='mail']")).sendKeys("Autiomation Testing");
		}
		WebElement ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (ageUnder18.isDisplayed()) {
			ageUnder18.click();
		}
		
		WebElement edu = driver.findElement(By.xpath("//label[@for='edu']"));
		if (edu.isDisplayed()) {
			driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Autiomation Testing");
		}

	}

	@AfterClass
	public void afterClass() {

		// quit browser
		driver.quit();
	}
}