package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_TestcaseTemplate {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Khoi chay tren IE
		
		// System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
		// WebDriver driver = new InternetExplorerDriver();
		
		// Khoi chay tren Chrome
		
		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();
		
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01() {

	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}