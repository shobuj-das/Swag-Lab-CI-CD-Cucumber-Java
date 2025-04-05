package Pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{

    public String login_url = "https://www.saucedemo.com/";
    public By username = By.cssSelector("#user-name");
    public By password = By.cssSelector("#password");
    public By loginButton = By.cssSelector("#login-button");
    public By errorMessage = By.xpath("//h3[@data-test='error']");
    public By errorRemoveButton = By.className("error-button");



    public boolean checkUsernamePasswordField() throws InterruptedException{
        String userNameText = getAttributeValue(username,"value");
        String passwordText = getAttributeValue(password,"value");

        return !userNameText.isEmpty() && !passwordText.isEmpty();
    }
}
