package StepDefs;

import Pages.LoginPage;
import Pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static StepDefs.Hooks.softAssert;
import static Utilities.DriverSetup.getDriver;

public class ProductPageDefs {
    ProductPage productPage = new ProductPage();
    LoginPage loginPage = new LoginPage();

    @And("User login with valid credentials")
    public void userLoginWithValidCredentials() throws InterruptedException{
        loginPage.writeOnElement(loginPage.username,"standard_user");
        loginPage.writeOnElement(loginPage.password,"secret_sauce");
        loginPage.clickOnElement(loginPage.loginButton);
    }

    @Then("Product page header should be {string}")
    public void productPageHeaderShouldBe(String arg0) throws InterruptedException{
        softAssert.assertEquals(productPage.getElementText(productPage.headerText),arg0,"Product page header mismatched");
    }

    @And("Page title should be {string}")
    public void pageTitleShouldBe(String arg0) {
        softAssert.assertEquals(getDriver().getTitle(),arg0,"Product page title mismatched");
    }

    @And("Current url should be {string}")
    public void currentUrlShouldBe(String arg0) {
        softAssert.assertEquals(getDriver().getCurrentUrl(),arg0,"Product page url mismatched");
    }

    @And("Menu bar should be displayed")
    public void menubarShouldBeDisplayed() throws InterruptedException{
        softAssert.assertTrue(productPage.getDisplayStatus(productPage.menuBar),"Menu bar didn't display");
    }

    @And("Menu bar should be clickable")
    public void menubarShouldBeClickable() throws InterruptedException{
        softAssert.assertTrue(productPage.getEnableStatus(productPage.menuBar), "Menu bar not enable or not clickable");
    }

    @And("Cart should be displayed")
    public void cartShouldBeDisplayed() throws InterruptedException{
        softAssert.assertTrue(productPage.getDisplayStatus(productPage.cart),"cart not displayed");
    }

    @And("Cart should be clickable")
    public void cartShouldBeClickable() throws InterruptedException {
        softAssert.assertTrue(productPage.getEnableStatus(productPage.cart),"cat not clickable");
    }

    @And("All products should be displayed")
    public void allProductsShouldBeDisplayed() throws InterruptedException {
        softAssert.assertTrue(productPage.getAllProducts(),"All products not displayed in the product page");
    }

    @When("User add first product to the cart")
    public void userAddFirstProductToTheCart() throws InterruptedException{
        List<WebElement> porductList = productPage.getAllElements(productPage.ProductAddToCart);
//        System.out.println("total product item:"+porductList.size());
        porductList.get(1).click();
    }

    @Then("The digit {string} should be visible on the cart logo")
    public void theDigitShouldBeVisibleOnTheCartLogo(String arg0) throws InterruptedException{
        softAssert.assertEquals(productPage.getElementText(productPage.totalItemInCart),arg0);
        productPage.addScreenShoot("product added to cart");
    }

    @And("User add second product to the cart")
    public void userAddSecondProductToTheCart() throws InterruptedException {
        List<WebElement> porductList = productPage.getAllElements(productPage.ProductAddToCart);
//        System.out.println("total product item:"+porductList.size());
        porductList.get(1).click();
    }

    @And("User remove first product from the cart")
    public void userRemoveFirstProductFromTheCart() throws InterruptedException{
        List<WebElement> totalProductInCart = productPage.getAllElements(productPage.productRemoveButton);
//        System.out.println("total product item:"+totalProductInCart.size());
        totalProductInCart.get(1).click();
    }

    @When("User select {string} from the sort products")
    public void userSelectFromTheSortProducts(String sortingType) throws InterruptedException{
        WebElement dropDown = productPage.getElement(productPage.productSort);
        Select select = new Select(dropDown);
        select.selectByVisibleText(sortingType);
    }

    @Then("User should see all products in ascending order according to product name")
    public void userShouldSeeAllProductsInAscendingOrderAccordingToProductName() throws InterruptedException{
        softAssert.assertTrue(productPage.checkAscendingOrder(),"Products are not in A to Z order");
    }

    @Then("User should see all products in descending order according to product name")
    public void userShouldSeeAllProductsInDescendingOrderAccordingToProductName() throws InterruptedException {
        softAssert.assertTrue(productPage.checkDescendingOrder(),"Products are not in Z to A order");
        productPage.addScreenShoot("Product sorting list");
    }

    @Then("User should see all products in low to high price order")
    public void userShouldSeeAllProductsInLowToHighPriceOrder() throws InterruptedException{
        softAssert.assertTrue(productPage.checkPriceAscendingOrder());
        productPage.addScreenShoot("product in low to high price order");
    }

    @Then("User should see all products in high to low price order")
    public void userShouldSeeAllProductsInHighToLowPriceOrder() throws InterruptedException {
        softAssert.assertTrue(productPage.checkPriceDescendingOrder());
        productPage.addScreenShoot("product in high to low price order");
    }

    @Then("Product sort option should be selected {string}")
    public void productSortOptionShouldBeSelected(String arg0) throws InterruptedException{
        WebElement dropDown = productPage.getElement(productPage.productSort);
        Select select = new Select(dropDown);
        WebElement selectedOption = select.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        softAssert.assertEquals(selectedText,arg0,"By default not A to Z");
    }
}
