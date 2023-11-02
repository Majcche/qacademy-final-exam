package automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Properties;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    Properties properties;

   public By logOutButton = By.cssSelector("[data-test = 'sidenav-signout']");
    public By bankAccounts = By.cssSelector("[data-test='sidenav-bankaccounts']");
    public By myAccount = By.cssSelector("[data-test = 'sidenav-user-settings']");
    public By newTransactionButton = By.cssSelector("[data-test='nav-top-new-transaction']");

    public HomePage(WebDriver driver, WebDriverWait wait, Properties properties) {
        this.driver = driver;
        this.wait = wait;
        this.properties = properties;
    }
    public void logoutUser() {
        driver.findElement(logOutButton).click();
    }
}
