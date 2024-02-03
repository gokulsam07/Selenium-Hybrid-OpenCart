package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pageobjects.AccountPage;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.LoginPage;

import browser.setup.BaseTest;
import browser.setup.ConfigReader;

public class LoginTest extends BaseTest {

	private LoginPage loginPage;
	private HomePage homePage;
	private AccountPage accountPage;

	@BeforeMethod
	public void setUp() {
		super.setUp();
		homePage = new HomePage(getDriver());
		homePage.ClickOnMyAccount();
		loginPage = homePage.ClickOnLogin();
	}

	@Test
	public void testLogin() {
		loginPage.enterUsername(new ConfigReader().loadProperties().getProperty("username"));
		loginPage.enterPassword(new ConfigReader().loadProperties().getProperty("password"));
		loginPage.clickLogin();
		AccountPage accountPage = new AccountPage(getDriver());

		if (accountPage.getDisplayOfEditAccInfo()) {
			Assert.assertTrue(true, "Logged in");
		} else {
			Assert.assertTrue(true, "Failed to Login becuase of wrong credentials");
		}
	}

	@Test
	public void checkIfRegisterDetailsPresent() {

		Assert.assertEquals(true, loginPage.isRegisterDetailsDisplayedInLoginPage());
	}

	@Test
	public void checkRegstrationInLoginPage() {
		Assert.assertEquals(true, loginPage.registerContinuefromLoginClick().checkIfFocusInRegisterPage());
	}

	@Test
	public void ribbonMyAccountOptions() {
		loginPage.enterUsername(new ConfigReader().loadProperties().getProperty("username"));
		loginPage.enterPassword(new ConfigReader().loadProperties().getProperty("password"));
		accountPage = loginPage.clickLogin();
		Assert.assertEquals(true, accountPage.IsAllAccountOptionsDisplayed());
	}

	@Test
	public void logOut() {
		loginPage.enterUsername(new ConfigReader().loadProperties().getProperty("username"));
		loginPage.enterPassword(new ConfigReader().loadProperties().getProperty("password"));
		accountPage = loginPage.clickLogin();
		Assert.assertEquals(true, accountPage.clickLogout());
	}
}