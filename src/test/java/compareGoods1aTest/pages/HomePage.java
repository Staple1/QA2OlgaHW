package compareGoods1aTest.pages;

import org.openqa.selenium.By;

public class HomePage {

    private BaseFunction baseFunc;

    private final String PRUDUCT_TYPE = "Datortehnika, preces birojam";

    private final By LEFT_COLUMN_MENU = By.xpath(".//ul[@class = 'sidenav v2']");
    private final By MENU_C0NTENT = By.tagName("a");

    public HomePage (BaseFunction baseFunc) {
        this.baseFunc = baseFunc;
    }

    public ItemTypeLvl2Page getPage () {
        baseFunc.testStap(LEFT_COLUMN_MENU,MENU_C0NTENT, PRUDUCT_TYPE);
        baseFunc.logger("We click an 'Datortehnika, preces birojam' on the homepage");
        return new ItemTypeLvl2Page(baseFunc);
    }
}
