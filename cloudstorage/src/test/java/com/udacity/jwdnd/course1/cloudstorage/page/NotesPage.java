package com.udacity.jwdnd.course1.cloudstorage.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesPage {
    private WebDriverWait wait;

    @FindBy(css = "#nav-notes-tab")
    private WebElement notesTab;

    @FindBy(css = "#showNoteForm")
    private WebElement showNoteForm;

    @FindBy(css = "#note-title")
    private WebElement noteTitleField;

    @FindBy(css = "#note-description")
    private WebElement noteDescriptionField;

    @FindBy(css = "#noteSubmit")
    private WebElement noteSubmitButton;

    @FindBy(xpath = "//*[@id='userTable']/tbody/tr/td[1]/button")
    private WebElement editNoteButton;

    @FindBy(xpath = "//*[@id='userTable']/tbody/tr/td[1]/a")
    private WebElement deleteNoteButton;

    @FindBy(css = "note-title-text")
    private WebElement noteTitleText;

    @FindBy(css = "note-description-text")
    private WebElement noteDescriptionText;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void addNote(String noteTitle, String noteDescription, WebDriver driver) throws InterruptedException {

        this.wait = new WebDriverWait(driver, 1);

        Thread.sleep(1000);
        notesTab.click();

        wait.until(ExpectedConditions.elementToBeClickable(showNoteForm));
        Assertions.assertNotNull(showNoteForm);
        showNoteForm.click();

        wait.until(ExpectedConditions.visibilityOf(noteTitleField));
        noteTitleField.sendKeys(noteTitle);
        noteDescriptionField.sendKeys(noteDescription);
        noteSubmitButton.submit();


    }

    public void editNote(String changeNoteTitle, String changeNoteDescription, WebDriver driver) throws InterruptedException {


        Thread.sleep(1000);
        notesTab.click();

        this.wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.elementToBeClickable(editNoteButton));
        editNoteButton.click();

        wait.until(ExpectedConditions.visibilityOf(noteTitleField));
        noteTitleField.clear();
        noteTitleField.sendKeys(changeNoteTitle);
        noteDescriptionField.clear();
        noteDescriptionField.sendKeys(changeNoteDescription);

        noteSubmitButton.submit();
    }

    public void deleteNote(WebDriver driver) throws InterruptedException {
        this.wait = new WebDriverWait(driver, 1);
        Thread.sleep(1000);
        notesTab.click();
        wait.until(ExpectedConditions.visibilityOf(deleteNoteButton));
        deleteNoteButton.click();
    }

}
