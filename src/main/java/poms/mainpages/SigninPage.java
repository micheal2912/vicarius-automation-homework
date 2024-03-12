package poms.mainpages;

import assertions.Asserter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.base.BasePom;
import reporting.ExtendedReporter;

import static base.SeleniumBaseTest.vicariusSigninUrl;

public class SigninPage extends BasePom {

	@FindBy(css = ".login .logo")
	protected WebElement vrxLogo;

	@FindBy(xpath = "//input[@placeholder='Work e-mail']")
	protected WebElement emailField;

	@FindBy(className = "forgot")
	private WebElement forgotMyEmail;

	@FindBy(css = "button[type='submit']")
	protected WebElement loginButton;

	@FindBy(css = ".type-email")
	protected WebElement notificationEmail;

	@FindBy(className = "error-message")
	protected WebElement invalidEmailErrorMessage;

	@FindBy(xpath = "//a[contains(text(), 'Get a Free Trial')]")
	protected WebElement getAFreeTrialLink;

	public SigninPage() throws Throwable {
		super();
		ExtendedReporter.log("Waiting for vrx logo to be displayed");
		wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(vrxLogo));
		Asserter.assertTrue(waitForUrlToBe(vicariusSigninUrl, 5), "Expected url to be " + vicariusSigninUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusSigninUrl);
	}

	public void fillEmailAddress(String email) throws Throwable {
		ExtendedReporter.log("Filling email field");
		emailField.clear();
		emailField.sendKeys(email);
	}

	public void clickOnLogin() throws Throwable {
		ExtendedReporter.log("Clicking on login");
		loginButton.click();
		Thread.sleep(1000);
	}

	public void fillEmailAddressAndLogin(String email) throws Throwable {
		ExtendedReporter.log("Filling email address and logging in");
		fillEmailAddress(email);
		clickOnLogin();
	}

	public void clickOnForgotEmail() throws Throwable {
		ExtendedReporter.log("Clicking on forgot my email");
		forgotMyEmail.click();
		Thread.sleep(1000);
	}

	public boolean waitUntilNotificationEmailPopupIsDisplayed(int timeout) throws Throwable {
		ExtendedReporter.log("Waiting for notification email to be displayed");
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.visibilityOf(notificationEmail));
			return true;
		}
		catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isInvalidEmailErrorMessageDisplayed() throws Throwable {
		return isDisplayed(invalidEmailErrorMessage);
	}

	public String getInvalidEmailErrorMessageText() throws Throwable {
		return invalidEmailErrorMessage.getText();
	}

	public void clickOnGetAFreeTrial() throws Throwable {
		ExtendedReporter.log("Clicking on get a free trial button");
		getAFreeTrialLink.click();
		Thread.sleep(1000);
	}

}
