package poms.mainpages;

import assertions.Asserter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.base.BasePom;
import reporting.ExtendedReporter;

import static base.SeleniumBaseTest.vicariusSignupUrl;

public class SignupPage extends BasePom {

	@FindBy(className = "signup-form")
	protected WebElement signupForm;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	protected WebElement firstNameField;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	protected WebElement lastNameField;

	@FindBy(xpath = "//input[@placeholder='Work e-mail']")
	protected WebElement workEmailField;

	@FindBy(xpath = "//input[@placeholder='Company']")
	protected WebElement companyField;

	@FindBy(css = "button[type='submit']")
	protected WebElement continueButton;

	@FindBy(xpath = "//input[@placeholder='Password']")
	protected WebElement passwordField;

	@FindBy(xpath = "//input[@placeholder='Confirm Password']")
	protected WebElement confirmPasswordField;

	@FindBy(css = ".signup-form p")
	protected WebElement verificationEmailMessage;

	@FindBy(xpath = "//li[contains(text(), '8 - 30 characters')]")
	protected WebElement passwordCharactersNumberRule;

	@FindBy(xpath = "//li[contains(text(), 'Lowercase letter')]")
	protected WebElement passwordLowerCaseRule;

	@FindBy(xpath = "//li[contains(text(), 'Uppercase letter')]")
	protected WebElement passwordUpperCaseRule;

	@FindBy(xpath = "//li[contains(text(), 'Number')]")
	protected WebElement passwordNumberRule;

	@FindBy(xpath = "//li[contains(text(), 'Special character')]")
	protected WebElement passwordSpecialCharactersRule;

	public SignupPage() throws Throwable {
		super();
		ExtendedReporter.log("Waiting for signup form to be displayed");
		wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(signupForm));
		Asserter.assertTrue(waitForUrlToBe(vicariusSignupUrl, 5), "Expected url to be " + vicariusSignupUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusSignupUrl);
	}

	public void fillFirstName(String firstName) throws Throwable {
		ExtendedReporter.log("Filling first name field");
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
	}

	public void fillLastName(String lastName) throws Throwable {
		ExtendedReporter.log("Filling last name field");
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
	}

	public void fillEmailAddress(String email) throws Throwable {
		ExtendedReporter.log("Filling work email field");
		workEmailField.clear();
		workEmailField.sendKeys(email);
	}

	public void fillCompany(String company) throws Throwable {
		ExtendedReporter.log("Filling company field");
		companyField.clear();
		companyField.sendKeys(company);
	}

	public void fillSignupDetailsAndContinue(String firstName, String lastName, String email, String company) throws Throwable {
		ExtendedReporter.log("Filling signup details and continue");
		fillFirstName(firstName);
		fillLastName(lastName);
		fillEmailAddress(email);
		fillCompany(company);
		clickOnContinue();
	}

	public void clickOnContinue() throws Throwable {
		ExtendedReporter.log("Clicking on continue");
		continueButton.click();
		Thread.sleep(1000);
	}

	public void fillPasswordField(String password) throws Throwable {
		ExtendedReporter.log("Filling password field");
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void fillConfirmPasswordField(String password) throws Throwable {
		ExtendedReporter.log("Filling confirm password field");
		confirmPasswordField.clear();
		confirmPasswordField.sendKeys(password);
	}

	public void fillPasswordDetailsAndContinue(String password) throws Throwable {
		ExtendedReporter.log("Filling password details and continue");
		fillPasswordField(password);
		fillConfirmPasswordField(password);
		clickOnContinue();
	}

	public boolean isVerificationEmailMessageDisplayed() throws Throwable {
		ExtendedReporter.log("Validating that verification email message is displayed");
		return isDisplayed(verificationEmailMessage);
	}

	public String getFirstNameErrorMessageText() {
		return getElementGrandfather(firstNameField).findElement(By.className("error")).getText();
	}

	public String getLastNameErrorMessageText() {
		return getElementGrandfather(lastNameField).findElement(By.className("error")).getText();
	}

	public String getWorkEmailErrorMessageText() {
		return getElementGrandfather(workEmailField).findElement(By.className("error")).getText();
	}

	public String getCompanyErrorMessageText() {
		return getElementGrandfather(companyField).findElement(By.className("error")).getText();
	}

	public String getPasswordErrorMessageText() {
		return getElementGrandfather(passwordField).findElement(By.className("error")).getText();
	}

	public String getConfirmPasswordErrorMessageText() {
		return getElementGrandfather(confirmPasswordField).findElement(By.className("error")).getText();
	}

	public boolean isPasswordCharactersNumberRuleInvalid() {
		return passwordCharactersNumberRule.getAttribute("class").contains("is-invalid");
	}

	public boolean isPasswordLowerCaseRuleInvalid() {
		return passwordLowerCaseRule.getAttribute("class").contains("is-invalid");
	}

	public boolean isPasswordUpperCaseRuleInvalid() {
		return passwordUpperCaseRule.getAttribute("class").contains("is-invalid");
	}

	public boolean isPasswordNumberRuleInvalid() {
		return passwordNumberRule.getAttribute("class").contains("is-invalid");
	}

	public boolean isPasswordSpecialCharactersRuleInvalid() {
		return passwordSpecialCharactersRule.getAttribute("class").contains("is-invalid");
	}

	public boolean isAllPasswordRulesInvalid() {
		return (isPasswordCharactersNumberRuleInvalid() && isPasswordLowerCaseRuleInvalid() && isPasswordUpperCaseRuleInvalid() && isPasswordNumberRuleInvalid() && isPasswordSpecialCharactersRuleInvalid());
	}

}
