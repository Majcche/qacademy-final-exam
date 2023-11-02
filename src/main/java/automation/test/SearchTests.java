package automation.test;

import automation.pom.*;
import org.testng.annotations.*;
import automation.pom.HomePage;
import automation.pom.LoginPage;
import automation.pom.NewTransactionPage;

import java.io.IOException;

public class SearchTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    NewTransactionPage newTransactionPage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        newTransactionPage = new NewTransactionPage(driver,wait,properties);
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver, wait, properties);

    }
    @BeforeMethod
    void goToNewTransaction() {
        driver.navigate().to(properties.getProperty("BASE_URL") + "/signin");
        loginPage.logInUser();
        driver.findElement(homePage.newTransactionButton).click();
    }
    @Test(priority = 1)
    void searchPersonUsingFirstName(){
        newTransactionPage.searchUsingName();
    }
    @Test(priority = 2)
    void searchPersonUsingUsername(){
        newTransactionPage.searchPersonUsingUsername();
    }
    @Test(priority = 3)
    void searchPersonUsingEmail(){
        newTransactionPage.searchUsingEmail();
    }
    @Test(priority = 4)
    void searchPersonUsingAccountNumber(){
        newTransactionPage.searchAccountNumber();
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
