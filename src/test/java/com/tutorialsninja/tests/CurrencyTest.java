package com.tutorialsninja.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pageobjects.HomePage;
import browser.setup.BaseTest;

public class CurrencyTest extends BaseTest {

	private HomePage homePage;


	@BeforeMethod
	public void setup() {
		homePage = new HomePage(getDriver());
	}

	@Test(priority=1)
	public void changeCurrencyToUSD_Verify() {
		Assert.assertEquals(true, homePage.selectCurrency("USD"));
	}
	@Test(priority=2)
	public void changeCurrencyToEuro_Verify() {
		Assert.assertEquals(true, homePage.selectCurrency("Euro"));
	}
	@Test(priority=3)
	public void changeCurrencyToPound_Verify() {
		Assert.assertEquals(true, homePage.selectCurrency("Pound"));
	}
	@Test(priority=4)
	public void changeCurrencyToUSDVerifyInPdtListing() {
		homePage.selectCurrency("USD");
		Assert.assertEquals(true, homePage.verifyInPdtListing("$"));

	}
	@Test(priority=5)
	public void changeCurrencyToEuroVerifyInPdtListing() {
		homePage.selectCurrency("Euro");
		Assert.assertEquals(true, homePage.verifyInPdtListing("€"));
	}
	@Test(priority=6)
	public void changeCurrencyToPoundVerifyInPdtListing() {
		homePage.selectCurrency("Pound");
		Assert.assertEquals(true, homePage.verifyInPdtListing("£"));
	}

}
