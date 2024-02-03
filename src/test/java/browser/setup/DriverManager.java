package browser.setup;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import tutorialsninja.utils.Utilities;

public class DriverManager {
	WebDriver dr;

	public WebDriver setupBrowser(String browserName) {
		DesiredCapabilities dc = new DesiredCapabilities();
		if (browserName.equalsIgnoreCase("chrome")) {
			dc.setBrowserName("chrome");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			dc.setBrowserName("firefox");
		} else if (browserName.equalsIgnoreCase("edge")) {
			dc.setBrowserName("MicrosoftEdge");
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + browserName);
		}
		try {
			dr = new RemoteWebDriver(new URL("http://localhost:4444"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		dr.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		dr.get(ConfigReader.loadProperties().getProperty("url"));

		return dr;
	}

}
