package browser.setup;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;
	private static Properties dataProp;

    public static Properties loadProperties() {
        prop = new Properties();
        try {
            File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\browser\\setup\\config.properties");
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static Properties loadDataProperties() {
        dataProp = new Properties();
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
