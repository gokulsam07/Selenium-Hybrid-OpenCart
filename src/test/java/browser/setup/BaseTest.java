package browser.setup;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	DriverManager drm;

	@BeforeMethod
	public void setUp() {
		drm = new DriverManager();
		driver.set(drm.setupBrowser(ConfigReader.loadProperties().getProperty("browserName")));
	}

	@AfterMethod
	public void tearDown() {
		driver.get().quit();
		driver.remove();
	}

	public WebDriver getDriver() {
		return driver.get();
	} 
}





