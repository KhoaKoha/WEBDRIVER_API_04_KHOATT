package selenium_api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Exercise_XpathUse {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();
		
		driver = new FirefoxDriver();
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
		Assert.assertEquals("This is a required field.", passErroMsg);

	}

	@Test
	public void TC_02_Login_Invalid_Email() {

		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.name("send")).click();

		String emailError = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", emailError);

	}

	@Test
	public void TC_03_Login_Invalid_Password() {

		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass'] ")).sendKeys("123");
		driver.findElement(By.xpath("//button[@class='button']")).click();

		String passwordError = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", passwordError);

	}

	@AfterClass
	public void afterClass() {
		
		// quit browser
		driver.quit();
			}
}