package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;
import com.tutorialsninja.pageobjects.IndividualCategoryPages.PhonesPage;
import browser.setup.BaseTest;

public class PhonesTest extends BaseTest {

	PhonesPage phonesPage;
	

	@BeforeMethod
	public void setup() {
	
		CategoryRibbon catRib = new CategoryRibbon(getDriver());
		phonesPage = catRib.selectPhones();

	}

	@Test(priority=1,enabled=true)
	public void checkIfTabletsPageIsDisplayed() {
		Assert.assertEquals(true, phonesPage.verifyIfFocusInPhonesPage());
	}
	
	@Test(priority=2, enabled=true)
	public void checkIfListedProdctCountEqualsDisplayed(){
		Assert.assertEquals(true, phonesPage.IsDisplayValueEqualsActualCount());
	}
}
