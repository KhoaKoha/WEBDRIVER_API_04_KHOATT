package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnvironment {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Khoi tai trinh duyet
//       driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		
		driver = new ChromeDriver();

		// wait cho page duoc load thanh cong
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// maximize browser len full screen
		driver.manage().window().maximize();

		// get URL cua app
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_CheckTitleAndUrl() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals("Home page", homePageTitle);

		String homePageURL = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/", homePageURL);
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}