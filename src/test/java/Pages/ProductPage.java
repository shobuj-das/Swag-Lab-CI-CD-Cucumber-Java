package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends BasePage{


    public By cart = By.cssSelector(".shopping_cart_link");
    public By headerText = By.className("app_logo");
    public By menuBar = By.id("react-burger-menu-btn");

    // product
    public By productName = By.className("inventory_item_name");
    public By productPrice = By.className("inventory_item_price");
    public By ProductAddToCart = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
    public By productRemoveButton = By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory");


    public By totalItemInCart = By.className("shopping_cart_badge");
    public By totalInventoryItem = By.cssSelector(".inventory_item");



    // ---- sorting locators
    public By productSort = By.className("product_sort_container");


    // ****************************** Methods related to product page *******************8
    public boolean getAllProducts() throws InterruptedException {
        List<WebElement> allProducts = getAllElements(totalInventoryItem);
        // total 6 products should be displayed
        return allProducts.size() == 6;
    }

    // -- ascending order method - A to Z
    public boolean checkAscendingOrder() throws InterruptedException{
        List<WebElement> productNameList = getAllElements(productName);
        for(int i=1; i<productNameList.size(); i++){
            String firstProductName = productNameList.get(i-1).getText();
            String secondProductName = productNameList.get(i).getText();
            if(secondProductName.compareToIgnoreCase(firstProductName) <0){
                return false;
            }
        }
        return true;
    }
    // -- descending order method - Z to A
    public boolean checkDescendingOrder() throws InterruptedException{
        return !checkAscendingOrder();
    }

    // -- ascending order method - price(low to high)
    public boolean checkPriceAscendingOrder() throws InterruptedException{
        List<WebElement> allProductPriceList = getAllElements(productPrice);

        for(int i=1, x=0; i<allProductPriceList.size(); i++,x++ ){
            double price1 = Double.parseDouble(allProductPriceList.get(x).getText().replace("$",""));
            double price2 = Double.parseDouble(allProductPriceList.get(i).getText().replace("$",""));

            if(price1 > price2)
                return false;
        }
        return true;
    }

    // -- descending order method - price(high to low)
    public boolean checkPriceDescendingOrder() throws InterruptedException{
        return !checkPriceAscendingOrder();
    }

}
