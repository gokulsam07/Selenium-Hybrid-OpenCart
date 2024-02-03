package browser.setup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseTest {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	DriverManager drm;

	@BeforeSuite
	public void cleanUpResources() {
		killChromeDriverProcesses();
		String screenshotLocation = System.getProperty("user.dir") + File.separator + "target" + File.separator
				+ "reports" + File.separator + "tests";
		try {
			FileUtils.deleteDirectory(new File(screenshotLocation));
		} catch (Exception e) {
			System.out.println("Unable to delete the folder or folder not found");
		}
	}

	@AfterSuite
	public void cleanUpResourcesPostCompletion() {
		killChromeDriverProcesses();
	}

	@BeforeMethod
	public void setUp() {
		drm = new DriverManager();
		driver.set(drm.setupBrowser(ConfigReader.loadProperties().getProperty("browserName")));
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		if (result.getStatus() == 2) {
			String relativeLoc = result.getMethod().getMethodName();
			File screenshotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String screenshotPath = System.getProperty("user.dir") + File.separator + "target" + File.separator
					+ "reports" + File.separator + "tests" + File.separator + relativeLoc + ".png";
			try {
				FileUtils.moveFile(screenshotFile, new File(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Reporter.log("<a href='" + screenshotPath + "'><img src='file://" + screenshotPath
					+ "' width='822' height='404'/></a>");
		}
		driver.get().quit();
		driver.remove();
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	private void killChromeDriverProcesses() {
		try {
			ProcessBuilder b1 = new ProcessBuilder("taskkill", "/F", "/IM", "chromedriver.exe");
			ProcessBuilder b2 = new ProcessBuilder("taskkill", "/F", "/IM", "chrome.exe");
			System.out.println("chrome driver is killed");
			b1.start();
			b2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
