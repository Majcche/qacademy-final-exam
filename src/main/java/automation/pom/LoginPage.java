package automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;


public class LoginPage {
    WebDriver driver;
    Properties properties;

    public By usernameField = By.id("username");
    By passwordField = By.id("password");
    public By signinButton = By.cssSelector("[data-test='signin-submit']");
    public By rememberMe = By.cssSelector("[name='remember']");
    By signupLink = By.cssSelector("[data-test='signup']");

    public By alertError = By.cssSelector("[data-test='signin-error']");


    public LoginPage(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
    }

    public void enterUsername() {
        driver.findElement(usernameField).sendKeys(properties.getProperty("USERNAME"));
    }

    public void enterPassword() {
        driver.findElement(passwordField).sendKeys(properties.getProperty("PASSWORD"));
    }

    public void signInButton() {
        driver.findElement(signinButton).click();
    }

    public void logInUser() {
        this.enterUsername();
        this.enterPassword();
        this.signInButton();
    }

    public void loginInvalidData() {
        driver.findElement(usernameField).sendKeys("M");
        driver.findElement(passwordField).sendKeys("maaa");
        this.signInButton();
    }

    public void logInOneFieldEmpty() {
        this.enterPassword();
    }

    public void logInLessThanMinPassword() {
        this.enterUsername();
        driver.findElement(passwordField).sendKeys("Abc");
    }

    public void rememberMeWorkingProperly() {
        this.enterUsername();
        this.enterPassword();
        driver.findElement(rememberMe).click();
        this.signInButton();
    }

    public void registrationLink() {
        driver.findElement(signupLink).click();

    }
}
