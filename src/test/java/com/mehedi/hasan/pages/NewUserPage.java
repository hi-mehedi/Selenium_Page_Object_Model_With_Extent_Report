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

public class NewUserPage extends commonMathod {
        
	ExtentTest test;
	
	public NewUserPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(), this);
		this.test = test;
	}
	@FindBys({@FindBy(xpath = "//*[@id=\"signInForm\"]/div[3]/a")})
	WebElement new_user;
	
	@FindBys({@FindBy(xpath = "//*[@id=\"firstname\"]")})
	WebElement firstname;
	
	@FindBys({@FindBy(xpath = "//*[@id=\"lastname\"]")})
	WebElement lastname;
	
	@FindBys({@FindBy(xpath = "//*[@id=\"username\"]")})
	WebElement userName;
	
	@FindBys({@FindBy(xpath = "//*[@id=\"password\"]")})
	WebElement password;
	
	@FindBys({@FindBy(xpath = "//*[@id=\"signupForm\"]/div[5]/input")})
	WebElement register;
	
	//Report
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
	
	public void newUser() throws IOException {
		try {
			test.info("Please Enter new user Button");
			if(new_user.isDisplayed()) {
				new_user.click();
				passCase("Successfully enter the button");
				try {
					test.info("Please enter Your First Name");
					if(firstname.isDisplayed()) {
						firstname.sendKeys("Mehedi Hasan");
						passCase("Done");
						try {
							test.info("Please enter Your Last Name");
							if(lastname.isDisplayed()) {
								lastname.sendKeys("Soumik");
								passCase("Done");
								try {
									test.info("Please enter Your user Name");
									if(userName.isDisplayed()) {
										userName.sendKeys("mehedi123");
										passCase("Done");
										try {
											test.info("Please enter Your password");
											if(password.isDisplayed()) {
												password.sendKeys("1234567");
												passCase("Done");
												try {
													test.info("Please enter Register Button");
													if(register.isDisplayed()) {
														register.click();
														passCaseWithSC("Your registration is done", "Registration_done");
													}
												} catch (Exception e) {
													// TODO: handle exception
													failCase("registration locator was no found", "registration_fail");
												}
											}
										} catch (Exception e) {
											// TODO: handle exception
											failCase("password locator was no found", "password_fail");

										}
									}
								} catch (Exception e) {
									// TODO: handle exception
									failCase("userName locator was no found", "userName_fail");
								}
								}
						} catch (Exception e) {
							// TODO: handle exception
							failCase("LastName locator was no found", "lastName_fail");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					failCase("FirstName locator was no found", "firstName_fail");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			failCase("New user locator was not found", "faild_New_user");
		}
	}
}
