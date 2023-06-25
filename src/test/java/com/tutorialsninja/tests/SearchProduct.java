package com.tutorialsninja.tests;



import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.StorePage;
import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;

import browser.setup.BaseTest;
import browser.setup.ConfigReader;

public class SearchProduct extends BaseTest{

	@BeforeMethod
	public void setUp() {
		super.setUp();
	}
	
	@Test(priority=1)
	public void searchandFindMac() {

		CategoryRibbon catRib = new CategoryRibbon(getDriver());
		catRib.selectDesktop();
		catRib.selectMacList();
		Assert.assertTrue(catRib.isMacProductDisplayed());
	}

	@Test(priority=2)
	public void searchInvalid() {
		HomePage homePage = new HomePage(getDriver());
		StorePage storePage = new StorePage(getDriver());
		homePage.EnterProductName(ConfigReader.loadDataProperties().getProperty("product"));
		homePage.clickSearchButton();
		Assert.assertTrue(storePage.noProductAvailableIsDisplayed(), "No product available");
	}


}
