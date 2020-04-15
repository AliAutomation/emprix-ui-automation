package com.empirix.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class LoginPage extends AbstractBasePage<LoginPage> {

	private static final String URL = "https://services.empirix.com/";

	public LoginPage(WebDriver driver) {
		super(driver, "signin-home.properties");
	}

	@Override
	protected void isLoaded() throws Error {
		driver.get(URL);
		super.isLoaded();
	}

	@Step
	public DashboardPage search(String query) {
		LOG.info("searching query '{}'", query);
		driver.findElement(getLocator("search_field")).sendKeys(query, Keys.ENTER);
		return new DashboardPage(driver).get();
	}

}
