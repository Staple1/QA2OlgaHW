package compareGoods1aTest.pages;

import org.openqa.selenium.By;

public class ItemTypeLvl3Page {
    private BaseFunction baseFunc;

    private final String PRUDUCT_TYPE_LVL3 = "Portatīvie datori";

    private final By LEFT_COLUMN_MENU = By.xpath(".//ul[@class = 'level-3']");
    private final By MENU_C0NTENT = By.tagName("a");


    public ItemTypeLvl3Page(BaseFunction baseFunc) {
        this.baseFunc = baseFunc;
    }


    public ItemTypeLvl4Page getPage () {
        baseFunc.testStap(LEFT_COLUMN_MENU,MENU_C0NTENT, PRUDUCT_TYPE_LVL3);
        baseFunc.logger("We click an '" + PRUDUCT_TYPE_LVL3 + "' on the 'Portatīvie datori un aksesuāri' page");
        return new ItemTypeLvl4Page(baseFunc);
    }


}



