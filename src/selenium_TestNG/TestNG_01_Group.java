package selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestNG_01_Group {
	@Test(groups = "group1")
	public void TC_01() {
		System.out.println("Testcase 01");
	}

	@Test(groups = "group2")
	public void TC_02() {
		System.out.println("Testcase 02");
	}

	@Test(groups = "group1")
	public void TC_03() {
		System.out.println("Testcase 03");
	}

	@Test(groups = "group2")
	public void TC_04() {
		System.out.println("Testcase 04");
	}

	@Test
	public void TC_05() {
		System.out.println("Testcase 05");
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
