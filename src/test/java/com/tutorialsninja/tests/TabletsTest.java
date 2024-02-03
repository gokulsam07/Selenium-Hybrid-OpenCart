package com.tutorialsninja.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;
import com.tutorialsninja.pageobjects.IndividualCategoryPages.TabletsPage;

import browser.setup.BaseTest;


public class TabletsTest extends BaseTest {
	TabletsPage tabPage;

	@BeforeMethod
	public void setup() {
		CategoryRibbon catRib = new CategoryRibbon(getDriver());
		tabPage = catRib.selectTablets();

	}

	@Test(priority=1,enabled=true)
	public void checkIfTabletsPageIsDisplayed() {
		Assert.assertEquals(true, tabPage.verifyIfFocusInTabletsPage());
	}
	
	@Test(priority=2, enabled=true)
	public void checkIfListedProdctCountEqualsDisplayed(){
		Assert.assertEquals(true, tabPage.IsDisplayValueEqualsActualCount());
	}



}
