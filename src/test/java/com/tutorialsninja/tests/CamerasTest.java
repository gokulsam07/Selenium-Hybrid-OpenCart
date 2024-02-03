package com.tutorialsninja.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pageobjects.CategoryRibbon.CategoryRibbon;
import com.tutorialsninja.pageobjects.IndividualCategoryPages.CameraPage;
import browser.setup.BaseTest;


public class CamerasTest extends BaseTest {
	CameraPage cameraPage;
	
	@BeforeMethod
	public void setup() {
		super.setUp();
		CategoryRibbon catRib = new CategoryRibbon(getDriver());
		cameraPage = catRib.selectCameras();

	}

	@Test(priority=1,enabled=true)
	public void checkIfTabletsPageIsDisplayed() {
		Assert.assertEquals(true, cameraPage.verifyIfFocusInCameraPage());
	}
	
	@Test(priority=2, enabled=true)
	public void checkIfListedProdctCountEqualsDisplayed(){
		Assert.assertEquals(true, cameraPage.IsDisplayValueEqualsActualCount());
	}

}
