import model.KlerrItem;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class KlerrTestObjHW4 {

    private final String URL = "http://www.klerr.lv/";
    private static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(KlerrTestObjHW4.class);
    private WebDriver driver;


    private final String PRUDUCT_TYPE = "KLEITAS";
    private final String PRODUCT_CATEGORY = "VAKARKLEITAS";
    private final int FIRST_ITEM_NUMBER = 3;
    private final int SECOND_ITEM_NUMBER = 4;
    private final By LEFT_COLUMN_MENU = By.id("mainm");
    private final By MENU_LIST = By.xpath(".//div[@class = 'menu2div']");
    private final By TYPE_MENU_LIST = By.xpath(".//a[@class = 'menu32']");
    private final By CENTER_COLUMN = By.id("infopob");
    private final By PRODUCT_LIST = By.xpath(".//a[@class = 'prname']");
    private final By ITEM_TITLE = By.tagName("h1");
    private final By ITEM_PRICE = By.xpath(".//td[@class = 'tabinfo']");
    private final By ITEM_TITLE_CART = By.xpath(".//td[@style = 'FONT-WEIGHT: 400;']");
    private final By ITEM_SIZE_CART = By.xpath(".//td[@style = 'FONT-SIZE: 12px; COLOR: #000000;']");
    private final By ITEM_PRICE_CART = By.xpath(".//td[@style = 'text-align: right; width: 150px;']");
    private final By ITEM_QUANTITY_CART = By.xpath(".//td[@style = 'text-align: center;']");
    private final By TOTAL_PRICE_CART = By.id("kopaprice");

    private final By SELECT_SIZE = By.id("izm");
    private final By PUT_IN_CART_BTN = By.xpath(".//div[@class = 'pirktbtn']");
    private final By POPUP_CLOSE = By.id("fancybox-close");
    private final By CART = By.id("korzdaudzums");

    private final By POPUP_EVENT_CLOSE = By.xpath(".//img[@alt = 'X']");

    @Test
    public void klerrTest() {
        LOGGER.info("We are starting our test");
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        LOGGER.info("We are opening klerr.lv home page.");
        goToUrl(URL);

        closeEventPopup();
        wayToItem(FIRST_ITEM_NUMBER);

        KlerrItem firstItemInfo = getItemInfo();

        driver.findElement(PUT_IN_CART_BTN).click();
        driver.findElement(POPUP_CLOSE).click();
        driver.findElement(CART).click();

        KlerrItem firstItemInfoCart = getItemInfoCart(0);

        Assertions.assertEquals(firstItemInfo.getSize(), firstItemInfoCart.getSize(),"First item size are wrong");
        Assertions.assertEquals(firstItemInfo.getQuantity(), firstItemInfoCart.getQuantity(),"First item quantity are wrong");
        Assertions.assertEquals(firstItemInfo.getPrice(), firstItemInfoCart.getPrice(),"First item price are wrong");
        Assertions.assertEquals(firstItemInfo.getTitle(), firstItemInfoCart.getTitle(),"First item title are wrong");

        wayToItem(SECOND_ITEM_NUMBER);
        KlerrItem secondItemInfo = getItemInfo();

        driver.findElement(PUT_IN_CART_BTN).click();
        driver.findElement(POPUP_CLOSE).click();
        driver.findElement(CART).click();

        KlerrItem secondItemInfoCart = getItemInfoCart(1);

        double totalPrice = firstItemInfo.getPrice()+secondItemInfo.getPrice();
        String totalPriceCart = driver.findElement(TOTAL_PRICE_CART).getAttribute("value");
        double totalPriceCartDigits = Double.valueOf(totalPriceCart.split(" ")[0]);

        Assertions.assertEquals(secondItemInfo.getSize(),secondItemInfoCart.getSize(),"Second item size are wrong");
        Assertions.assertEquals(secondItemInfo.getQuantity(),secondItemInfoCart.getQuantity(),"Second item quantity are wrong");
        Assertions.assertEquals(secondItemInfo.getPrice(),secondItemInfoCart.getPrice(),"Second item price are wrong");
        Assertions.assertEquals(secondItemInfo.getTitle(),secondItemInfoCart.getTitle(),"Second item title are wrong");

        Assertions.assertEquals(totalPrice,totalPriceCartDigits);

    }

    private void wayToItem(int itemNumber) {
        testStap(LEFT_COLUMN_MENU, MENU_LIST, PRUDUCT_TYPE);
        LOGGER.info("We have moved to the product type page");
        testStap(LEFT_COLUMN_MENU, TYPE_MENU_LIST, PRODUCT_CATEGORY);
        LOGGER.info("We have moved to the product category page");

        List<WebElement> items = driver.findElement(CENTER_COLUMN).findElements(PRODUCT_LIST);
        WebElement currentItem = items.get(itemNumber - 1);

        currentItem.click();
        LOGGER.info("We have moved to the product page");
    }

    private void testStap(By locator1, By locator2, String LINK_NAME) {
        List<WebElement> findElements = driver.findElement(locator1).findElements(locator2);

        for (int i = 0; i < findElements.size(); i++) {
            WebElement element = findElements.get(i);
            String currentTitle = element.getText();
            if (currentTitle.equals(LINK_NAME)) {
                element.click();
                break;
            }
        }
    }

    private KlerrItem getItemInfoCart(int i) {
        KlerrItem currentItemInfo = new KlerrItem();

        String itemTitleCart = driver.findElements(ITEM_TITLE_CART).get(i).getText();
        currentItemInfo.setTitle(itemTitleCart);
        LOGGER.info("We got the product title on the cart page.");

        List<WebElement> elements = driver.findElements(ITEM_SIZE_CART);
        if ((elements.size()) > 2) {
            String itemSizeCart = elements.get(i + 1).getText();
            currentItemInfo.setSize(itemSizeCart);
        } else {
            String itemSizeCart = elements.get(i).getText();
            currentItemInfo.setSize(itemSizeCart);
        }
        LOGGER.info("We got the product size on the cart page.");

        String itemPriceCart = driver.findElements(ITEM_PRICE_CART).get(i).getText();
        currentItemInfo.setPrice(itemPriceCart);
        LOGGER.info("We got the product price on the cart page.");

        String itemQuantityCart = driver.findElements(ITEM_QUANTITY_CART).get(i).getText();
        int itemQuantityCartDigits = Integer.valueOf(itemQuantityCart);
        currentItemInfo.setQuantity(itemQuantityCartDigits);
        LOGGER.info("We got the product quantity on the cart page.");

        return currentItemInfo;
    }

    private KlerrItem getItemInfo() {
        KlerrItem currentItemInfo = new KlerrItem();

        String itemTitle = driver.findElement(ITEM_TITLE).getText();
        currentItemInfo.setTitle(itemTitle);
        LOGGER.info("We got the product title on the product page.");

        WebElement selectElem = driver.findElement(SELECT_SIZE);
        Select select = new Select(selectElem);
        select.selectByIndex(1);
        String itemSize = select.getFirstSelectedOption().getText().split(" ")[0];
        currentItemInfo.setSize(itemSize);
        LOGGER.info("We got the product size on the product page.");

        String itemPrice = driver.findElement(ITEM_PRICE).getText();
        currentItemInfo.setPrice(itemPrice);
        LOGGER.info("We got the product price on the product page.");

        currentItemInfo.setQuantity(1);
        LOGGER.info("We got the product quantity on the product page.");

        return currentItemInfo;
    }
    private void closeEventPopup () {
        List<WebElement> eventWindows = driver.findElements(POPUP_EVENT_CLOSE);
        if (eventWindows.size()>0) {
            eventWindows.get(0).click();
        }
        LOGGER.info("We closed event window");
    }


    public void goToUrl (String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
    }

    @AfterEach
    public void closeBrowser () {
        Assertions.assertNotNull(driver, "There is no opened window!" );
        driver.close();
    }
}


