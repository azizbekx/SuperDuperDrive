package com.udacity.jwdnd.course1.cloudstorage.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsPage {
    private WebDriverWait wait;

    @FindBy(css = "#nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(css = "#showCredentialForm")
    private WebElement showCredentialForm;

    @FindBy(css = "#credential-url")
    private WebElement credentialUrl;

    @FindBy(css = "#credential-username")
    private WebElement credentialUsername;

    @FindBy(css = "#credential-password")
    private WebElement credentialPassword;

    @FindBy(css = "#credentialSubmit")
    private WebElement credentialSubmit;

    @FindBy(xpath = "//*[@id='credentialTable']/tbody/tr/td[1]/button")
    private WebElement editButton;

    @FindBy(xpath = "//*[@id='credentialTable']/tbody/tr/td[1]/a")
    private WebElement deleteButton;

    @FindBy(css = "note-title-text")
    private WebElement noteTitleText;

    @FindBy(css = "note-description-text")
    private WebElement noteDescriptionText;

    public CredentialsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addCredential(String url, String username, String password,WebDriver driver) throws InterruptedException {
        this.wait = new WebDriverWait(driver, 1);

        Thread.sleep(1000);
        credentialsTab.click();


        wait.until(ExpectedConditions.elementToBeClickable(showCredentialForm));
        Assertions.assertNotNull(showCredentialForm);
        showCredentialForm.click();

        wait.until(ExpectedConditions.visibilityOf(credentialUrl));
        credentialUrl.sendKeys(url);
        credentialUsername.sendKeys(username);
        credentialPassword.sendKeys(password);

        credentialSubmit.submit();
    }
    public void editCredential(String changeUrl, String changeUsername, String changePassword, WebDriver driver) throws InterruptedException {
        this.wait = new WebDriverWait(driver, 1);
        Thread.sleep(1000);
        credentialsTab.click();

        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        Assertions.assertNotNull(editButton);
        editButton.click();

        wait.until(ExpectedConditions.visibilityOf(credentialUrl));
        credentialUrl.clear();
        credentialUrl.sendKeys(changeUrl);
        credentialUsername.clear();
        credentialUsername.sendKeys(changeUsername);
        credentialPassword.clear();
        credentialPassword.sendKeys(changePassword);

        credentialSubmit.submit();

    }

    public void deleteCredential(WebDriver driver) throws InterruptedException {
        this.wait = new WebDriverWait(driver, 1);

        Thread.sleep(1000);
        credentialsTab.click();

        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        Thread.sleep(1000);
        deleteButton.click();
    }

}
