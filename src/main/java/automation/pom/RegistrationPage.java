package automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;
    Properties properties;

   // public By firstNameField = By.cssSelector("[name='firstName']");
    public By firstNameField = By.xpath("//*[@id='firstName']");
    public By lastNameField = By.cssSelector("[name='lastName']");
    public By usernameInputField = By.id("username");
    public By passwordInputField = By.id("password");
    public By confirmPassword = By.id("confirmPassword");
    public By signupButton = By.cssSelector("[data-test='signup-submit']");
    By signInLink = By.linkText("Have an account? Sign In");

    public RegistrationPage(WebDriver driver, WebDriverWait wait, Properties properties) {
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

    public void enterUsername() {
        driver.findElement(usernameInputField).sendKeys(properties.getProperty("USERNAME2"));
    }
    public void enterPassword() {
        driver.findElement(passwordInputField).sendKeys(properties.getProperty("PASSWORD2"));
    }
    public void enterConfirmPassword() {
        driver.findElement(confirmPassword).sendKeys(properties.getProperty("PASSWORD2"));
    }

    public void signUpUser() {
        this.enterFirstName();
        this.enterLastName();
        this.enterUsername();
        this.enterPassword();
        this.enterConfirmPassword();
        driver.findElement(signupButton).click();
    }
    public void nonMatchingPassword() {
        this.enterFirstName();
        this.enterLastName();
        this.enterUsername();
        this.enterPassword();
        driver.findElement(confirmPassword).sendKeys("1234");
    }
    public void oneFieldEmpty() {
        this.enterLastName();
        this.enterUsername();
        this.enterPassword();
        this.enterConfirmPassword();
    }
    public void lessThanMinLengthPassword() {
        this.enterFirstName();
        this.enterLastName();
        this.enterUsername();
        driver.findElement(passwordInputField).sendKeys("Abc");
        this.enterConfirmPassword();
    }
    public void verifyLink() {
        driver.findElement(signInLink).click();
    }
}
