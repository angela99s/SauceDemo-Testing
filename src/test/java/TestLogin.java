
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.Assert.assertTrue;

public class TestLogin {
    private WebDriver driver;
    private LoginPage loginPage;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "E:\\Informatyka\\Testing\\chromedriver-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testLoginStandardUser() throws InterruptedException {
        User user = new User("standard_user", "secret_sauce");
        loginPage.enterCredentials(user);
        loginPage.pressLogin();
        Thread.sleep(1000);
        String expectedRedirectUrl = "https://www.saucedemo.com/inventory.html";
        String actualRedirectUrl = driver.getCurrentUrl();
        assertTrue(actualRedirectUrl.equals(expectedRedirectUrl));
    }

    @Test
    public void testLoginLockedUser() throws InterruptedException {
        User user = new User("locked_user", "secret_sauce");
        loginPage.enterCredentials(user);
        loginPage.pressLogin();
        Thread.sleep(1000);
        WebElement errorElement = driver.findElement(By.className("error-message-container"));
        assertTrue(errorElement.isDisplayed());
    }

    @After
    public void close(){
        if(driver != null) {
            driver.quit();
        }
    }

}
