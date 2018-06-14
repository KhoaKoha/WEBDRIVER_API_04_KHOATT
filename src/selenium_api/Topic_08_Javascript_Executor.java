package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Javascript_Executor {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();

	}

	@Test
	public void TC_01_JavascriptExcecutor() throws Exception {
		// 1 - Truy cập vào trang: http://live.guru99.com/
		// 2 - Sử dụng JE để get domain của page. Verify domain = live.guru99.com
		// 3 - Sử dụng JE để get URL của page. Verify URL = http://live.guru99.com/
		// 4 - Open MOBILE page (Sử dụng JE)
		// 5 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng
		// JE)
		// 6 - Verify message được hiển thị: Samsung Galaxy was added to your shopping
		// cart. (Sử dụng JE - Get innertext of the entire webpage )
		// 7 - Open PRIVACY POLICY page (Sử dụng JE). Verify title của page = Privacy
		// Policy (Sử dụng JE)
		// 8 - Srcoll xuống cuối page
		// 9 - Verify dữ liệu có hiển thị với chỉ 1 xpath
		// 10 - Navigate tới domain: http://demo.guru99.com/v4/ (Sử dụng JE). Verify
		// domain sau khi navigate = http://demo.guru99.com/v4/

		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String domain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals("live.guru99.com", domain);
		String pageURL = (String) executeJSForBrowserElement("return document.URL;");
		Assert.assertEquals("http://live.guru99.com/", pageURL);

		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		executeByJSForWebElement(mobileLink);
		WebElement buttonAdd = driver.findElement(By.xpath("//h2[@class='product-name']/a[@title='Samsung Galaxy']/../following-sibling::div[@class='actions']/button"));
		executeByJSForWebElement(buttonAdd);

		String message = (String) executeJSForBrowserElement("return document.documentElement.innerText;");
		Assert.assertTrue(message.contains("Samsung Galaxy was added to your shopping cart."));
		WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		executeByJSForWebElement(privacyLink);

		String titlePrivacy = (String) executeJSForBrowserElement("return document.title;");
		Assert.assertEquals("Privacy Policy", titlePrivacy);

		scrollToBottomPage();

		WebElement wishlist = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td"));
		Assert.assertTrue(wishlist.isDisplayed());

		String demoGuru = "http://demo.guru99.com/v4/";
		executeJSForBrowserElement("window.location = '" + demoGuru + "'");

		String domainGuru = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals("demo.guru99.com", domainGuru);
		Thread.sleep(1500);
	}

	@Test
	public void TC_02_RemoveAttribute() throws Exception {

		// 1 - Truy cập vào trang:
		// https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
		// 2 - Remove thuộc tính disabled của field Last name. Switch qua iframe nếu có
		// 3 - Sendkey vào field Last name.Eg. Automation Testing
		// 4 - Click Submit button
		// 5 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname
		// (Automation Testing)

		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframeResult);

		WebElement lastnameTextbox = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(lastnameTextbox, "disabled");
		lastnameTextbox.sendKeys("Anh KHoa");
		Thread.sleep(1500);
		WebElement submitBtn = driver.findElement(By.xpath("//input[@value='Submit']"));
		submitBtn.click();

		WebElement mgsSuccess = driver.findElement(By.xpath("//div[contains(text(),\"Anh KHoa\")]"));
		Assert.assertTrue(mgsSuccess.isDisplayed());
		Thread.sleep(1500);
	}

	public Object executeJSForBrowserElement(String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeByJSForWebElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeInDOM(WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public boolean checkAnyImageLoaded(WebElement img) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (boolean) js.executeScript(" return arguments[0].complete && " + "typeof arguments[0].naturalWidth != 'undefiend' && arguments[0].naturalWidth>0", img);
	}
	
	

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}