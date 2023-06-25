package com.tutorialsninja.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;
import com.tutorialsninja.pageobjects.IndividualCategoryPages.SoftwarePage;


import browser.setup.BaseTest;

public class SoftwaresTest extends BaseTest {

	SoftwarePage softwaresPage;
	

	@BeforeMethod
	public void setup() {
		CategoryRibbon catRib = new CategoryRibbon(getDriver());
		 softwaresPage = catRib.selectSoftwares();

	}

	@Test(priority=1,enabled=true)
	public void checkIfTabletsPageIsDisplayed() {
		Assert.assertEquals(true, softwaresPage.verifyIfFocusInSoftwarePage());
	}
	
	@Test(priority=2, enabled=true)
	public void checkIfListedProdctCountEqualsDisplayed(){
		Assert.assertEquals(true, softwaresPage.IsDisplayValueEqualsActualCount());
	}


}
