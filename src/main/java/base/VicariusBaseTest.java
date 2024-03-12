package base;

import assertions.Asserter;
import poms.mainpages.MainPage;
import poms.productpages.ProductPage;
import poms.mainpages.SigninPage;
import poms.mainpages.SignupPage;

public class VicariusBaseTest extends SeleniumBaseTest{

    protected MainPage mainPage;
    protected SignupPage signupPage;
    protected SigninPage signinPage;
    protected ProductPage productPage;

    protected String validEmail = "newnemy@ae.io";
    protected String personalEmail = "automation@gmail.com";

    protected String invalidEmail = "automation@123";

    protected String validPassword = "Asdf1234@";

    protected String invalidPassword = "Asdf";

    protected String validFirstName = "Automation";

    protected String validLaseName = "Automation";

    protected String validCompanyName = "Automation";

    protected String expectedSigninValidationErrorMessagePopup = "Validation failed\nPlease, verify if all fields are correctly filled.";

    protected String expectedSigninValidationErrorMessage = "Invalid email address try again or Get a Free Trial";

    protected String expectedSignupValidationErrorMessagePopup = "Let’s try again...\nPlease review your login details and try again.";

    protected String expectedSignupFieldIsRequiredErrorMessage = "Field is required.";

    protected String expectedSignupPersonalEmailErrorMessage = "Can't be a personal email.";

    protected String expectedSignuoPasswordRuleErrorMessage = "Must contain required characters.";

    protected String expectedSignuoPasswordsDoNotMatchErrorMessage = "Passwords do not match.";

    // Signup sanity test covers negative and positive flows
    protected void signupTest() throws Throwable {
        mainPage = new MainPage();
        mainPage.clickOnStartFreeTrial();

        signupPage = new SignupPage();

        // Validate field is required message on all fields
        signupPage.clickOnContinue();

        String fieldErrorMessage = signupPage.getFirstNameErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignupFieldIsRequiredErrorMessage, "Expected first name field error message to be " + expectedSignupFieldIsRequiredErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that first name field error message is " + expectedSignupFieldIsRequiredErrorMessage);
        fieldErrorMessage = signupPage.getLastNameErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignupFieldIsRequiredErrorMessage, "Expected last name field error message to be " + expectedSignupFieldIsRequiredErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that last name field error message is " + expectedSignupFieldIsRequiredErrorMessage);
        fieldErrorMessage = signupPage.getWorkEmailErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignupFieldIsRequiredErrorMessage, "Expected work email field error message to be " + expectedSignupFieldIsRequiredErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that work email field error message is " + expectedSignupFieldIsRequiredErrorMessage);
        fieldErrorMessage = signupPage.getCompanyErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignupFieldIsRequiredErrorMessage, "Expected company field error message to be " + expectedSignupFieldIsRequiredErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that company field error message is " + expectedSignupFieldIsRequiredErrorMessage);

        // Validate review your login details popup
        Asserter.assertTrue(signupPage.isValidationNotificationPopupDisplayed(), "Expected validation notification to be displayed, but it's not", "Validating that validation notification is displayed");
        String notificationMessage = signupPage.getValidationNotificationPopupText();
        Asserter.assertEquals(notificationMessage, expectedSignupValidationErrorMessagePopup, "Expected notification message to be " + expectedSignupValidationErrorMessagePopup + " but found that it's " + notificationMessage);
        signupPage.clickOnCloseNotificationPopupButton();

        signupPage.fillFirstName(validFirstName);
        signupPage.fillLastName(validLaseName);
        signupPage.fillEmailAddress(personalEmail);
        signupPage.clickOnContinue();

        // Validate personal email error message
        fieldErrorMessage = signupPage.getWorkEmailErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignupPersonalEmailErrorMessage, "Expected work email field error message to be " + expectedSignupFieldIsRequiredErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that work email field error message is " + expectedSignupPersonalEmailErrorMessage);
        fieldErrorMessage = signupPage.getCompanyErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignupFieldIsRequiredErrorMessage, "Expected company field error message to be " + expectedSignupFieldIsRequiredErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that company field error message is " + expectedSignupFieldIsRequiredErrorMessage);

        Asserter.assertTrue(signupPage.isValidationNotificationPopupDisplayed(), "Expected validation notification to be displayed, but it's not", "Validating that validation notification is displayed");
        notificationMessage = signupPage.getValidationNotificationPopupText();
        Asserter.assertEquals(notificationMessage, expectedSignupValidationErrorMessagePopup, "Expected notification message to be " + expectedSignupValidationErrorMessagePopup + " but found that it's " + notificationMessage);
        signupPage.clickOnCloseNotificationPopupButton();

        signupPage.fillSignupDetailsAndContinue(validFirstName, validLaseName, validEmail, validCompanyName);

        // Check negative and positive password flow, verify that all fields are required and then check some invalid password
        signupPage.clickOnContinue();

        fieldErrorMessage = signupPage.getPasswordErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignupFieldIsRequiredErrorMessage, "Expected password field error message to be " + expectedSignupFieldIsRequiredErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that password field error message is " + expectedSignupFieldIsRequiredErrorMessage);
        Asserter.assertTrue(signupPage.isAllPasswordRulesInvalid(), "Expected all password rules to be invalid, but found that they are not", "Validating that all password rules are invalid");

        signupPage.fillPasswordField(invalidPassword);
        fieldErrorMessage = signupPage.getPasswordErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignuoPasswordRuleErrorMessage, "Expected password field error message to be " + expectedSignuoPasswordRuleErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that password field error message is " + expectedSignuoPasswordRuleErrorMessage);
        fieldErrorMessage = signupPage.getConfirmPasswordErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignuoPasswordsDoNotMatchErrorMessage, "Expected confirm password field error message to be " + expectedSignuoPasswordsDoNotMatchErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that confirm password field error message is " + expectedSignuoPasswordsDoNotMatchErrorMessage);
        Asserter.assertTrue(signupPage.isPasswordCharactersNumberRuleInvalid(), "Expected 8 - 30 characters password rule to be invalid, but found that it's not", "Validating that 8 - 30 password rule is invalid");
        Asserter.assertFalse(signupPage.isPasswordLowerCaseRuleInvalid(), "Expected lowercase letter password rule to be valid, but found that it's not", "Validating that lowercase letter password rule is valid");
        Asserter.assertFalse(signupPage.isPasswordUpperCaseRuleInvalid(), "Expected uppercase letter password rule to be valid, but found that it's not", "Validating that uppercase letter password rule is valid");
        Asserter.assertTrue(signupPage.isPasswordNumberRuleInvalid(), "Expected number password rule to be invalid, but found that it's not", "Validating that number password rule is invalid");
        Asserter.assertTrue(signupPage.isPasswordSpecialCharactersRuleInvalid(), "Expected special character password rule to be invalid, but found that it's not", "Validating that special character password rule is invalid");

        // Verify password do not match case
        signupPage.fillPasswordField(validPassword);
        signupPage.fillConfirmPasswordField(invalidPassword);
        fieldErrorMessage = signupPage.getConfirmPasswordErrorMessageText();
        Asserter.assertEquals(fieldErrorMessage, expectedSignuoPasswordsDoNotMatchErrorMessage, "Expected confirm password field error message to be " + expectedSignuoPasswordsDoNotMatchErrorMessage + " but found that it's " + fieldErrorMessage, "Validating that confirm password field error message is " + expectedSignuoPasswordsDoNotMatchErrorMessage);

        // Verify positive flow
        signupPage.fillPasswordDetailsAndContinue(validPassword);
        Asserter.assertTrue(signupPage.isVerificationEmailMessageDisplayed(), "Expected verification email message to be displayed, but it's not", "Validating that verification email message is displayed");
    }

    // Signin sanity test covers negative and positive flows
    protected void signinTest() throws Throwable {
        signupPage.navigateToUrl(vicariusSigninUrl);

        // Verify navigation to signup page and get back to signup page
        signinPage = new SigninPage();
        signinPage.clickOnStartFreeTrial();
        Asserter.assertTrue(signinPage.waitForUrlToBe(vicariusSignupUrl, 5), "Expected url to be " + vicariusSignupUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusSignupUrl);
        signinPage.navigateBack();
        Asserter.assertTrue(signinPage.waitForUrlToBe(vicariusSigninUrl, 5), "Expected url to be " + vicariusSigninUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusSigninUrl);

        signinPage.clickOnLogin();

        // Validate invalid email error message
        String invalidEmailErrorMessage = signinPage.getInvalidEmailErrorMessageText();
        Asserter.assertEquals(invalidEmailErrorMessage, expectedSigninValidationErrorMessage, "Expected invalid email error message to be " + expectedSigninValidationErrorMessage + " but found that it's " + invalidEmailErrorMessage, "Validating that invalid email error message is " + expectedSigninValidationErrorMessage);

        // Validate notification popup error
        Asserter.assertTrue(signinPage.isValidationNotificationPopupDisplayed(), "Expected validation notification to be displayed, but it's not", "Validating that validation notification is displayed");
        String notificationMessage = signinPage.getValidationNotificationPopupText();
        Asserter.assertEquals(notificationMessage, expectedSigninValidationErrorMessagePopup, "Expected notification message to be " + expectedSigninValidationErrorMessagePopup + " but found that it's " + notificationMessage);
        signinPage.clickOnCloseNotificationPopupButton();

        //Validate invalid email case
        signinPage.fillEmailAddress(invalidEmail);
        signinPage.clickOnLogin();

        invalidEmailErrorMessage = signinPage.getInvalidEmailErrorMessageText();
        Asserter.assertEquals(invalidEmailErrorMessage, expectedSigninValidationErrorMessage, "Expected invalid email error message to be " + expectedSigninValidationErrorMessage + " but found that it's " + invalidEmailErrorMessage, "Validating that invalid email error message is " + expectedSigninValidationErrorMessage);

        Asserter.assertTrue(signinPage.isValidationNotificationPopupDisplayed(), "Expected validation notification to be displayed, but it's not", "Validating that validation notification is displayed");
        notificationMessage = signinPage.getValidationNotificationPopupText();
        Asserter.assertEquals(notificationMessage, expectedSigninValidationErrorMessagePopup, "Expected notification message to be " + expectedSigninValidationErrorMessagePopup + " but found that it's " + notificationMessage);
        signinPage.clickOnCloseNotificationPopupButton();

        // Validate navigation to to get a free trial
        signinPage.clickOnGetAFreeTrial();
        Asserter.assertTrue(signinPage.waitForUrlToBe(vicariusSignupUrl, 5), "Expected url to be " + vicariusSignupUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusSignupUrl);
        signinPage.navigateBack();
        Asserter.assertTrue(signinPage.waitForUrlToBe(vicariusSigninUrl, 5), "Expected url to be " + vicariusSigninUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusSigninUrl);

        // Validate Positive flow
        signinPage.fillEmailAddressAndLogin(validEmail);
        Asserter.assertTrue(signinPage.waitUntilNotificationEmailPopupIsDisplayed(5), "Expected notification email popup to be displayed in 5 seconds, but timeout is over", "Validating that notification email popup is being displayed");
    }

    //Product page sanity test is validating login and logout url, it's also hovering and scrolling over elements
    protected void productPageTest() throws Throwable {
        signinPage.navigateToUrl(vicariusBaseUrl);

        // Navigate to product page
        mainPage.hoverAndClickOnProductOverview();

        productPage = new ProductPage();

        // Validate login and start free trial links
        productPage.clickAndValidateLogin();
        productPage.clickAndValidateStartFreeTrial();

        // Hover check on all the tabs and scroll through the page
        productPage.hoverOnAllTabs();
        Asserter.assertTrue(productPage.isWatchDemoDisplayed(), "Expected watch demo button to be displayed, but found that it's not", "Validating that watch demo button is displayed");
        productPage.scrollBy(0, 1500);
        Asserter.assertTrue(productPage.isAnalyzeCircleDisplayed(), "Expected analyze circle to be displayed, but found that it's not", "Validating that analyze circle is displayed");
        productPage.scrollBy(0, 1500);
        Asserter.assertTrue(productPage.isPrioritizeCircleDisplayed(), "Expected prioritize circle to be displayed, but found that it's not", "Validating that prioritize circle is displayed");
        productPage.scrollBy(0, 1800);
        Asserter.assertTrue(productPage.isActCircleDisplayed(), "Expected act circle to be displayed, but found that it's not", "Validating that act circle is displayed");
    }

}
