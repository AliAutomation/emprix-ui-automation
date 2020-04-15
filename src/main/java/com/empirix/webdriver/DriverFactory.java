package com.empirix.webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empirix.utils.ConfigurationManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);

	private DriverFactory() {
	}

	/**
	 * Create WebDriver Instance
	 */
	public static WebDriver createInstance(String browser) {
		return createInstance(browser, false);
	}

	/**
	 * Create WebDriver Instance
	 */
	public static WebDriver createInstance(String browser, boolean isMobile) {

		WebDriver driver = null;

		// code for creating Chrome, Firefox or any other driver object
		switch (browser) {

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--window-size=1920,1080");
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if (isMobile) {
				Map<String, Object> emulation = new HashMap<>();
				emulation.put("deviceName", ConfigurationManager.getInstance().getProperty("mobileDevice"));
				options.setExperimentalOption("mobileEmulation", emulation);
			}
			driver = new ChromeDriver(options);
			break;
		}

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		LOG.info("creating webdriver {}", driver);
		return driver;
	}
}