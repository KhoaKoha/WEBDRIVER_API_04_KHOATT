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
		// Khoi chay tren IE

		// System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
		// WebDriver driver = new InternetExplorerDriver();

		// Khoi chay tren Chrome

		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void TC_01_Check_Elements_Displayed() {

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");

		// tim field Email
		WebElement emailForm = driver.findElement(By.xpath("//input[@id='mail']"));
		// check neu field nay hien thi, sau do send key
		if (emailForm.isDisplayed()) {
			emailForm.sendKeys("Autiomation Testing");
		}
		// tim field age
		WebElement ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		// check neu field nay hien thi, sau do send key
		if (ageUnder18.isDisplayed()) {
			ageUnder18.click();
		}

		WebElement edu = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if (edu.isDisplayed()) {
			edu.sendKeys("Autiomation Testing");
		}

	}

	@Test
	public void TC_02_Check_Element_Enabled_andPrint() {

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");

		// email field
		WebElement emailForm = driver.findElement(By.xpath("//input[@id='mail']"));
		if (emailForm.isEnabled()) {
			System.out.println("Email is enabled");
		} else {
			System.out.println("Email is disabled");
		}
		// age field
		WebElement ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (ageUnder18.isEnabled()) {
			System.out.println("Age Under 18 is enabled");
		} else {
			System.out.println("Age Under 18 is disabled");
		}
		// Education field
		WebElement edu = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if (edu.isEnabled()) {
			System.out.println("Education is enabled");
		} else {
			System.out.println("Education is disabled");
		}
		// Job Role
		WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
		if (jobRole1.isEnabled()) {
			System.out.println("Job Role 01 is enabled");
		} else {
			System.out.println("Job Role 01 is disabled");
		}
		// Interest job
		WebElement interestJob = driver.findElement(By.xpath("//input[@id='development']"));
		if (interestJob.isEnabled()) {
			System.out.println("Interests (Development) is enabled");
		} else {
			System.out.println("Interests (Development) is disabled");
		}
		// Slider 01
		WebElement slider1 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		if (slider1.isEnabled()) {
			System.out.println("Slider 01 is enabled");
		} else {
			System.out.println("Slider 01 is disabled");
		}
		// Button 01
		WebElement button1 = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		if (button1.isEnabled()) {
			System.out.println("Button 01 is enabled");
		} else {
			System.out.println("Button 01  is disabled");
		}

		// Password
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
		if (passwordField.isEnabled()) {
			System.out.println("Password is enabled");
		} else {
			System.out.println("Password  is disabled");
		}

		// Age disabled
		WebElement ageDisable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		if (ageDisable.isEnabled()) {
			System.out.println("Age-disabled is enabled");
		} else {
			System.out.println("Age-disabled  is disabled");
		}

		// Biography
		WebElement bio = driver.findElement(By.xpath("//input[@id='password']"));
		if (bio.isEnabled()) {
			System.out.println("Biography is enabled");
		} else {
			System.out.println("Biography  is disabled");
		}

		// Job Role 02
		WebElement jobRole2 = driver.findElement(By.xpath("//select[@id='job2']"));
		if (jobRole2.isEnabled()) {
			System.out.println("Job Role 02 is enabled");
		} else {
			System.out.println("Job Role 02  is disabled");
		}

		// Interests (Checkbox is disabled)
		WebElement interest2 = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		if (interest2.isEnabled()) {
			System.out.println("Interests (Checkbox is disabled) is enabled");
		} else {
			System.out.println("Interests (Checkbox is disabled) is disabled");
		}

		// Slider 02
		WebElement slider2 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		if (slider2.isEnabled()) {
			System.out.println("Slider 02 is enabled");
		} else {
			System.out.println("Slider 02  is disabled");
		}

		// Button 02
		WebElement button2 = driver.findElement(By.xpath("//button[@id='button-disabled']"));
		if (button2.isEnabled()) {
			System.out.println("Button 02 is enabled");
		} else {
			System.out.println("Button 02  is disabled");
		}

	}

	@Test
	public void TC_03_Check_Recheck_IsSelectedElements() {

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");

		// Age (Under 18)
		WebElement ageRadio = driver.findElement(By.xpath("//input[@id='under_18']"));
		ageRadio.click();

		// Interest Development
		WebElement interest = driver.findElement(By.xpath("//input[@id='development']"));
		interest.click();

		if (ageRadio.isSelected()) {
			System.out.println("Age (Under 18)is clicked");
		} else {
			interest.click();
		}

		if (interest.isSelected()) {
			System.out.println("Interest is clicked");
		} else {
			interest.click();
		}

	}

	@AfterClass
	public void afterClass() {

		// quit browser
		driver.quit();
	}
}