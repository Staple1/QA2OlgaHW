import model.Article;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

public class ArticleAndCommentTestHW3 {
    private final String URL = "http://rus.delfi.lv";
    private final int ARTICLE_NUMBER = 3;

    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.tagName("h1");
    private final By COMMENT_COUNTER = By.xpath(".//a[contains(@class, 'text-red')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//div[contains(@class, 'article-title')]");
    private final By COMMENT_PAGE_TITLE = By.xpath(".//h1[@class = 'article-title']/a");
    private final By COMMENT_PAGE_COMMENT_COUNT = By.xpath(".//span[contains(@class, 'type-cnt')]");

    private static Logger LOGGER = LogManager.getLogger(ArticleAndCommentTestHW3.class);

    private WebDriver driver;

    @Test
    public void articleAndCommentCountTest () {
        LOGGER.info("We are starting our test.");
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        List<WebElement> articles = driver.findElements(ARTICLE);

        Article article = getArticle(articles, ARTICLE_NUMBER-1);

        String homePageArticleTitle = article.getTitle();
        Integer homePageCommentCount = article.getCommentCount();
        LOGGER.info("We found an article.");
        articles.get(ARTICLE_NUMBER-1).click();
        LOGGER.info("We have moved to the article page");

        List<WebElement> articlePageTitles = driver.findElements(ARTICLE_PAGE_TITLE);

        Article articlePage = getArticle(articlePageTitles, 0);
        String articlePageTitle = articlePage.getTitle();
        LOGGER.info("We found an article title on the article page.");
        Integer articlePageCommentCount = articlePage.getCommentCount();
        LOGGER.info("We found a comments count on the article page.");

        Assertions.assertEquals(homePageCommentCount,articlePageCommentCount, "Comment count is wrong");
        Assertions.assertEquals(homePageArticleTitle,articlePageTitle, "Title is wrong");

        if (articlePageCommentCount > 0) {

            WebElement articlePageCommentCountLink = driver.findElement(COMMENT_COUNTER);
            articlePageCommentCountLink.click();
            LOGGER.info("We have moved to the comments page");

            String commentPageTitle = driver.findElement(COMMENT_PAGE_TITLE).getText();
            LOGGER.info("We found an article title on the comments page.");

            List<WebElement> commentCountsCommentPage = driver.findElements(COMMENT_PAGE_COMMENT_COUNT);
            String commentCountAnonymous = commentCountsCommentPage.get(0).getText();
            String commentCountRegistered = commentCountsCommentPage.get(1).getText();

            int sumOfCommentCount = setDigits(commentCountAnonymous) + setDigits(commentCountRegistered);
            LOGGER.info("We found a comments count on the comments page.");

            Assertions.assertEquals(articlePageCommentCount, sumOfCommentCount, "Comment count is wrong");
            Assertions.assertEquals(articlePageTitle, commentPageTitle, "Title is wrong");

        }else {
            LOGGER.info("No comments, can not go to the comments page");
        }
    }

        private Article getArticle(List<WebElement> elements, int i){

        Article currentArticle = new Article();
        WebElement article = elements.get(i);
        currentArticle.setTitle(article.findElement(TITLE).getText());

        List<WebElement> commentCounters = article.findElements(COMMENT_COUNTER);
            if (commentCounters.isEmpty()) {
                currentArticle.setCommentCount(0);
            } else {
                currentArticle.setCommentCount(commentCounters.get(0).getText());
            }
            return currentArticle;
    }

    public Integer setDigits(String commentCount) {
        commentCount = commentCount.substring(1, commentCount.length() - 1);
        return Integer.valueOf(commentCount);
        }

    @AfterEach
    public void closeBrowser () {
            driver.close();
    }

}
