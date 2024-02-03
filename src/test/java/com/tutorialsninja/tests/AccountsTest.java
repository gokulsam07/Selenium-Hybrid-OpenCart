package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pageobjects.AccountPage;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.LoginPage;
import com.tutorialsninja.pageobjects.accountsPageObjects.ChangePasswordPage;
import com.tutorialsninja.pageobjects.accountsPageObjects.EditAccInfoPage;
import com.tutorialsninja.pageobjects.accountsPageObjects.NewsLetterPage;

import browser.setup.BaseTest;
import browser.setup.ConfigReader;

public class AccountsTest extends BaseTest {

	private AccountPage accountPage;
	private LoginPage loginPage;
	private NewsLetterPage newsLetterPage;
	private EditAccInfoPage editAccInfoPage;
	private ChangePasswordPage changePasswordPage;


	@BeforeMethod
	public void setUp() {
		super.setUp();
		HomePage homePage = new HomePage(getDriver());
		homePage.ClickOnMyAccount();
		loginPage = homePage.ClickOnLogin();
		loginPage.enterUsername(new ConfigReader().loadProperties().getProperty("username"));
		loginPage.enterPassword(new ConfigReader().loadProperties().getProperty("password"));
		accountPage = loginPage.clickLogin();
	}


	@Test(priority=1)
	public void subscriptionTest() {
		newsLetterPage = accountPage.selectNewsLetter();
		Assert.assertEquals(true, newsLetterPage.isFocusInNewsLetterPage());
		Assert.assertEquals(true, newsLetterPage.selectSusbsciption("yes"));
	}

	@Test(priority=2)
	public void editAccountInformation() {
		editAccInfoPage = accountPage.clickEditAccountInfo();
		Assert.assertEquals("Success: Your account has been successfully updated.",  editAccInfoPage.editLastName());
	}

	@Test(priority=3)
	public void editAccountInformationWithoutChange() {
		editAccInfoPage = accountPage.clickEditAccountInfo();
		Assert.assertNotEquals("Success: Your account has been successfully updated.",  editAccInfoPage.editLastName());
	}

	@Test(priority=4)
	public void updateExistingPassword(){
		changePasswordPage = accountPage.clickChangepassword();
		Assert.assertNotEquals("Success: Your password has been successfully updated.",  changePasswordPage.changePassword());
	}

	@Test(priority=5)
	public void noValueForPwd(){
		changePasswordPage = accountPage.clickChangepassword();
		Assert.assertEquals("Password must be between 4 and 20 characters!",  changePasswordPage.clickCtdDirectly());
	}

	@Test(priority=6)
	public void noValueForConfirmPwd(){
		changePasswordPage = accountPage.clickChangepassword();
		Assert.assertEquals("Password confirmation does not match password!",  changePasswordPage.leaveConfirmPwdEmpty());
	}

	@Test(priority=7)
	public void notMatchingPwd(){
		changePasswordPage = accountPage.clickChangepassword();
		Assert.assertEquals("Password confirmation does not match password!",  changePasswordPage.notMatchingPwd());
	}
}