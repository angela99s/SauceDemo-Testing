import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestCartPage {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "E:\\Informatyka\\Testing\\chromedriver-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage.enterCredentials(new User("standard_user", "secret_sauce"));
        loginPage.pressLogin();
        inventoryPage.addToCart("add-to-cart-sauce-labs-bike-light");
        inventoryPage.goToCart();
    }

    @Test
    public void testCartOneProduct(){
        List<WebElement> items = cartPage.getListOfItems();
        for(WebElement element : items){
            String itemText = element.getText();

            if(itemText.contains("Sauce Labs Bike Light") && itemText.contains("$9.99")){
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @Test
    public void testRemoveButton(){
        cartPage.clickRemove("remove-sauce-labs-bike-light");
        assertTrue(cartPage.getNumberOfItems() == 0);
    }

    @Test
    public void testCheckoutButton(){
        cartPage.clickCheckout();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        String actualUrl = driver.getCurrentUrl();
        assertTrue(expectedUrl.equals(actualUrl));
    }

    @After
    public void close(){
        if(driver != null) {
            driver.quit();
        }
    }
}
