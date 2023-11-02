package automation.test;


import automation.pom.BaseTest;
import automation.pom.HomePage;
import automation.pom.LoginPage;
import automation.pom.MyAccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;

public class MyAccountTests extends BaseTest {
    MyAccountPage myAccountPage;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        myAccountPage = new MyAccountPage(driver, wait, properties);
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver, wait, properties);

    }

    @BeforeMethod
    void goToUserSettings() {
        driver.navigate().to(properties.getProperty("BASE_URL") + "/signin");
        loginPage.logInUser();
        driver.findElement(homePage.myAccount).click();
        myAccountPage.clearFields();

    }

    @Test(priority = 1)
    void saveUserSettings() {
        myAccountPage.saveUserSettings();
    }

    @Test(priority = 2)
    void enterCharInNumberField() {
        myAccountPage.charInNumberField();
        wait.until(ExpectedConditions.attributeContains(myAccountPage.saveSettingsButton, "disabled", ""));
        wait.until(ExpectedConditions.textToBe(By.id("user-settings-phoneNumber-input-helper-text"), "Phone number is not valid"));
    }

    @Test(priority = 3)
    void leaveFirstFieldEmpty() {
        myAccountPage.oneFieldEmpty();
        wait.until(ExpectedConditions.attributeContains(myAccountPage.saveSettingsButton, "disabled", ""));
        wait.until(ExpectedConditions.textToBe(By.id("user-settings-firstName-input-helper-text"), "Enter a first name"));
    }

    @Test(priority = 4)
    void enterInvalidEmail() { //nije disabled
        myAccountPage.invalidEmail();
        wait.until(ExpectedConditions.attributeContains(myAccountPage.saveSettingsButton, "disabled",""));
        wait.until(ExpectedConditions.textToBe(By.id("user-settings-email-input-helper-text"), "Enter an email address"));
    }
    @AfterMethod
    void logoutUser() {
        homePage.logoutUser();
    }

    @AfterTest
    void browserClose() {
        this.closeBrowser();
    }
}





