package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.StorePage;
import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;
import browser.setup.BaseTest;
import browser.setup.ConfigReader;

public class Laptops_NotebooksTest extends BaseTest {


	private CategoryRibbon catRib;
	private HomePage homePage;

	@BeforeMethod
	public void setUp() {
		super.setUp();
		catRib= new CategoryRibbon(getDriver());
	}


	@Test(priority=1,enabled=true)
	public void macTest() {
		homePage = catRib.selectLaptopAndNoteBook().selectlaptopOrNotebookType(ConfigReader.loadDataProperties().getProperty("nb1")).clickOnContinue();
		Assert.assertEquals(true, homePage.isSliderVisible());

	}

	@Test(priority=2,enabled=true)
	public void windowsTest() {
		homePage = catRib.selectLaptopAndNoteBook().selectlaptopOrNotebookType(ConfigReader.loadDataProperties().getProperty("nb2")).clickOnContinue();
		Assert.assertEquals(true, homePage.isSliderVisible());
	}

	@Test(priority=3,enabled=true)
	public void checkIfLap_NotebookPageIsDisplayed() {
		StorePage allNotebooks =(StorePage) catRib.selectLaptopAndNoteBook().selectlaptopOrNotebookType(ConfigReader.loadDataProperties().getProperty("nb3"));
		Assert.assertEquals(true, allNotebooks.isAllLaptopsNotebboksDisplayed());
	}

}
