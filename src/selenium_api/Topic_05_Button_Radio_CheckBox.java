package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Button_Radio_CheckBox {

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
		driver.get("http://daominhdam.890m.com/");
	}

	@Test
	public void TC_01() throws Exception {

		WebElement buttonClick = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		buttonClick.click();
		
		String homePageTitle = driver.getCurrentUrl();
		Assert.assertEquals("http://daominhdam.890m.com/#", homePageTitle);
		Thread.sleep(2000);
		
		driver.navigate().back();
		WebElement enable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", enable);
		Thread.sleep(2000);
		
		String homePageTitle2 = driver.getCurrentUrl();
		Assert.assertEquals("http://daominhdam.890m.com/#", homePageTitle2);
		Thread.sleep(2000);
		
	}

	public void TC_02() {

	}

	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}