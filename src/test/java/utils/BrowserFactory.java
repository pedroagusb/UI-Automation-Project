package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;

public class BrowserFactory {

    private static final List<String> DEFAULT_OPTIONS = new ArrayList<>();

    public static WebDriver getBrowserDriver(String browserName) throws Exception {
        WebDriver driver;

        switch (browserName.toLowerCase()){
            case "firefox":{
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);

                break;
            }
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = getChromeOptions();
                driver = new ChromeDriver(chromeOptions);

                break;
            }
            default:
                //If no browser passed throw exception
                throw new Exception("Not Supported Browser:"+browserName);
        }
        return driver;
    }

    static {
        DEFAULT_OPTIONS.add("--no-sandbox");
        DEFAULT_OPTIONS.add("--disable-dev-shm-usage");
        DEFAULT_OPTIONS.add("--disable-extensions");
        DEFAULT_OPTIONS.add("--disable-gpu");
        DEFAULT_OPTIONS.add("--start-maximized");
    }

    private static ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments(DEFAULT_OPTIONS);

        return options;
    }

    private static FirefoxOptions getFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(DEFAULT_OPTIONS);

        return options;
    }
}
