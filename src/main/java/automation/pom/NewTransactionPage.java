package automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class NewTransactionPage {
    WebDriver driver;
    WebDriverWait wait;
    Properties properties;

    By searchInputField = By.id("user-list-search-input");

    public NewTransactionPage(WebDriver driver, WebDriverWait wait, Properties properties) {
        this.driver = driver;
        this.wait = wait;
        this.properties = properties;
    }
    public void searchUsingName() {
        driver.findElement(searchInputField).click();
        driver.findElement(searchInputField).sendKeys(properties.getProperty("FIRSTNAME"));
    }
    public void searchUsingEmail() {
        driver.findElement(searchInputField).sendKeys(properties.getProperty("EMAIL"));
    }
    public void searchAccountNumber() {
        driver.findElement(searchInputField).sendKeys(properties.getProperty("ACCOUNTNUMBER"));
    }
    public void searchPersonUsingUsername() {
        driver.findElement(searchInputField).sendKeys(properties.getProperty("USERNAME"));
    }

}




