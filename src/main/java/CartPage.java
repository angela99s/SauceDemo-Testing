import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private final WebDriver webDriver;


    public CartPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public List<WebElement> getListOfItems(){
        List<WebElement> items = webDriver.findElements(By.className("cart_item"));
        return items;
    }

    public int getNumberOfItems(){
        return this.getListOfItems().size();
    }

    public void clickRemove(String id){
        webDriver.findElement(By.id(id)).click();
    }

    public void clickCheckout(){
        webDriver.findElement(By.id("checkout")).click();
    }


}
