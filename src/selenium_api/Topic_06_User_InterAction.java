package selenium_api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	public void TC_01_Mouse_Element() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");

		WebElement hoverLink = driver.findElement(By.xpath("//a[text()='Hover over me']"));

		Actions act = new Actions(driver);
		act.moveToElement(hoverLink).perform();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='tooltip-inner' and text()='Hooray!']")).isDisplayed());

	}

	@Test
	public void TC_01_02_Mouse_Element() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");

		WebElement hoverLink = driver.findElement(By.xpath("//div[@class ='desktop-userIconsContainer']"));

		Actions act = new Actions(driver);
		act.moveToElement(hoverLink).perform();

		driver.findElement(By.xpath("//a[@class='desktop-linkButton' and text()='login']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Login to Myntra']")).isDisplayed());

	}

	@Test
	public void TC_03_ClickAndHoldElement() {

		// 1 - go to page:
		// http://jqueryui.com/resources/demos/selectable/display-grid.html
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 2 - Click and hold from 1-> 4
		List<WebElement> listElement = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions moveItems = new Actions(driver);
		moveItems.clickAndHold(listElement.get(0)).moveToElement(listElement.get(3)).release().perform();

		// 3 - verify 4 elements are selected
		List<WebElement> listElementSeleted = driver
				.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		int number = listElementSeleted.size();
		Assert.assertEquals(4, number);

	}

	@Test
	public void TC_04_02_ClickAndHoldElement() {

		// 1 - go to page:
		// http://jqueryui.com/resources/demos/selectable/display-grid.html
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 2 - Click and hold 1,3,5,7
		List<WebElement> listElement = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
		listElement.get(0).click();
		listElement.get(2).click();
		listElement.get(4).click();
		listElement.get(6).click();
		action.keyUp(Keys.CONTROL).build().perform();

		// 3 - verify 4 elements are selected
		List<WebElement> listNumber = driver
				.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		int numb = listNumber.size();
		Assert.assertEquals(4, numb);

	}

	@Test
	public void TC_05_DoubleClick() {

		// 1 - go to page http://www.seleniumlearn.com/double-click
		driver.get("http://www.seleniumlearn.com/double-click");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 2 - Double click on element: Double-Click Me!
		WebElement doubleClickBtn = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		Actions action = new Actions(driver);
		action.doubleClick(doubleClickBtn).perform();

		// 3 - Verify text alert is displayed: 'The Button was double-clicked.'
		Alert actionAlert = driver.switchTo().alert();
		String textAlert = actionAlert.getText();
		Assert.assertEquals("The Button was double-clicked.", textAlert);

		// 4 - Accept Javascript alert
		actionAlert.accept();
	}

	@Test
	public void TC_06_RightClickToElement() {

		// 1 - go to page http://swisnl.github.io/jQuery-contextMenu/demo.html
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 2 - Right click to element: 'right click me'
		WebElement rightClickBtn = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(rightClickBtn).perform();

		// 3 - Hover on element: 'Quit'
		WebElement hoverQuitBefore = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(hoverQuitBefore).perform();

		// 4 - Verify element Quit (visible + hover)
		WebElement hoverQuitAfter = driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]//span[text()='Quit']"));
		Assert.assertTrue(hoverQuitAfter.isDisplayed());

		// 5 - Click Quit
		action.click(hoverQuitAfter).perform();
		Alert quitAlert = driver.switchTo().alert();
		String textAlert = quitAlert.getText();
		Assert.assertEquals("clicked: quit", textAlert);

		// 6 - Accept Javascript alert
		quitAlert.accept();

	}

	@Test
	public void TC_07_DrapAndDropElement() {

		// 1 - truy cap vao trang: http://demos.telerik.com/kendo-ui/dragdrop/angular
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 2 - Kéo hình tròn nhỏ vào hình tròn lớn
		// WebElement textBefore = driver.findElement(By.xpath("//div[text()='Drag the
		// small circle here.']")); (!!!)

		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).build().perform();
		action.release();

		// 3 - Verify message đã thay đổi: You did great!
		WebElement textAfter = driver.findElement(By.xpath("//div[text()='You did great!']"));
		// Assert.assertFalse(textBefore.isDisplayed());(!!!)
		Assert.assertTrue(textAfter.isDisplayed());

	}

	@Test
	public void TC_08_DrapAndDropElement() {

		// 1 - Truy cập vào trang:
		// http://jqueryui.com/resources/demos/droppable/default.html
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 2 - Kéo hình chữ nhật: Drag me to my target vào hình Drop here
		WebElement textBeforeDrap = driver.findElement(By.xpath("//div[@id='droppable']//p[text()='Drop here']"));
		Assert.assertTrue(textBeforeDrap.isDisplayed());
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droppable']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).build().perform();
		action.release();

		// 3 - Verify message đã thay đổi: Dropped!
		WebElement textAfterDrop = driver.findElement(By.xpath("//div[@id='droppable']/p[text()='Dropped!']"));
		Assert.assertTrue(textAfterDrop.isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}
}