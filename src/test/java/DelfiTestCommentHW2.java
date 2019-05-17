import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class DelfiTestCommentHW2 {
    private final String URL = "http://delfi.lv";
    private final By HOME_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By HOME_PAGE_ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By COMMENT_PAGE_COMMENT_COUNT = By.xpath(".//span[contains(@class, 'type-cnt')]");

    private WebDriver driver;

    @Test
    public void commentCountCheck() {

        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        String commentCountHomePage = getElement(HOME_PAGE_COMMENT_COUNT).getText();
        int commentCountHomePageDigits = setDigits(commentCountHomePage);

        WebElement homePageTitle = getElement(HOME_PAGE_ARTICLE_TITLE);

        homePageTitle.click();

        String commentCountArticlePage = getElement(ARTICLE_PAGE_COMMENT_COUNT).getText();
        int commentCountArticlePageDigits = setDigits(commentCountArticlePage);

        Assertions.assertEquals(commentCountHomePageDigits, commentCountArticlePageDigits, "Comment count is wrong.");

        WebElement articlePageCommentCount = getElement(ARTICLE_PAGE_COMMENT_COUNT);
        articlePageCommentCount.click();

        List<WebElement> commentCountsCommentPage = driver.findElements(COMMENT_PAGE_COMMENT_COUNT);
        String commentCountAnonymous = commentCountsCommentPage.get(0).getText();
        String commentCountRegistered = commentCountsCommentPage.get(1).getText();

        int commentCountAnonymousDigits = setDigits(commentCountAnonymous);
        int commentCountRegisteredDigits = setDigits(commentCountRegistered);

        Assertions.assertEquals(commentCountHomePageDigits, commentCountAnonymousDigits + commentCountRegisteredDigits, "Comment count is wrong.");
    }

    private Integer setDigits (String commentcount){
        commentcount = commentcount.substring(1, commentcount.length() - 1);
        return Integer.valueOf(commentcount);
    }

    private WebElement getElement (By locator) {
        return driver.findElement(locator);
    }

   @AfterEach
    public void closeBrowser() {
        driver.close();
    }
}
