package com.empirix.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.empirix.BaseTest;
import com.empirix.pages.LoginPage;
import com.empirix.pages.DashboardPage;
import com.empirix.webdriver.DriverManager;

public class TestLoginPage extends BaseTest {

	@Test
	public void testUserLogin() {

		LoginPage search = new LoginPage(DriverManager.getWebDriver()).get();
		DashboardPage results = search.search("educative");
		results.nextPage();
	}

}
