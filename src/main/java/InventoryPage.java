import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private final WebDriver webDriver;


    public InventoryPage(WebDriver webDriver){
        this.webDriver = webDriver;

    }

    public void addToCart(String id){
        webDriver.findElement(By.id(id)).click();

    }

    public boolean isCartCountVisible(){
        try{
            webDriver.findElement(By.className("shopping_cart_badge"));
            return true;
        }catch(NoSuchElementException e){
            return false;
        }

    }
    public int getCartCount(){
        String count = webDriver.findElement(By.className("shopping_cart_badge")).getText();
        return Integer.parseInt(count);
    }

    public void removeProduct(String id){
        webDriver.findElement(By.id(id)).click();
    }

    public void goToCart(){
        WebElement cartLink = webDriver.findElement(By.className("shopping_cart_link"));
        cartLink.click();
    }
}
