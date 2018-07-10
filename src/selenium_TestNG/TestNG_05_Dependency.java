package selenium_TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_05_Dependency {
	WebDriver driver;

	@Test
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath("//h3")).isDisplayed());
	}

	@Test(dependsOnMethods = "TC_01")
	public void TC_02() {
		System.out.println("run TC_02");

	}
}
