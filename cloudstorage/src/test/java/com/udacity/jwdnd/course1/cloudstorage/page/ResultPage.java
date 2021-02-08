package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(css = "#successResult")
    private WebElement successResult;

    @FindBy(css = "#errorResult1")
    private WebElement errorResult1;

    @FindBy(css = "#errorResult2")
    private WebElement errorResult2;

    @FindBy(css = "#errorResult3")
    private WebElement errorResult4;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);

    }

    public void clickSuccessResult() throws InterruptedException {
        Thread.sleep(1000);
        successResult.click();
    }

}
