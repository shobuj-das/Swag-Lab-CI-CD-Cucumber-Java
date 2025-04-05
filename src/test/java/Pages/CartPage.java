package Pages;

import org.openqa.selenium.By;

public class CartPage extends BasePage{
    // -- cart
    public By cartLink = By.className("shopping_cart_link");
    public By cartBadge= By.className("shopping_cart_badge");

    // ------- products
    public By cartItemsRootLocator = By.className("cart_item_label");
    public By productName = By.className("inventory_item_name");
    public By productDescription = By.className("inventory_item_desc");
    public By productPrice = By.className("inventory-item-price");
    public By removeButton_css = By.className(".btn.btn_secondary.btn_small.cart_button");

    public By continueShoppingButton = By.id("continue-shopping");
    public By checkOutButton = By.cssSelector("#checkout");


}
