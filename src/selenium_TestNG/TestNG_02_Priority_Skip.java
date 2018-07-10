package selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestNG_02_Priority_Skip {
	@Test
	public void TC_01() {
		System.out.println("Testcase ko set do uu tien");
	}

	@Test(groups = "group2", priority = 4)
	public void TC_02() {
		System.out.println("Testcase uu tien 4");
	}

	@Test(groups = "group1", priority = 3, enabled = false)
	public void TC_03() {
		System.out.println("Testcase uu tien 3");
	}

	@Test(groups = "group2", priority = 2)
	public void TC_04() {
		System.out.println("Testcase uu tien 2");
	}

	@Test(priority = 1)
	public void TC_05() {
		System.out.println("Testcase uu tien 1");
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
