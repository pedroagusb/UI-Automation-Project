package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utils.WebDriverUtils.driver;

public class BrowserFactory {

    private BrowserFactory(){

    }

    private static final Logger log = LogManager.getLogger(BrowserFactory.class);
    private static final List<String> DEFAULT_OPTIONS = new ArrayList<>();

    public static WebDriver getBrowserDriver(String browserName) {
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
            default: {
                log.fatal("Browser don't detected");
                throw new NoSuchElementException("Not Supported Browser:" + browserName);
            }
        }

        log.info("Browser selected. Driver created successfully");
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

        log.info("Chrome options created");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(DEFAULT_OPTIONS);

        log.info("Firefox options created");
        return options;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
