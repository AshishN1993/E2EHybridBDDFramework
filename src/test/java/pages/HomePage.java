package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {
    private WebDriver driver;


    public HomePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }


    public String getTitle() {
        return driver.getTitle();
    }


    public void open(String url) {
        driver.get(url);
    }
}
