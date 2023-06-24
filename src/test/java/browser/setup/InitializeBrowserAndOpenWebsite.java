package browser.setup;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tutorialsninja.utils.Utilities;

public class InitializeBrowserAndOpenWebsite {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Properties prop;
    public Properties dataProp;

    public Properties loadProperties() {
        prop = new Properties();
        try {
            File file = new File("F:\\Selenium-Hybrid-OpenCart\\src\\test\\java\\browser\\setup\\config.properties");
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public Properties loadDataProperties() {
        dataProp = new Properties();
        try {
            File file = new File("F:\\Selenium-Hybrid-OpenCart\\src\\test\\java\\testdata\\testdata.properties");
            FileInputStream fis = new FileInputStream(file);
            dataProp.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataProp;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    
    
    public WebDriver setupBrowser(String browserName) {
        WebDriver dr;

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
        dr.get(prop.getProperty("url"));

        driver.set(dr);
        return driver.get();
    }
}
