package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_User_InterAction {
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
		// test
		
	}

	@Test
	public void TC_01_Mouse_Element () {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
		
		WebElement hoverLink = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		
		Actions act = new Actions(driver);
		act.moveToElement(hoverLink).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='tooltip-inner' and text()='Hooray!']")).isDisplayed());
		
	}

	@Test
	public void TC_01_02_Mouse_Element_02() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		
		WebElement hoverLink = driver.findElement(By.xpath("//div[@class ='desktop-userIconsContainer']"));
		
		Actions act = new Actions(driver);
		act.moveToElement(hoverLink).perform();
		
		driver.findElement(By.xpath("//a[@class='desktop-linkButton' and text()='login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Login to Myntra']")).isDisplayed());
		
	}

	
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}