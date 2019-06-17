package pageObjectTVnetHW5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pageObjectTVnetHW5.pages.ArticlePage;
import pageObjectTVnetHW5.pages.BaseFunc;
import pageObjectTVnetHW5.pages.HomePage;


public class TvnetTest {

    private final String URL = "tvnet.lv";
    private final int ARTICLE_NUMBER = 1;

    private BaseFunc baseFunc = new BaseFunc();

    @Test
    public void articleTest () {
        baseFunc.goToUrl(URL);
        HomePage tvnetHomePage = new HomePage(baseFunc);
        ArticlePage tvnetArticlePage = new ArticlePage(baseFunc);

        WebElement homePageArticle = tvnetHomePage.getArticle(ARTICLE_NUMBER-1);
        int homePageArticleCommentCount = tvnetHomePage.getArticleCommentCount(homePageArticle);
        String homePageArticleTitle = tvnetHomePage.getArticleTitle(homePageArticle,homePageArticleCommentCount);

        tvnetHomePage.clickArticle(ARTICLE_NUMBER-1);

        String articlePageArticleTitle = tvnetArticlePage.getArticleTitle();
        int articlePageCommentCount = tvnetArticlePage.getArticleCommentCount();

        Assertions.assertEquals(homePageArticleTitle, articlePageArticleTitle, "Article title is wrong");
        Assertions.assertEquals(homePageArticleCommentCount, articlePageCommentCount, "Comments count is wrong");

        //baseFunc.closeBrowser();
    }


}
