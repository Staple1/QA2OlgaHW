package pageObjectTVnetHW5.pages;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjectTVnetHW5.TvnetTest;

import java.util.List;

public class BaseFunc {

    private WebDriver driver;

    public BaseFunc() {
        logger("We are starting our test");
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public void logger (String message){
        org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TvnetTest.class);
        LOGGER.info(message);
    }

    public void goToUrl (String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
        logger("Opening main page");
    }

    public List<WebElement> getElements (By locator) {
        return driver.findElements(locator);
    }

    public Integer setDigits(String commentCount) {
        commentCount = commentCount.substring(1, commentCount.length() - 1);
        int commentCountDigits = Integer.valueOf(commentCount);
        return commentCountDigits;
    }

    public WebElement getElement (By locator) {
        return driver.findElement(locator);
    }

    public void closeBrowser () {
            Assertions.assertNotNull(driver, "There is no opened window!" );
            driver.close();
    }

}
