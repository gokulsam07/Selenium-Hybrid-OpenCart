package browser.setup;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import tutorialsninja.utils.Utilities;

public class DriverManager {
	WebDriver dr;
	 public WebDriver setupBrowser(String browserName) {
	        

	        if (browserName.equalsIgnoreCase("chrome")) {
	            dr = new ChromeDriver();
	        } else if (browserName.equalsIgnoreCase("firefox")) {
	            dr = new FirefoxDriver();
	        } else if (browserName.equalsIgnoreCase("edge")) {
	            dr = new EdgeDriver();
	        } else {
	            throw new IllegalArgumentException("Invalid browser name: " + browserName);
	        }

	        dr.manage().window().maximize();
	        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
	        dr.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
	        dr.get(ConfigReader.loadProperties().getProperty("url"));

	        return dr;
	    }

}
