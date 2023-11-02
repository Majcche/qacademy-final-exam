package automation.test;

import automation.pom.BaseTest;
import automation.pom.HomePage;
import automation.pom.LoginPage;
import automation.pom.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import java.io.IOException;
import static org.testng.Assert.*;

public class RegistrationTests extends BaseTest {
    LoginPage loginPage;
    RegistrationPage registrationPage;
    HomePage homePage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        registrationPage = new RegistrationPage(driver, wait, properties);
        homePage = new HomePage(driver,wait,properties);
    }

    @BeforeMethod
    void goToSignUp() {
        driver.navigate().to(properties.getProperty("BASE_URL") + "/signup");
    }

    @Test (priority = 5) // kada je priority 1, onda sledeci test pada, ne znam razlog
    void registrationWithValidData() {
        registrationPage.signUpUser();
    }

    @Test(priority = 1)
    void withNonMatchingPassword() {
        registrationPage.nonMatchingPassword();
        wait.until(ExpectedConditions.attributeContains(registrationPage.signupButton, "disabled", ""));
        wait.until(ExpectedConditions.textToBe(By.id("confirmPassword-helper-text"), "Password does not match"));
    }

    @Test(priority = 2)
    void withOneFieldEmpty() {
        registrationPage.oneFieldEmpty();
        wait.until(ExpectedConditions.attributeContains(registrationPage.signupButton, "disabled", ""));
        wait.until(ExpectedConditions.textToBe(By.id("firstName-helper-text"), "First Name is required"));
    }

    @Test(priority = 3)
    void registrationLessThanMinPassword() {
        registrationPage.lessThanMinLengthPassword();
        wait.until(ExpectedConditions.attributeContains(registrationPage.signupButton, "disabled", ""));
        wait.until(ExpectedConditions.textToBe(By.id("password-helper-text"), "Password must contain at least 4 characters"));
    }

    @Test(priority = 4)
    void verifySignInLink() { // ovde treba da padne
        registrationPage.verifyLink();
        assertEquals(driver.getCurrentUrl(), "https://app.qacademy.rs/signin");
    }

    @AfterTest
    void browserClose() {
        this.closeBrowser();
    }
}
