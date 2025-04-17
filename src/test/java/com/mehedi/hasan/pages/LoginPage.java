package com.mehedi.hasan.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.mehedi.hasan.drivers.PageDriver;
import com.mehedi.hasan.utilities.GetScreenshot;
import com.mehedi.hasan.utilities.commonMathod;


public class LoginPage extends commonMathod {

	/*
	 * Locators Methods
	 */

	ExtentTest test;
	
	public LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(), this);
		this.test = test;
	}

	@FindBys({ @FindBy(xpath = "//*[@id=\"email\"]") })
	WebElement userEmail;

	@FindBys({ @FindBy(xpath = "//*[@id=\"password\"]") })
	WebElement password;

	@FindBys({ @FindBy(xpath = "//*[@id=\"signInForm\"]/div[3]/input")
			 })
	WebElement signIn;
	

	// Report
	public void passCase(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
	}

	@SuppressWarnings("unused")
	public void passCaseWithSC(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}

	// Fail
	@SuppressWarnings("unused")
	public void failCase(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDriver().quit();
	}

	public void login() throws IOException {
		try {
			test.info("Please enter your email address");
			if (userEmail.isDisplayed()) {
				userEmail.sendKeys("mehedi@gmail.com");
				passCase("You have successfully entered your email");
				timeout(3000);
				try {
					test.info("Please enter your password");
					if (password.isDisplayed()) {
						password.sendKeys("123456");
						passCase("You have successfully entered your password");
						timeout(3000);
						try {
							test.info("Click on Sign In");
							if (signIn.isDisplayed()) {
								signIn.click();
								timeout(10000);
								passCaseWithSC("You have successfully clicked on Sign In button", "login_pass");
							}
						} catch (Exception e) {
							failCase("Sign In button locator was not found", "login_fail");
						}
					}
				} catch (Exception e) {
					failCase("Password locator was not found", "pass_fail");
				}
			}
		} catch (Exception e) {
			failCase("User email locator was not found", "user_fail");
		}
	}

	

}