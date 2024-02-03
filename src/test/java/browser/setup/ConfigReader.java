package browser.setup;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	public Properties prop;
	public Properties dataProp;
	
	public ConfigReader() {
		prop = new Properties();
		dataProp = new Properties();
    }

    public Properties loadProperties() {
        try {
            File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\browser\\setup\\config.properties");
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public  Properties loadDataProperties() {
        try {
            File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\testdata\\testdata.properties");
            FileInputStream fis = new FileInputStream(file);
            dataProp.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataProp;
    }

}
