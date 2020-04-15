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
	public DashboardPage search(String username,String password) {
		LOG.info("User enter username and password '{}'", username,password);
		driver.findElement(getLocator("username_field.xpath")).sendKeys(username);
		driver.findElement(getLocator("password_field.xpath")).sendKeys(password);
		driver.findElement(getLocator("signin_button.xpath")).click();
		return new DashboardPage(driver).get();
	}

}
