
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {
    private final WebDriver webDriver;
    private User user;

    private By loginField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;

    }



    private void enterLogin(String login){
        WebElement loginElement = webDriver.findElement(loginField);
        loginElement.sendKeys(login);
    }

    private void enterPassword(String pass){
        WebElement loginElement = webDriver.findElement(passwordField);
        loginElement.sendKeys(pass);
    }
    public void enterCredentials(User user){
        enterLogin(user.getLogin());
        enterPassword(user.getPassword());
    }

    public void pressLogin(){
        WebElement loginButton = webDriver.findElement(By.id("login-button"));
        loginButton.click();
    }

}

