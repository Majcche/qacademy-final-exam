package automation.test;

import automation.pom.BaseTest;
import automation.pom.HomePage;
import automation.pom.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;

public class LoginTests extends BaseTest {
    LoginPage loginpage;
    HomePage homepage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginpage = new LoginPage(driver, properties);
        homepage = new HomePage(driver, wait, properties);
    }

    @BeforeMethod
    void signInPage() {
        driver.get(properties.getProperty("BASE_URL") + "/signin");
    }

    @Test(priority = 1)
    void logInUser() {
        loginpage.logInUser();
        driver.findElement(homepage.logOutButton).click();
    }

    @Test(priority = 2)
    void logInUserInvalidData() {
        loginpage.loginInvalidData();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginpage.alertError));
        wait.until(ExpectedConditions.textToBe(By.cssSelector("[data-test='signin-error']"), "Username or password is invalid"));
    }

    @Test(priority = 3)
    void logInOneFieldEmpty() {
        loginpage.logInOneFieldEmpty();
        wait.until(ExpectedConditions.attributeContains(loginpage.signinButton, "disabled", ""));
        wait.until(ExpectedConditions.textToBe(By.id("username-helper-text"), "Username is required"));
    }

    @Test(priority = 4)
    void logInLessThanMin() {
        loginpage.logInLessThanMinPassword();
        driver.findElement(loginpage.rememberMe).click();
        wait.until(ExpectedConditions.textToBe(By.id("password-helper-text"), "Password must contain at least 4 characters"));
        wait.until(ExpectedConditions.attributeContains(loginpage.signinButton, "disabled", ""));
    }

    @Test(priority = 5)
    void checkboxWorkingProperly() {
        loginpage.rememberMeWorkingProperly();
    }

    @Test(priority = 6)
    void signupLinkWorkingProperly() {
        loginpage.registrationLink();
        wait.until(ExpectedConditions.presenceOfElementLocated(homepage.myAccount));
        wait.until(ExpectedConditions.textToBe(By.id("username-helper-text"), "Username is required"));
    }

    @AfterTest
    void browserClose() {
        this.closeBrowser();
    }
}