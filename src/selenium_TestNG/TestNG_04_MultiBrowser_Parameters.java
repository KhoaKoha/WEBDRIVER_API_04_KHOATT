package selenium_TestNG;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class TestNG_04_MultiBrowser_Parameters {
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browser) {

		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome_headless")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
		} else {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Parameters({ "user", "password" })
	@Test(invocationCount = 2)
	public void TC_01_LoginWithMultiBrowers(String email, String password) {
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		myAccountLink.click();

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.sendKeys(email);

		WebElement passwworkTextBox = driver.findElement(By.xpath("//input[@id='pass']"));
		passwworkTextBox.sendKeys(password);

		WebElement loginBTN = driver.findElement(By.xpath("//button[@id='send2']"));
		loginBTN.click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());

		WebElement accountLink = driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']"));
		accountLink.click();

		WebElement logoutLink = driver.findElement(By.xpath("//a[text()='Log Out']"));
		logoutLink.click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
