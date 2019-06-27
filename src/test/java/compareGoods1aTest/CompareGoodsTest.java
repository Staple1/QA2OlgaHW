package compareGoods1aTest;

import compareGoods1aTest.pages.*;
import model.Product1a;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

public class CompareGoodsTest {

    private final String URL = "1a.lv";
    private final int FIRST_PRODUCT_NUMBER = 1;
    private final int SECOND_PRODUCT_NUMBER = 2;
    private final int THIRD_PRODUCT_NUMBER = 3;
    private final int FOURTH_PRODUCT_NUMBER = 4;
    private final int FIFTH_PRODUCT_NUMBER = 5;

    private BaseFunction baseFunc = new BaseFunction();

    @Test
    public void goodsTest()  {
        baseFunc.goToUrl(URL);
        HomePage homePage1a = new HomePage(baseFunc);
        ItemTypeLvl2Page itemTypeLvl2 = new ItemTypeLvl2Page(baseFunc);
        ItemTypeLvl3Page itemTypeLvl3 = new ItemTypeLvl3Page(baseFunc);
        ItemTypeLvl4Page itemTypeLvl4 = new ItemTypeLvl4Page(baseFunc);
        ComparePage comparePage = new ComparePage(baseFunc);

        homePage1a.getPage();
        itemTypeLvl2.getPage();
        itemTypeLvl3.getPage();

        Product1a firstItemInfo = itemTypeLvl4.setItemInfo(FIRST_PRODUCT_NUMBER);
        Product1a secondItemInfo = itemTypeLvl4.setItemInfo(SECOND_PRODUCT_NUMBER);
        Product1a thirdItemInfo = itemTypeLvl4.setItemInfo(THIRD_PRODUCT_NUMBER);
        Product1a fourthItemInfo = itemTypeLvl4.setItemInfo(FOURTH_PRODUCT_NUMBER);
        Product1a fifthItemInfo = itemTypeLvl4.setItemInfo(FIFTH_PRODUCT_NUMBER);

        String firstProductName = firstItemInfo.getTitle();
        String secondProductName = secondItemInfo.getTitle();
        String thirdProductName = thirdItemInfo.getTitle();
        String fourthProductName = fourthItemInfo.getTitle();
        String fifthProductName = fifthItemInfo.getTitle();

        double firstProductPrice = firstItemInfo.getPrice();
        double secondProductPrice = secondItemInfo.getPrice();
        double thirdProductPrice = thirdItemInfo.getPrice();
        double fourthProductPrice = fourthItemInfo.getPrice();
        double fifthProductPrice = fifthItemInfo.getPrice();

        itemTypeLvl4.getComparePage();

        List<Product1a> productsForCompare = comparePage.addProduct();


        for (int i = 0; i < productsForCompare.size(); i++) {
            String currentCompareProductName = productsForCompare.get(i).getTitle();
            if (currentCompareProductName.equals(firstProductName)) {
                Assertions.assertEquals(firstProductName, currentCompareProductName);
                Assertions.assertEquals(firstProductPrice, productsForCompare.get(i).getPrice());
                for (i = 0; i < productsForCompare.size(); i++) {
                    currentCompareProductName = productsForCompare.get(i).getTitle();
                    if (currentCompareProductName.equals(secondProductName)) {
                        Assertions.assertEquals(secondProductName, currentCompareProductName);
                        Assertions.assertEquals(secondProductPrice, productsForCompare.get(i).getPrice());
                        for (i = 0; i < productsForCompare.size(); i++) {
                            currentCompareProductName = productsForCompare.get(i).getTitle();
                            if (currentCompareProductName.equals(thirdProductName)) {
                                Assertions.assertEquals(thirdProductName, currentCompareProductName);
                                Assertions.assertEquals(thirdProductPrice, productsForCompare.get(i).getPrice());
                                for (i = 0; i < productsForCompare.size(); i++) {
                                    currentCompareProductName = productsForCompare.get(i).getTitle();
                                    if (currentCompareProductName.equals(fourthProductName)) {
                                        Assertions.assertEquals(fourthProductName, currentCompareProductName);
                                        Assertions.assertEquals(fourthProductPrice, productsForCompare.get(i).getPrice());
                                        for ( i = 0; i < productsForCompare.size(); i++) {
                                            currentCompareProductName = productsForCompare.get(i).getTitle();
                                            if (currentCompareProductName.equals(fifthProductName)) {
                                                Assertions.assertEquals(fifthProductName, currentCompareProductName);
                                                Assertions.assertEquals(fifthProductPrice, productsForCompare.get(i).getPrice());
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        comparePage.setDifferentsOnly();
        comparePage.getDataFile();
        baseFunc.closeBrowser();

    }

}