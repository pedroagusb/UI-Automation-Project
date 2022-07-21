package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class BrowserFactory {
    private static WebDriver driver = null;
    private static ChromeDriverService serviceChrome;
    private static GeckoDriverService serviceFirefox;

    public static WebDriver getBrowserDriver(String browserName) throws Exception {
        switch (browserName){
            case "firefox":{
                WebDriverManager wdm = WebDriverManager.firefoxdriver();
                wdm.setup();

                serviceFirefox = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(wdm.getDownloadedDriverPath()))
                        .usingAnyFreePort()
                        .build();
                serviceFirefox.start();

                //*************************************************************************************

                driver = new RemoteWebDriver(serviceFirefox.getUrl(), new DesiredCapabilities());
                driver.manage().window().maximize();
                break;
            }
            case "chrome": {

                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                WebDriverManager wdm = WebDriverManager.chromedriver();
                wdm.setup();
                serviceChrome = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(wdm.getDownloadedDriverPath()))
                        .usingAnyFreePort()
                        .build();
                serviceChrome.start();

                //*************************************************************************************
                driver = new RemoteWebDriver(serviceChrome.getUrl(), new DesiredCapabilities());
                driver.manage().window().maximize();

                break;
            }
                default:
                    //If no browser passed throw exception
                    throw new Exception("Not Supported Browser.");
        }
        return driver;
    }
}
