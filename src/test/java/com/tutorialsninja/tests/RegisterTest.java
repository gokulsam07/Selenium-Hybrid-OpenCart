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
		registerPage.enterFirstName(ConfigReader.loadDataProperties().getProperty("firstName"));
		registerPage.enterLastName(ConfigReader.loadDataProperties().getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateTimeStamp());
		registerPage.enterTelephone(ConfigReader.loadDataProperties().getProperty("telephone"));
		registerPage.enterPassword(ConfigReader.loadDataProperties().getProperty("password"));
		registerPage.confirmPassword(ConfigReader.loadDataProperties().getProperty("password"));
		registerPage.agreePolicy();
		registerPage.save();
		Assert.assertEquals(registerPage.confirmationMessageIsDisplayed(), ConfigReader.loadDataProperties().getProperty("accountCreatedMessage"));


	}

	@Test(priority=2)
	public void RegisterWithPreRegisteredEmail() {

		RegisterPage registerPage = new RegisterPage(getDriver());
		registerPage.enterFirstName(ConfigReader.loadDataProperties().getProperty("firstName"));
		registerPage.enterLastName(ConfigReader.loadDataProperties().getProperty("lastName"));
		registerPage.enterEmail(ConfigReader.loadProperties().getProperty("username"));
		registerPage.enterTelephone(ConfigReader.loadDataProperties().getProperty("telephone"));
		registerPage.enterPassword(ConfigReader.loadDataProperties().getProperty("password"));
		registerPage.confirmPassword(ConfigReader.loadDataProperties().getProperty("password"));
		registerPage.agreePolicy();
		registerPage.save();
		Assert.assertEquals(registerPage.isErrorDisplayedForPreConfiguredMail(), ConfigReader.loadDataProperties().getProperty("emailPreConfigured"));


	}

	@Test(priority=3)
	public void RegisterWithoutMandatoryFields() {
		RegisterPage registerPage = new RegisterPage(getDriver());
		registerPage.save();

		Assert.assertEquals(registerPage.isErrorDisplayedForfirstName(), ConfigReader.loadDataProperties().getProperty("fnError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForlastName(), ConfigReader.loadDataProperties().getProperty("lnError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForEmail(), ConfigReader.loadDataProperties().getProperty("eError"));
		Assert.assertEquals(registerPage.isErrorDisplayedFortelephone(), ConfigReader.loadDataProperties().getProperty("tError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForpassword(), ConfigReader.loadDataProperties().getProperty("pasError"));
		Assert.assertEquals(registerPage.isErrorDisplayedForpolicy(), ConfigReader.loadDataProperties().getProperty("policyError"));
	}
}
