package com.empirix.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class DashboardPage extends AbstractBasePage<DashboardPage> {

	public DashboardPage(WebDriver driver) {
		super(driver, "homepage-results.properties");
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(wait(urlContains("search?"), 5000), "url mismatch");
	}

	@Step
	public DashboardPage nextPage() {
		LOG.info("navigating to next page");
		driver.findElement(getLocator("dashborad-tab.xpath")).isDisplayed();
		return this;
	}

}
