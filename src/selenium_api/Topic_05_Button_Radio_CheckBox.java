package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

	}

	@Test
	public void TC_01_Handle_Click() throws Exception {

		
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		
		WebElement seleniumClick = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		seleniumClick.click();
		String homepageURL = driver.getCurrentUrl();
		Assert.assertEquals(homepageURL, "http://live.guru99.com/index.php/customer/account/login/");

		
		WebElement createAcc = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		clickElementByJavaScript(createAcc);
		Assert.assertEquals("http://live.guru99.com/index.php/customer/account/create/", driver.getCurrentUrl());
	}

	@Test
	public void TC_02_Hande_Checkbox() throws InterruptedException {

		
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		

		WebElement checkboxDual = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		clickElementByJavaScript(checkboxDual);
		Assert.assertTrue(checkboxDual.isSelected());
		uncheckCheckBox(checkboxDual);
	}

	@Test
	public void TC_03_05_Hande_RadioButton() throws InterruptedException {

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		
		WebElement radioPetrol = driver
				.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		clickElementByJavaScript(radioPetrol);

		
		Assert.assertTrue(radioPetrol.isSelected());
		checkRadioButton(radioPetrol);

	}

	@Test
	public void TC_04_Hande_jsAlert() {
		

		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement jsAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		jsAlertBtn.click();

		Alert jsAlert = driver.switchTo().alert();
		String textOnAlert = jsAlert.getText();
		Assert.assertEquals("I am a JS Alert", textOnAlert);

		jsAlert.accept();
		String msgAlertResult = driver.findElement(By.xpath(".//p[@id='result']")).getText();
		Assert.assertEquals("You clicked an alert successfully", msgAlertResult);

	}

	@Test
	public void TC_05_Hande_jsConfirm() {
		

		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
		jsConfirmBtn.click();

		Alert jsConfirmAlert = driver.switchTo().alert();
		String textOnAlert = jsConfirmAlert.getText();
		Assert.assertEquals("I am a JS Confirm", textOnAlert);

		jsConfirmAlert.dismiss();
		String msgConfirmResult = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You clicked: Cancel", msgConfirmResult);

	}

	@Test
	public void TC_06_Hande_jsPrompt() {
		
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement jsPromptBtn = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		jsPromptBtn.click();

		Alert jsPromptAlert = driver.switchTo().alert();
		String textOnAlert = jsPromptAlert.getText();
		Assert.assertEquals("I am a JS prompt", textOnAlert);

		jsPromptAlert.sendKeys("hoanguyen");
		jsPromptAlert.accept();
		String msgPromptResult = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You entered: hoanguyen", msgPromptResult);

	}

	public void clickElementByJavaScript(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click()", element);

	}

	public void uncheckCheckBox(WebElement element) {
		if (element.isSelected()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click()", element);
			Assert.assertTrue(!element.isSelected());
		}
	}

	public void checkRadioButton(WebElement element) {
		if (!element.isSelected()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click()", element);
			Assert.assertTrue(element.isSelected());

		}

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}