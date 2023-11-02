package automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class MyAccountPage {
    WebDriver driver;
    WebDriverWait wait;
    Properties properties;

    By firstNameField = By.id("user-settings-firstName-input");
    By lastNameField = By.id("user-settings-lastName-input");
    By emailField = By.id("user-settings-email-input");
    By phoneNumberField = By.id("user-settings-phoneNumber-input");
    public By saveSettingsButton = By.cssSelector("[data-test='user-settings-submit']");

    public MyAccountPage(WebDriver driver, WebDriverWait wait, Properties properties) {
        this.driver = driver;
        this.wait = wait;
        this.properties = properties;
    }

    public void enterFirstName() {
        driver.findElement(firstNameField).sendKeys(properties.getProperty("FIRSTNAME"));
    }

    public void enterLastName() {
        driver.findElement(lastNameField).sendKeys(properties.getProperty("LASTNAME"));
    }

    public void enterEmail() {
        driver.findElement(emailField).sendKeys(properties.getProperty("EMAIL"));
    }

    public void enterPhoneNumber() {
        driver.findElement(phoneNumberField).sendKeys(properties.getProperty("PHONENUMBER"));
    }
    public void saveUserSettings() {
        this.enterFirstName();
        this.enterLastName();
        this.enterEmail();
        this.enterPhoneNumber();
    }
    public void charInNumberField() {
        this.enterFirstName();
        this.enterLastName();
        this.enterEmail();
        driver.findElement(phoneNumberField).sendKeys("abc");
    }
    public void oneFieldEmpty() {
        this.enterLastName();
        this.enterEmail();
        this.enterPhoneNumber();
    }
    public void invalidEmail() {
        this.enterFirstName();
        this.enterLastName();
        driver.findElement(emailField).sendKeys("");
        this.enterPhoneNumber();
    }
    public void clearFields(){
        driver.findElement(firstNameField).sendKeys(Keys.LEFT_CONTROL+"a"+Keys.DELETE);
        driver.findElement(lastNameField).sendKeys("");
        driver.findElement(emailField).sendKeys(Keys.LEFT_CONTROL+"a"+Keys.DELETE);
        driver.findElement(phoneNumberField).sendKeys("");
    }
}

