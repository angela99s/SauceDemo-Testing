
import org.openqa.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class TestInventory {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\Informatyka\\Testing\\chromedriver-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage.enterCredentials(new User("standard_user", "secret_sauce"));
        Thread.sleep(1000);
        loginPage.pressLogin();
    }

    @Test
    public void testAddToCartOneProduct() throws InterruptedException {
        inventoryPage.addToCart("add-to-cart-sauce-labs-bike-light");
        Thread.sleep(3000);
        assertTrue(inventoryPage.isCartCountVisible());
        assertEquals(inventoryPage.getCartCount(), 1);
    }

    @Test
    public void testAddAndRemoveProduct(){
        inventoryPage.addToCart("add-to-cart-sauce-labs-bike-light");
        assertTrue(driver.findElement(By.id("remove-sauce-labs-bike-light")).isDisplayed());
        inventoryPage.removeProduct("remove-sauce-labs-bike-light");
        assertFalse(inventoryPage.isCartCountVisible());

    }

    @Test
    public void testCartLink() throws InterruptedException {
        inventoryPage.goToCart();
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        String actualUrl = driver.getCurrentUrl();
        Thread.sleep(1000);
        assertTrue(actualUrl.equals(expectedUrl));
    }
    @After
    public void close(){
        if(driver != null) {
            driver.quit();
        }
    }
}
