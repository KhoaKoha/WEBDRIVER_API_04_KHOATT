package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_10_Wait_Type {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		// Khoi tai trinh duyet
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);

	}

	// @Test
	public void TC_01_ImplicitWait() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement startBtn = driver.findElement(By.xpath("//button[text()='Start']"));
		startBtn.click();

		WebElement Confirm = driver.findElement(By.xpath("//h4[text()='Hello World!']"));
		Assert.assertEquals("Hello World!", Confirm.getText());
		// Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello
		// World!']")).isDisplayed());

	}

	// @Test
	public void TC_02_explicitWait() {

		// 1 - Truy cập vào trang:
		// http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
		// 2 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or
		// visibility)
		// 3 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra =
		// "No Selected Dates to display."
		// 4 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong
		// tháng/ năm hiện tại)
		// 5 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility).
		// Xpath: //div[@class='raDiv']
		// 6 - Wait cho selected date = 23 được visible ((sử dụng: visibility). Xpath:
		// //*[contains(@class,'rcSelected')]//a[text()='23']
		// 7 - Verify ngày đã chọn bằng = Saturday, September 23, 2017

		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement dateTimePicker = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']"));
		wait.until(ExpectedConditions.visibilityOf(dateTimePicker));

		WebElement dateSelectedBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textBefore = dateSelectedBefore.getText().trim();
		Assert.assertEquals("No Selected Dates to display.", textBefore);

		WebElement today = driver.findElement(By.xpath("//a[text()='17']"));
		today.click();

		By ajaxIcon = By.xpath("//div[@class='raDiv']");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxIcon));

		WebElement todaySelected = driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='17']"));
		wait.until(ExpectedConditions.visibilityOf(todaySelected));

		WebElement dateSelectedAfter = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textAfter = dateSelectedAfter.getText().trim();
		Assert.assertEquals("Sunday, June 17, 2018", textAfter);

	}

	// @Test
	public void TC_03_fluentWait() {
		// 1 - Truy cập vào trang:
		// https://stuntcoders.com/snippets/javascript-countdown/
		// 2 - Wait cho đến khi countdown time được visible (visibility)
		// 3 - Sử dụng Fluent wait để:
		// Mỗi 1s kiểm tra countdount= 00 được available trên page (giây đếm ngược về
		// 00)
		// Tức là trong vòng 15s, cứ mỗi 1 giây verify xem nó đã đếm ngược về giây 00
		// hay chưa

		driver.get("https://stuntcoders.com/snippets/javascript-countdown/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement coundownt = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOf(coundownt));

		// Khoi tao FluentWait
		new FluentWait<WebElement>(coundownt)
				// Tong time wait la 15s
				.withTimeout(15, TimeUnit.SECONDS)
				// Tan so moi 1s check 1 lan
				.pollingEvery(1, TimeUnit.SECONDS)
				// Neu gap exception la find ko thay element se bo qua
				.ignoring(NoSuchElementException.class)
				// kiem tra dieu kien
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						// Kiem tra dieu kien countdount = 00
						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());
						// return gia tri cho function
						return flag;

					}

				});

	}

	@Test
	public void TC_04() throws Exception {

		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement countdown = driver.findElement(By.xpath("//span[@id='clock']"));
		wait.until(ExpectedConditions.visibilityOf(countdown));

		WebElement colorChange = driver.findElement(By.xpath("//button[@id='colorVar']"));
		wait.until(ExpectedConditions.visibilityOf(colorChange));

		new FluentWait<WebElement>(colorChange)

				.withTimeout(45, TimeUnit.SECONDS)

				.pollingEvery(5, TimeUnit.SECONDS)

				.ignoring(NoSuchElementException.class)

				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getAttribute("style").contentEquals("color: red;");
						System.out.println(element.getAttribute("style"));
						return flag;
					}
				});

		new FluentWait<WebElement>(countdown)

				.withTimeout(45, TimeUnit.SECONDS)

				.pollingEvery(10, TimeUnit.SECONDS)

				.ignoring(NoSuchElementException.class)

				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getText().contentEquals("Buzz Buzz");
						System.out.println(element.getText());
						return flag;
					}
				});
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}