package com.mehedi.hasan.tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mehedi.hasan.drivers.PageDriver;
import com.mehedi.hasan.pages.LoginPage;
import com.mehedi.hasan.utilities.ExtentFactory;
import com.mehedi.hasan.utilities.commonMathod;



public class LoginTest extends commonMathod{
	
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	@BeforeClass
	public void openUrl() throws InterruptedException {
		PageDriver.getCurrentDriver().get(url);
		timeout(2000);
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:13px\"><b>LOGIN</b></p>").assignAuthor("QA Team").assignDevice("Windows");
		
	}
	
	@Test (priority = 0)
	public void loginIntoShop() throws IOException {
		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>User Login</b></p>");
		LoginPage loginPage = new LoginPage(childTest);
		loginPage.login();
	}
	
//	@Test (priority = 1)
//	public void createNewUser() throws IOException {
//		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>New User</b></p>");
//		LoginPage loginPage = new LoginPage(childTest);
//		loginPage.createUser();
//	}
	
	@AfterClass
	public void report() {
		report.flush();
	}
	
}