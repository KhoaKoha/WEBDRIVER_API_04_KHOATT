package selenium_api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.UUID;

public class Topic_02_Exercise_XpathUse {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_Login_Empty() {

		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass'] ")).sendKeys("");
		driver.findElement(By.xpath("//button[@class='button']")).click();

		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals("This is a required field.", emailErrorMsg);

		String passErroMsg = driver.findElement(By.xpath("//div[@class='validation-advice']")).getText();
		Assert.assertEquals(passErroMsg, "This is a required field.");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {

		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.name("send")).click();

		String emailError = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailError, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_Invalid_Password() {

		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass'] ")).sendKeys("123");
		driver.findElement(By.xpath("//button[@class='button']")).click();

		String passwordError = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordError, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Create_Account_Successfully() {

		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[contains(@title,'Create an Account')]")).click();
		driver.findElement(By.id("firstname")).sendKeys("KhoaKoha");
		driver.findElement(By.name("middlename")).sendKeys("Tester");
		driver.findElement(By.name("lastname")).sendKeys("abcdef");
		Random ranNum = new Random();
		int ranEmailNum = ranNum.nextInt(100) + 1;
		
		String ranString = UUID.randomUUID().toString();
		
		driver.findElement(By.id("email_address")).sendKeys(ranString + ranEmailNum + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("12345678");
		driver.findElement(By.xpath("//form[@id='form-validate']//button[@class='button']")).click();
		String regSuccess = driver
				.findElement(By.xpath("//span[contains(.,'Thank you for registering with Main Website Store.')]"))
				.getText();
		Assert.assertEquals(regSuccess, "Thank you for registering with Main Website Store.");
		driver.findElement(By.xpath(".//div[@class='skip-links']//span[contains(text(),'Account')]")).click();
		driver.findElement(By.xpath(".//div[@id='header-account']//a[@title='Log Out']")).click();
		
		String homePageTitle = driver.getTitle();
		Assert.assertEquals("Magento Commerce", homePageTitle);

		String homePageURL = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/index.php/customer/account/logoutSuccess/", homePageURL);

	}

	@AfterClass
	public void afterClass() {

		// quit browser
		driver.quit();
	}
}