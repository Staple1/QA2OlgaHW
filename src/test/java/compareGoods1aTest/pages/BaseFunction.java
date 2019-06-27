package compareGoods1aTest.pages;

import compareGoods1aTest.CompareGoodsTest;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;



public class BaseFunction {

    public WebDriver driver;

    public BaseFunction() {
        logger("We are starting our test");
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public void logger(String message) {
        org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CompareGoodsTest.class);
        LOGGER.info(message);
    }

    public void goToUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
        logger("Opening main page");
    }

    public void testStap(By locator1, By locator2, String LINK_NAME) {
        List<WebElement> findElements = driver.findElement(locator1).findElements(locator2);

        for (int i = 0; i < findElements.size(); i++) {
            WebElement element = findElements.get(i);
            String title = element.getText();
            String currentTitle;
            if (!(title.indexOf('(') == -1)) {
                currentTitle = title.substring(0, title.indexOf('('));
            } else {
                currentTitle = title;
            }
            if (currentTitle.equals(LINK_NAME)) {
                element.click();
                break;
            }
        }
    }

    public WebElement getElement(By locator) {
        WebElement element = driver.findElement(locator);
        return element;
    }

    public List<WebElement> getElements(By locator1, By locator2) {
        List<WebElement> elements = driver.findElement(locator1).findElements(locator2);
        return elements;
    }

    public List<WebElement> getElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }

    public void closeBrowser () {
        Assertions.assertNotNull(driver, "There is no opened window!" );
        driver.close();
    }

}

