package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_iFrame_Frame_Window {
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


	public void TC_01() {

	}

	
	public void TC_02() {

	}

	@Test
	public void TC_03_Window() throws Exception {
		// 1 - Truy cập vào trang: http://www.hdfcbank.com/
		// 2 - Kiểm tra và close quảng cáo nếu có xuất hiện
		// 3 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
		// 4 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
		// 5- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
		// 6- Click CSR link on Privacy Policy page
		// 7- Back về Main window (Parent window)
		// 8 - Close tất cả popup khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
		
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		 List<WebElement> IframeClosePopup = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		 System.out.println("Count Element=" + IframeClosePopup.size());
		 if(IframeClosePopup.size()>0) {
			 driver.switchTo().frame(IframeClosePopup.get(0));  
			 WebElement closePopup = driver.findElement(By.xpath("//div[@id='div-close']"));
			  closePopup.click();
		 }
		 
		 WebElement agriLink = driver.findElement(By.xpath("//a[text()='Agri']"));
		 agriLink.click();
		 
		 String parentWindowID = driver.getWindowHandle();
		 System.out.println("parent ID = "+ parentWindowID);
		 switchToChildWindow(parentWindowID);
		 
		 WebElement accountDetailLink = driver.findElement(By.xpath("//p[text()='Account Details']"));
		 accountDetailLink.click();
		 switchToChildWindows("Welcome to HDFC Bank NetBanking");
		
		  
		 
		 
		 WebElement privacyLink = driver.findElement(By.xpath("//frame[@name='footer']"));
		 driver.switchTo().frame(privacyLink); 
		 WebElement privacyPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		 privacyPolicy.click();
		 
	}
	public void switchToChildWindow (String parent) {
		Set<String>  allWindows = driver.getWindowHandles();
		for (String runwindow : allWindows) {
			System.out.println("Window ID =" + runwindow);
			if(!runwindow.equals(parent)) {
				driver.switchTo().window(runwindow);
				break;
			}
		}	
		
	}
	
	public void switchToChildWindows (String title) {
		Set<String>  allWindows = driver.getWindowHandles();
		for (String runwindows : allWindows) {
			System.out.println("Window ID =" + runwindows);
			driver.switchTo().window(runwindows);
			String currentTitle = driver.getTitle();
			if(currentTitle.equals(title)) {
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}