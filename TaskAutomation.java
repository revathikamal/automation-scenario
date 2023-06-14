package cctel;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
public class TaskAutomation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");

		//Login to the site with the credentials provided
   		WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();
        //Thread.sleep(2000);
        //Sort the products in price low to high order
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();
        WebElement priceLowToHighOption = driver.findElement(By.xpath("//option[@value='lohi']"));
        priceLowToHighOption.click();
        //Add all items to the cart
        List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
        for (WebElement addToCartButton : addToCartButtons)
        {
            addToCartButton.click();
        }
        // Go to the Cart page and remove items that have a price <$15
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();
        List<WebElement> cartItemPrices = driver.findElements(By.className("inventory_item_price"));
        for (WebElement cartItemPrice : cartItemPrices) {
            String priceText = cartItemPrice.getText().substring(1);
            double price = Double.parseDouble(priceText);
            if (price < 15) {
               WebElement removeButton = cartItemPrice.findElement(By.xpath("../..//button[text()='Remove']"));
            	//WebElement removeButton=cartItemPrice.findElement(By.id("remove-sauce-labs-fleece-jacket"));
                removeButton.click();
            }
        }
        Thread.sleep(500);
        WebElement checkoutButton= driver.findElement(By.id("checkout"));
        checkoutButton.click();
        driver.findElement(By.id("first-name")).sendKeys("Revathi");
        driver.findElement(By.id("last-name")).sendKeys("Kamal");
        driver.findElement(By.id("postal-code")).sendKeys("670502");
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();
        //Return to the home page
        WebElement backButton = driver.findElement(By.id("back-to-products"));
        backButton.click();

        //Perform logout
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
        Thread.sleep(500);
        logoutLink.click();
        



	}

}
