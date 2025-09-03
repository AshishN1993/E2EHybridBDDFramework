package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    // ThreadLocal to support parallel runs
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();


    public static void initDriver() {
        String browserFromJenkins = System.getProperty("browser", "chrome");
        String crossBrowser = System.getProperty("crossBrowser", "true");


// If crossBrowser contains comma separated browsers, framework can be extended to iterate. For simplicity, pick first when not cross.
        String browser = browserFromJenkins.split(",")[0].trim();


        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
        }
        tlDriver.set(driver);
    }


    public static WebDriver getDriver() {
        return tlDriver.get();
    }


    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}