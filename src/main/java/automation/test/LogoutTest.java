package automation.test;

import automation.pom.BaseTest;
import automation.pom.HomePage;
import automation.pom.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class LogoutTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties);
        homePage = new HomePage(driver,wait,properties);
    }
    @BeforeMethod
      void signInPage(){
        driver.navigate().to(properties.getProperty("BASE_URL") + "/signin");
    }

    @Test // user je izlogovan, ali sa error porukom
    void logoutUser(){
        loginPage.logInUser();
        homePage.logoutUser();
        assertEquals(driver.getCurrentUrl(), "https://app.qacademy.rs/");
        wait.until(ExpectedConditions.textToBe(By.className("MuiAlert-message"), "Request failed with status code 500"));
    }
    @AfterTest
    void browserClose(){
        this.closeBrowser();
    }
}
