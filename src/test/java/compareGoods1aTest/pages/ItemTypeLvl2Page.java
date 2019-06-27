package compareGoods1aTest.pages;

import org.openqa.selenium.By;

public class ItemTypeLvl2Page {
    private BaseFunction baseFunc;

    private final String PRUDUCT_TYPE_LVL2 = "Portatīvie datori un aksesuāri";

    private final By LEFT_COLUMN_MENU = By.xpath(".//div[@class = 'ait-category-dropdown-drop-wrapper static']");
    private final By MENU_C0NTENT = By.tagName("a");

    public ItemTypeLvl2Page(BaseFunction baseFunc) {
        this.baseFunc = baseFunc;
    }

    public ItemTypeLvl3Page getPage () {
        baseFunc.testStap(LEFT_COLUMN_MENU,MENU_C0NTENT, PRUDUCT_TYPE_LVL2);
        baseFunc.logger("We click an '" + PRUDUCT_TYPE_LVL2 + "' on the 'Datortehnika, preces birojam' page");
        return new ItemTypeLvl3Page(baseFunc);
    }
}
