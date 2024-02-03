package com.tutorialsninja.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pageobjects.ProductComparisonPage;
import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;
import browser.setup.BaseTest;
import browser.setup.ConfigReader;

public class ComparisonTest extends BaseTest {

	CategoryRibbon catRib;

	@BeforeMethod
	public void setUp() {
		super.setUp();
	}

	@Test(priority=1,enabled=true)
	public void checkEmptyProductComparisonPage() {
		driver.get().get(new ConfigReader().loadDataProperties().getProperty("pdtCmpURL"));
		ProductComparisonPage cmpPage = new ProductComparisonPage(getDriver());
		Assert.assertEquals(true, cmpPage.emptyPdtPageElementIsDisplayed(new ConfigReader().loadDataProperties().getProperty("noPdt")));
	}

	@Test(priority=2,enabled=true)
	public void checkAfterAddingProductsToCmpPage() throws InterruptedException {
		driver.get().get(new ConfigReader().loadDataProperties().getProperty("pdtCmpURL"));
		ProductComparisonPage cmpPage = new ProductComparisonPage(getDriver());
		cmpPage.startComparison();
		cmpPage.addToCmpPageAndRedirectToCmpPage();	
		Assert.assertEquals(true, cmpPage.verifyAfterPdtAddition());
	}

	@Test(priority=3,enabled=true)
	public void addAndRemoveFromComparePageVeification() throws InterruptedException {
		driver.get().get(new ConfigReader().loadDataProperties().getProperty("pdtCmpURL"));
		ProductComparisonPage cmpPage = new ProductComparisonPage(getDriver());
		cmpPage.startComparison();
		cmpPage.addToCmpPageAndRedirectToCmpPage();	
		cmpPage.goToPdtComparisonPageAfterAddAndRemove();
		Assert.assertEquals(true, cmpPage.emptyPdtPageElementIsDisplayed(new ConfigReader().loadDataProperties().getProperty("noPdt")));
	}

}
