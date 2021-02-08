package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.page.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();

	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:"+port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}
	@Test
	@Order(1)
	public void test_loginAndSignUp() throws InterruptedException {
		String username="azizbekx";
		String password="hello";

		driver.get(baseURL+"/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Azizbek", "Khushvakov", username, password);


		driver.get(baseURL+"/login");

		LoginPage loginPage =new LoginPage(driver);
		loginPage.login(username,password);




	}
	@Test
	public void test_note() throws InterruptedException{
		this.test_loginAndSignUp();
		NotesPage notesPage = new NotesPage(driver);
		ResultPage resultPage = new ResultPage(driver);


		String noteTitle="Test Note";
		String noteDescription = "-Success test , finished test";

		notesPage.addNote(noteTitle,noteDescription,driver);

		String changeNoteTitle="Changed Note";
		String changeNoteDescription = "- Good test";

		resultPage.clickSuccessResult();
		notesPage.editNote(changeNoteTitle,changeNoteDescription,driver);

		resultPage.clickSuccessResult();
		notesPage.deleteNote(driver);
	}
	@Test
	public void test_credential() throws InterruptedException{
		this.test_loginAndSignUp();
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		ResultPage resultPage = new ResultPage(driver);

		String url = "github.com";
		String username = "azizbekx";
		String password = "password";


		credentialsPage.addCredential(url,username,password, driver);

		String changeUrl="gitlab.com";
		String changeUsername = "azizbek";
		String changePassword="changePassword";
		resultPage.clickSuccessResult();
		credentialsPage.editCredential(changeUrl,changeUsername,changePassword,driver);

		resultPage.clickSuccessResult();
		credentialsPage.deleteCredential(driver);
	}

}
