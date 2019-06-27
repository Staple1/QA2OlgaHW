package compareGoods1aTest.pages;

import model.Product1a;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ComparePage {
    private BaseFunction baseFunc;

    private final By DIFFERENTS_ONLY_CHECKBOX = By.xpath(".//span[@class = 'check-text']");
    private final By PRODUCT_INFO = By.xpath(".//td[@class = 'main-info']");
    private final By PRODUCT_NAME = By.tagName("a"); // взять третью
    private final By PRODUCT_INFO_PRICE = By.xpath(".//td[@class = 'product-price']");
    private final By PRODUCT_PRICE = By.xpath(".//div[@class = 'price']");
    private final By COMPARE_TABLE = By.tagName("table");


    public ComparePage(BaseFunction baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void setDifferentsOnly () {
        WebElement element = baseFunc.getElement(DIFFERENTS_ONLY_CHECKBOX);
        ((JavascriptExecutor) baseFunc.driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        baseFunc.logger("We click on the 'Rādīt tikai atšķirības' checkbox");
    }

    public Product1a setItemInfoCompare (int i) {
        Product1a currentItemInfo = new Product1a();

        List<WebElement> elementsForName = baseFunc.getElements(PRODUCT_INFO);
        WebElement name = elementsForName.get(i-1).findElements(PRODUCT_NAME).get(2);
        String productName = name.getText();
        currentItemInfo.setTitle(productName);
        baseFunc.logger("We get " + i + ". product name on the Comparison page");

        List<WebElement> elementsForPrice = baseFunc.getElements(PRODUCT_INFO_PRICE);
        WebElement price = elementsForPrice.get(i-1).findElement(PRODUCT_PRICE);
        double productPrice = Integer.valueOf(price.getText().substring(1));
        currentItemInfo.setPrice(productPrice);
        baseFunc.logger("We get " + i + ". product price on the Comparison page");

        return currentItemInfo;
    }

    public List<Product1a> addProduct () {
        List<Product1a> compareProducts = new ArrayList<Product1a>();
        for (int i = 1; i < 6; i++){
            compareProducts.add(setItemInfoCompare(i));
        }
        return compareProducts;
    }

    public void getDataFile () {
        WebElement table = baseFunc.getElement(COMPARE_TABLE);

        String dataoutput = table.getText();
        System.out.println(dataoutput);
        //String csvOutputFile = "table.csv";

        File filedata = new File("src/main/table.csv");
    }



}
