package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.RegisterPage;
import browser.setup.BaseTest;
import browser.setup.ConfigReader;
import tutorialsninja.utils.Utilities;


public class RegisterTest extends BaseTest {


	@BeforeMethod
	public void setup() {
		HomePage homePage = new HomePage(getDriver());
		homePage.ClickOnMyAccount();
		homePage.ClickOnRegister();

	}
	
	@Test(priority=1)
	public void RegisterWithMandatoryFields() {
		RegisterPage registerPage = new RegisterPage(getDriver());
		registerPage.enterFirstName(new ConfigReader().loadDataProperties().getProperty("firstName"));
		registerPage.enterLastName(new ConfigReader().loadDataProperties().getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateTimeStamp());
		registerPage.enterTelephone(new ConfigReader().loadDataProperties().getProperty("telephone"));
		registerPage.enterPassword(new ConfigReader().loadDataProperties().getProperty("password"));
		registerPage.confirmPassword(new ConfigReader().loadDataProperties().getProperty("password"));
		registerPage.agreePolicy();
		registerPage.save();
		Assert.assertEquals(registerPage.confirmationMessageIsDisplayed(), new ConfigReader().loadDataProperties().getProperty("accountCreatedMessage"));


	}

	@Test(priority=2)
	public void RegisterWithPreRegisteredEmail() {

		RegisterPage registerPage = new RegisterPage(getDriver());
		registerPage.enterFirstName(new ConfigReader().loadDataProperties().getProperty("firstName"));
		registerPage.enterLastName(new ConfigReader().loadDataProperties().getProperty("lastName"));
		registerPage.enterEmail(new ConfigReader().loadProperties().getProperty("username"));
		registerPage.enterTelephone(new ConfigReader().loadDataProperties().getProperty("telephone"));
		registerPage.enterPassword(new ConfigReader().loadDataProperties().getProperty("password"));
		registerPage.confirmPassword(new ConfigReader().loadDataProperties().getProperty("password"));
		registerPage.agreePolicy();
		registerPage.save();
		Assert.assertEquals(registerPage.isErrorDisplayedForPreConfiguredMail(), new ConfigReader().loadDataProperties().getProperty("emailPreConfigured"));


	}

	@Test(priority=3)
	public void RegisterWithoutMandatoryFields() {
		RegisterPage registerPage = new RegisterPage(getDriver());
		registerPage.save();

		Assert.assertEquals(registerPage.isErrorDisplayedForfirstName(), new ConfigReader().loadDataProperties().getProperty("fnError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForlastName(), new ConfigReader().loadDataProperties().getProperty("lnError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForEmail(), new ConfigReader().loadDataProperties().getProperty("eError"));
		Assert.assertEquals(registerPage.isErrorDisplayedFortelephone(), new ConfigReader().loadDataProperties().getProperty("tError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForpassword(), new ConfigReader().loadDataProperties().getProperty("pasError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForpolicy(), new ConfigReader().loadDataProperties().getProperty("policyError"));
	}
}
