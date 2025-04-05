package Pages;

import Utilities.DriverSetup;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

public class BasePage extends DriverSetup {
    public WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

    public WebElement getElement(By locator) throws InterruptedException{
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
//        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return getDriver().findElement(locator);
    }

    public List<WebElement> getAllElements(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return getDriver().findElements(locator);
    }
    public void clickOnElement(By locator) throws InterruptedException{
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
//        getElement(locator).click();
    }

    public void clearInputText(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
    }
    public void writeOnElement(By locator, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        getElement(locator).clear();
        getElement(locator).sendKeys(text);
    }

    public String getElementText(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getElement(locator).getText();
    }

    public String getAttributeValue(By locator , String attributeName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getElement(locator).getDomAttribute(attributeName);
    }

    public String getPropertyValue(By locator, String propertyName) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return getElement(locator).getDomProperty(propertyName);
    }

    public String getElementCssValue(By locator, String propertyName) throws InterruptedException{
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getCssValue(propertyName);
    }

    public boolean getDisplayStatus(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getElement(locator).isDisplayed();
    }

    public boolean getSelectedStatus(By locator) throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getElement(locator).isSelected();
    }

    public boolean getEnableStatus(By locator) throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getElement(locator).isEnabled();
    }

    public void loadAWebPage(String url){
        getDriver().get(url);
    }

    public void addScreenShoot(String name){
        Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    public void hoverOnElement(By locator) throws InterruptedException{
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getElement(locator)).build().perform();
    }

    // navigate
    public void navigateBack(){
        getDriver().navigate().back();
    }
    public void navigateForward(){
        getDriver().navigate().forward();
    }
    public void refresh(){
        getDriver().navigate().refresh();
    }

    public void scrollIntoViewPoint(By locator) throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)",getElement(locator));

//        Thread.sleep(3000);
    }

    public Dimension getDimension(By locator) throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getElement(locator).getSize();
    }
}