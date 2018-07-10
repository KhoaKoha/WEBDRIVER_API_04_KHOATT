package selenium_TestNG;

import org.testng.annotations.Test;


import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class TestNG_03_Data_provider {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "UserAndPass")
	public void TC_01_LoginWithMultiSteps(String user, String password) {
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		myAccountLink.click();

		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		email.sendKeys(user);

		WebElement passwwork = driver.findElement(By.xpath("//input[@id='pass']"));
		passwwork.sendKeys(password);

		WebElement loginBTN = driver.findElement(By.xpath("//button[@id='send2']"));
		loginBTN.click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());

		WebElement accountLink = driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']"));
		accountLink.click();

		WebElement logoutLink = driver.findElement(By.xpath("//a[text()='Log Out']"));
		logoutLink.click();
	}

	@DataProvider(name = "UserAndPass")
	public Object[][] getUserPass() {
		return new Object[][] { new Object[] { "automationvalid_01@gmail.com", "111111" }, new Object[] { "automationvalid_02@gmail.com", "111111" }, new Object[] { "automationvalid_03@gmail.com", "111111" }, };
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}