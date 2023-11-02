package automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BankAccountPage {
    WebDriver driver;
    WebDriverWait wait;
    Properties properties;

    By createButton = By.cssSelector("[data-test='bankaccount-new']");
    By bankName = By.id("bankaccount-bankName-input");
    By routingNumber = By.id("bankaccount-routingNumber-input");
    By accountNumber = By.name("accountNumber");
   public By saveButton = By.cssSelector("[data-test='bankaccount-submit']");
   By deleteButton = By.cssSelector("[data-test='bankaccount-delete']");

    public BankAccountPage(WebDriver driver, WebDriverWait wait, Properties properties) {
        this.driver = driver;
        this.wait = wait;
        this.properties = properties;
    }
    public void enterBankName(){
        driver.findElement(bankName).sendKeys(properties.getProperty("BANKNAME"));
    }
    public void enterRoutingNumber(){
        driver.findElement(routingNumber).sendKeys(properties.getProperty("ROUTINGNUMBER"));
    }
    public void enterAccountNumber(){
        driver.findElement(accountNumber).sendKeys(properties.getProperty("ACCOUNTNUMBER"));
    }
   public void createBankAccount(){
        driver.findElement(createButton).click();
        this.enterBankName();
        this.enterRoutingNumber();
        this.enterAccountNumber();
        driver.findElement(saveButton).click();
    }
    public void minNameLength(){
        driver.findElement(createButton).click();
        driver.findElement(bankName).sendKeys("abcd");
        this.enterRoutingNumber();
        this.enterAccountNumber();
    }
    public void oneFieldEmpty(){
        driver.findElement(createButton).click();
        this.enterBankName();
        this.enterRoutingNumber();
    }
    public void charInNumberFields(){
        driver.findElement(createButton).click();
        this.enterBankName();
        driver.findElement(routingNumber).sendKeys("aaaaaaaaa");
        driver.findElement(accountNumber).sendKeys("bbbbbbbbb");
        driver.findElement(saveButton).click();
    }
    public void invalidNumbers(){
        driver.findElement(createButton).click();
        this.enterBankName();
        driver.findElement(routingNumber).sendKeys("12345678");
        driver.findElement(accountNumber).sendKeys("1234567899999");
    }
    public void deleteButton(){
        driver.findElement(deleteButton).click();
    }
}
