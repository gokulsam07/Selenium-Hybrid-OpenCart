package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.StorePage;
import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;

import browser.setup.BaseTest;
import browser.setup.ConfigReader;

public class ComponentsTest extends BaseTest {


	private CategoryRibbon catRib;
	private HomePage homePage;

	@BeforeMethod
	public void setUp() {
		super.setUp();
		catRib= new CategoryRibbon(getDriver());
	}

	@Test(priority=1)
	public void miceTest() {
	 homePage = catRib.selectComponents().selectComponentType(ConfigReader.loadDataProperties().getProperty("c1")).clickOnContinue();
		Assert.assertEquals(true, homePage.isSliderVisible());

	}

	@Test(priority=2)
	public void monitorTest() {
		Assert.assertEquals(true, catRib.selectComponents().selectComponentType(ConfigReader.loadDataProperties().getProperty("c2")).isProductDisplayed(ConfigReader.loadDataProperties().getProperty("apple")));
	}
	@Test(priority=3)
	public void printerTest() {
		 homePage =catRib.selectComponents().selectComponentType(ConfigReader.loadDataProperties().getProperty("c4")).clickOnContinue();
		Assert.assertEquals(true, homePage.isSliderVisible());
	}

	@Test(priority=4)
	public void scannerTest() {
		 homePage =catRib.selectComponents().selectComponentType(ConfigReader.loadDataProperties().getProperty("c4")).clickOnContinue();
		Assert.assertEquals(true, homePage.isSliderVisible());
	}
	@Test(priority=5)
	public void webCameraTest() {
		 homePage =catRib.selectComponents().selectComponentType(ConfigReader.loadDataProperties().getProperty("c5")).clickOnContinue();
		Assert.assertEquals(true, homePage.isSliderVisible());
	}

	@Test(priority=6)
	public void checkIfAllComponentsIsDisplayed() {
		StorePage allComponents =(StorePage) catRib.selectComponents().selectComponentType(ConfigReader.loadDataProperties().getProperty("c6"));
		Assert.assertEquals(true, allComponents.isAllComponentsDisplayed());
	}
}

