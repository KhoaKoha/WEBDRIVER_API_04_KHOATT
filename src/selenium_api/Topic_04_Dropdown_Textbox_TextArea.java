package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Dropdown_Textbox_TextArea {
	WebDriver driver;
	String dropDownjob = "//select[@id='job1']";
	String user, pass, name, dob, address, city, state, pin, mobi, email, password, ID, newAddress, newCity;

	@BeforeClass
	public void beforeClass() {
		// Khoi chay tren IE

		// System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
		// WebDriver driver = new InternetExplorerDriver();

		// Khoi chay tren Chrome

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		// driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		user = "mngr135194";
		pass = "supAtAg";
		name = "Anh Khoa";
		dob = "1999-11-05";
		address = "123 Address";
		city = "Da Nang";
		state = "Can Tho";
		pin = "650000";
		mobi = "0123456789";
		email = "automation" + randomNumber() + "@gmail.com";
		password = "12345678";
		newAddress = "57 Dien Bien Phu";
		newCity = "HCM City";
	}

	@Test
	public void TC_01_Dropdown_List() {
		driver.get("http://daominhdam.890m.com/");

		Select jobDrop = new Select(driver.findElement(By.xpath(dropDownjob)));

		jobDrop.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobDrop.getFirstSelectedOption().getText(), "Automation Tester");

		jobDrop.selectByValue("manual");
		Assert.assertEquals(jobDrop.getFirstSelectedOption().getText(), "Manual Tester");

		jobDrop.selectByIndex(3);
		Assert.assertEquals(jobDrop.getFirstSelectedOption().getText(), "Mobile Tester");

		int allDropOp = jobDrop.getOptions().size();
		Assert.assertEquals(allDropOp, 5);

	}

	@Test
	public void TC_02_Handle_TextBox_TextArea() {
		// 1
		driver.get("http://demo.guru99.com/v4");
		
		// 2
		WebElement userTextbox = driver.findElement(By.xpath("//input[@name = 'uid']"));
		WebElement passTextbox = driver.findElement(By.xpath("//input[@name = 'password']"));
		userTextbox.sendKeys(user);
		passTextbox.sendKeys(pass);
		WebElement loginBtn = driver.findElement(By.xpath("//input[@name = 'btnLogin']"));
		loginBtn.click();
		
		// 3
		WebElement newCustomerLink = driver.findElement(By.xpath("//a[text() = 'New Customer']"));
		newCustomerLink.click();
		
		// 4
		WebElement nameTextbox = driver.findElement(By.xpath("//input[@name = 'name']"));
		nameTextbox.sendKeys(name);

		WebElement femaleRatio = driver.findElement(By.xpath("//input[@value = 'f']"));
		femaleRatio.click();

		WebElement dobTextbox = driver.findElement(By.xpath("//input[@name = 'dob']"));
		removeAttributeInDOM(dobTextbox, "type");
		dobTextbox.sendKeys(dob);

		WebElement addrTextarea = driver.findElement(By.xpath("//textarea [@name = 'addr']"));
		addrTextarea.sendKeys(address);

		WebElement cityTextbox = driver.findElement(By.xpath("//input[@name = 'city']"));
		cityTextbox.sendKeys(city);

		WebElement stateTextbox = driver.findElement(By.xpath("//input[@name = 'state']"));
		stateTextbox.sendKeys(state);

		WebElement pinTextbox = driver.findElement(By.xpath("//input[@name = 'pinno']"));
		pinTextbox.sendKeys(pin);

		WebElement mobiTextbox = driver.findElement(By.xpath("//input[@name = 'telephoneno']"));
		mobiTextbox.sendKeys(mobi);

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@name = 'emailid']"));
		emailTextbox.sendKeys(email);

		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@name = 'password']"));
		passwordTextbox.sendKeys(password);

		WebElement submitButton = driver.findElement(By.xpath("//input[@name = 'sub']"));
		submitButton.click();

		WebElement createddSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"));
		createddSuccessMsg.isDisplayed();

		ID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		// 5
		Assert.assertEquals(name, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		
		Assert.assertEquals("female", driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		
		Assert.assertEquals(dob, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		
//		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		
		Assert.assertEquals(mobi, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		
		// 6
		WebElement editButton = driver.findElement(By.xpath("//a[text() = 'Edit Customer']"));
		editButton.click();

		WebElement customerIDTextbox = driver.findElement(By.xpath("//input[@name = 'cusid']"));
		customerIDTextbox.sendKeys(ID);
		
		WebElement accSubmitBtn = driver.findElement(By.xpath("//input[@name = 'AccSubmit']"));
		accSubmitBtn.click();

		// 7
		WebElement custNameResult = driver.findElement(By.xpath("//input[@name='name']"));
		
		Assert.assertEquals(name, custNameResult.getAttribute("value"));
		
		
		WebElement lastAddress = driver.findElement(By.xpath("//textarea[@name='addr']"));
		lastAddress.click();
		Assert.assertEquals(address, lastAddress.getAttribute("value"));
		
		

		
		// Edit address, city
		WebElement editAdd = driver.findElement(By.xpath("//textarea [@name='addr']"));
		editAdd.clear();
		editAdd.sendKeys(newAddress);
		
		WebElement editCityTb = driver.findElement(By.xpath("//input[@name = 'city']"));
		editCityTb.clear();
		editCityTb.sendKeys(newCity);
		
		driver.findElement(By.xpath("//input[@name = 'sub']")).click();
	

	}

	public void TC_03() {

	}

	public boolean isControlSelected(String xpathName) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		return element.isSelected();

	}

	public boolean isControlDisplayed(String xpathName) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		return element.isDisplayed();

	}

	public boolean isControlEnabled(String xpathName) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		return element.isEnabled();

	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(10000);
		return number;
	}

	private void removeAttributeInDOM(WebElement element, String attributeName) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("arguments[0].removeAttribute('" + attributeName + "')", element);
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}