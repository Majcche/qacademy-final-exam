package automation.test;

import automation.pom.BankAccountPage;
import automation.pom.BaseTest;
import automation.pom.HomePage;
import automation.pom.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import java.io.IOException;

public class BankAccountTests extends BaseTest {
    BankAccountPage bankAccountPage;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        bankAccountPage = new BankAccountPage(driver, wait, properties);
        homePage = new HomePage(driver, wait, properties);
    }
    @BeforeMethod
    void navigateToBankAccount() {
        driver.navigate().to(properties.getProperty("BASE_URL") + "/signin");
        loginPage.logInUser();
        driver.findElement(homePage.bankAccounts).click();
    }
        @Test(priority = 1)
        void newBankAccount() {
            bankAccountPage.createBankAccount();
        }
        @Test(priority = 2)
        void withMinNameLength() {
            bankAccountPage.minNameLength();
            wait.until(ExpectedConditions.attributeContains(bankAccountPage.saveButton, "disabled", ""));
            wait.until(ExpectedConditions.textToBe(By.id("bankaccount-bankName-input-helper-text"), "Must contain at least 5 characters"));
        }
        @Test(priority = 3)
        void withOneEmptyField() {
            bankAccountPage.oneFieldEmpty();
            wait.until(ExpectedConditions.attributeContains(bankAccountPage.saveButton, "disabled", ""));
        }
        @Test(priority = 4)
        void withCharInNumberFields() {
            bankAccountPage.charInNumberFields();
            wait.until(ExpectedConditions.attributeContains(bankAccountPage.saveButton, "disabled", ""));
        }
        @Test(priority = 5)
        void withInvalidNumbers() {
            bankAccountPage.invalidNumbers();
            wait.until(ExpectedConditions.attributeContains(bankAccountPage.saveButton, "disabled", ""));
            wait.until(ExpectedConditions.textToBe(By.id("bankaccount-routingNumber-input-helper-text"), "Must contain a valid routing number"));
            wait.until(ExpectedConditions.textToBe(By.id("bankaccount-accountNumber-input-helper-text"), "Must contain no more than 12 digits"));
        }
        @Test(priority = 6)
        void verifyDeleteButton() {
            bankAccountPage.deleteButton();
        }
        @AfterMethod
        void signOutUser () {
            homePage.logoutUser();
        }

        @AfterTest
        void browserClose () {
            this.closeBrowser();
        }
    }