package org.rjpasive.appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class AppTest {

	AndroidDriver<WebElement> driver;

	@Before
	public void setup() throws MalformedURLException {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "blablablaaa");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");

		URL url = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver<>(url, capabilities);

		System.out.println("before");
	}

	@Test
	public void testApp() throws InterruptedException {
		driver.get("http://mvnrepository.com/");

		driver.findElement(By.id("query")).sendKeys("Appium");

		WebElement regBtn = driver.findElement(By.cssSelector("#search input[type='submit'"));

		System.out.println(regBtn.getTagName());
		System.out.println(regBtn.getText());

		regBtn.click();

		System.out.println("Title: " + driver.getTitle());

		TimeUnit.SECONDS.sleep(10);
	}

	@After
	public void close() {
		if (driver != null) {
			driver.quit();
		}
	}
}
