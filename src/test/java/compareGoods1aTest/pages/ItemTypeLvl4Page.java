package compareGoods1aTest.pages;

import model.Product1a;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ItemTypeLvl4Page {
    private BaseFunction baseFunc;

    private final By PRODUCT_COMPARE_CHECKBOX = By.xpath(".//span[@class='product-attribute-value']");
    private final By PRODUCT_COMPARE_BUTTON = By.id("compare_products_button");
    private final By PRODUCT = By.tagName("section");
    private final By PRODUCT_CONTENT = By.xpath(".//div[@class = 'p-content']");
    private final By PRODUCT_NAME = By.tagName("a");
    private final By PRODUCT_PRICE = By.xpath(".//div[@class = 'price']");

    public ItemTypeLvl4Page(BaseFunction baseFunc) {
        this.baseFunc = baseFunc;
    }

    private List<WebElement> getProducts() {
        List<WebElement> elements = baseFunc.getElements(PRODUCT);
        return elements;
    }

    private String getProductName(int i, List<WebElement> elements) {
        WebElement product = elements.get(i-1);
        WebElement name = product.findElement(PRODUCT_CONTENT).findElement(PRODUCT_NAME);
        String productName = name.getText();
        baseFunc.logger("We get " + i + ". product name");
        return productName;
    }

    private double getProductPrice(int i, List<WebElement> elements) {
        WebElement product = elements.get(i-1);
        WebElement price = product.findElement(PRODUCT_PRICE);
        double productPrice = Integer.valueOf(price.getText().substring(1));
        baseFunc.logger("We get " + i + ". product price");
        return productPrice;
    }

    public Product1a setItemInfo (int i){
        Product1a currentItemInfo = new Product1a();

        List<WebElement> elements = getProducts();
        String productName = getProductName(i, elements);
        currentItemInfo.setTitle(productName);
        double productPrice = getProductPrice(i, elements);
        currentItemInfo.setPrice(productPrice);
        setCompareCheckbox(i, elements);
        return currentItemInfo;
    }

    public void setCompareCheckbox(int i, List<WebElement> elements) {
        WebElement product = elements.get(i-1);
        WebElement checkbox = product.findElement(PRODUCT_COMPARE_CHECKBOX);
        ((JavascriptExecutor) baseFunc.driver).executeScript("arguments[0].scrollIntoView();", checkbox);
        checkbox.click();
        int productNumber = i;
        baseFunc.logger("We click on the " + productNumber + ". product 'Pievienot salīdzināšanai' checkbox");
    }

    public ComparePage getComparePage() {
        WebElement element = baseFunc.getElement(PRODUCT_COMPARE_BUTTON);
        element.click();
        baseFunc.logger("We click an 'Salīdzināt preces' on the 'Portatīvie datori' page");
        return new ComparePage(baseFunc);
    }


}
