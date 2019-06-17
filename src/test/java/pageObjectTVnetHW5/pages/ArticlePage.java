package pageObjectTVnetHW5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ArticlePage {

    private BaseFunc baseFunc;

    private final By ARTICLE_PAGE_ARTICLE_TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'item--comments')]");
    private final By ARTICLE_PAGE_COMMENTS_COUNT = By.xpath(".//span[contains(@class, 'item--count')]");

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getArticleTitle () {
        String articleTitle = baseFunc.getElement(ARTICLE_PAGE_ARTICLE_TITLE).getText();
        baseFunc.logger("We got an article title on the article page");
        return articleTitle;
    }

    public Integer getArticleCommentCount () {
        int commentCounter;
        List<WebElement> comments = baseFunc.getElement(ARTICLE_PAGE_COMMENTS).findElements(ARTICLE_PAGE_COMMENTS_COUNT);
        String commentCount = comments.get(0).getText();

        if (commentCount == null){
            commentCounter = 0;
        }else {
            commentCounter = Integer.valueOf(commentCount);
        }
        baseFunc.logger("We got an article comments count on the article page");
        return commentCounter;
    }
}
