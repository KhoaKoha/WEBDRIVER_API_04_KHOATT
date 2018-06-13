package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_iFrame_Frame_Window {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void TC_01_Iframe_Frame_Hdfc() {

		// 1 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 2 - Close popup nếu có hiển thị (switch qua iframe nếu có) - F5 (refresh
		// page) nhiều lần thì sẽ xuất hiện popup
		List<WebElement> IframeClosePopup = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Count Element=" + IframeClosePopup.size());
		if (IframeClosePopup.size() > 0) {
			driver.switchTo().frame(IframeClosePopup.get(0));
			WebElement closePopup = driver.findElement(By.xpath("//div[@id='div-close']"));
			closePopup.click();
		}

		// 3 - Verify đoạn text được hiển thị: What are you looking for? (switch qua
		// iframe nếu có)
		WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingForIframe);

		WebElement lookingForText = driver.findElement(By.xpath("//span[@id='messageText']"));
		Assert.assertEquals("What are you looking for?", lookingForText.getText());

		// 4 Verify banner image được hiển thị (switch qua iframe nếu có)
		driver.switchTo().defaultContent();
		WebElement bannerImageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(bannerImageIframe);

		// Verify banner có đúng 6 images
		List<WebElement> image = driver.findElements(By.xpath("//div[@id='bannercontainer']//img"));
		int imageNumber = image.size();
		Assert.assertEquals(6, imageNumber);

		// 5 - Verify flipper banner được hiển thị và có 8 items
		driver.switchTo().defaultContent();
		WebElement flipperBanner = driver.findElement(By.xpath("//div[@class='flipBannerWrap']"));
		Assert.assertTrue(flipperBanner.isDisplayed());

		List<WebElement> flipperImage = driver.findElements(By.xpath("//div[@class='flipBannerWrap']//img[@class='front icon']"));
		imageNumber = flipperImage.size();
		Assert.assertEquals(8, imageNumber);

	}

	@Test
	public void TC_02_Window() {

		// 1 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String parentWindowID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentWindowID);

		// 2 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		switchToChildWindow(parentWindowID);
		// 3 - Kiểm tra title của window mới = Google
		String titleNewWindow = driver.getTitle();
		Assert.assertEquals("Google", titleNewWindow);
		// 4 - Close window mới
		// 5 - Switch về parent window
		closeAllWithoutParentWindows(parentWindowID);

	}

	@Test
	public void TC_03_Window() {

		// (http://www.hdfcbank.com/)

		// 1 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// 2 - Kiểm tra và close quảng cáo nếu có xuất hiện
		List<WebElement> IframeClosePopup = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Count Element=" + IframeClosePopup.size());
		if (IframeClosePopup.size() > 0) {
			driver.switchTo().frame(IframeClosePopup.get(0));
			WebElement closePopup = driver.findElement(By.xpath("//div[@id='div-close']"));
			closePopup.click();
		}

		// 3 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
		WebElement agriLink = driver.findElement(By.xpath("//a[text()='Agri']"));
		agriLink.click();

		String parentWindowID = driver.getWindowHandle();
		System.out.println("parent ID = " + parentWindowID);
		switchToChildWindow(parentWindowID);

		// 4 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
		WebElement accountDetailLink = driver.findElement(By.xpath("//p[text()='Account Details']"));
		accountDetailLink.click();
		switchToChildWindows("Welcome to HDFC Bank NetBanking");
		// 5- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới ->
		WebElement privacyLink = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(privacyLink);
		WebElement privacyPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		privacyPolicy.click();

		// 6- Click CSR link on Privacy Policy page
		switchToChildWindows("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		WebElement csrLink = driver.findElement(By.xpath("//a[text()='CSR']"));
		csrLink.click();

		// 7- Back về Main window (Parent window)
		driver.switchTo().defaultContent();
		// 8 - Close tất cả popup khác - chỉ giữ lại parent window
		closeAllWithoutParentWindows(parentWindowID);

	}

	public void switchToChildWindow(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runwindow : allWindows) {
			System.out.println("Window ID =" + runwindow);
			if (!runwindow.equals(parent)) {
				driver.switchTo().window(runwindow);
				break;
			}
		}

	}

	public void switchToChildWindows(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runwindows : allWindows) {
			System.out.println("Window ID =" + runwindows);
			driver.switchTo().window(runwindows);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}