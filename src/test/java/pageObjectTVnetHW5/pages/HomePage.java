package pageObjectTVnetHW5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class HomePage {

    private BaseFunc baseFunc;

    private final By HOME_PAGE_ARTICLE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By HOME_PAGE_COMMENT_COUNT = By.xpath(".//.//span[@class = 'list-article__comment section-font-color']");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public WebElement getArticle (int i) {
        List<WebElement> titles = baseFunc.getElements(HOME_PAGE_ARTICLE);
        WebElement title = titles.get(i);
        baseFunc.logger("We find an article");
        return title;
    }

    public Integer getArticleCommentCount (WebElement element) {
        int commentCounter;
        List<WebElement> commentCounters = element.findElements(HOME_PAGE_COMMENT_COUNT);
        if (commentCounters.isEmpty()) {
          commentCounter = 0;
        } else {
          commentCounter = baseFunc.setDigits(commentCounters.get(0).getText());
        }
        baseFunc.logger("We got an article comments count on the home page");
        return commentCounter;
    }

    public String getArticleTitle (WebElement element, int commentCount) {
        String title;
        if (commentCount ==0) {
            title = element.getText();
        }else {
            String article = element.getText();
            String commentCounter = element.findElement(HOME_PAGE_COMMENT_COUNT).getText();
            title = article.substring(0, article.length()-commentCounter.length()-1);
             }
        baseFunc.logger("We got an article title on the home page");
        return title;
    }

    public ArticlePage clickArticle(int i) {
        getArticle(i).click();
        baseFunc.logger("We click an article on the home page");
        return new ArticlePage(baseFunc);
    }
}