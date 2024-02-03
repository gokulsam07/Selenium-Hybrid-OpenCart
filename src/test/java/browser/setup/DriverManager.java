package browser.setup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import tutorialsninja.utils.Utilities;

public class DriverManager {
	WebDriver dr;
	private int portNum;

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
			String runMode = new ConfigReader().loadDataProperties().getProperty("run");
			if (runMode.equals("local")) {
				portNum = 9515;
				String exePath = System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe";
				runChromeDriverInLocal(exePath);
			} else {
				portNum = 4444;
			}
			dr = new RemoteWebDriver(new URL("http://localhost:" + portNum), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		dr.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		dr.get(new ConfigReader().loadProperties().getProperty("url"));

		return dr;
	}

	private void runChromeDriverInLocal(String path) {

		try {
			if (!checkDriverAvailability()) {
				Runtime.getRuntime().exec(path);
			} else {
				System.err.println("*****************chrome driver is already up and running****************");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkDriverAvailability() throws IOException {
		Process process = Runtime.getRuntime().exec("tasklist");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			return reader.lines().anyMatch(line -> line.contains("chrome.exe"));
		}
	}
}
