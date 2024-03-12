package poms.productpages;

import assertions.Asserter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.base.BasePom;
import reporting.ExtendedReporter;

import static base.SeleniumBaseTest.*;
import static utils.WebDriverUtils.closeRequestedTab;
import static utils.WebDriverUtils.focusOnRequestedTab;

public class ProductPage extends BasePom {

	@FindBy(css = ".logo")
	protected WebElement vrxLogo;

	@FindBy(css = "a[href*='sign-in']")
	protected WebElement loginButton;

	@FindBy(css = "a[href*='sign/up']")
	private WebElement startFreeTrialButton;

	@FindBy(xpath = "//button[contains(text(), 'Product')]")
	protected WebElement productButton;

	@FindBy(css = ".link-holder a[href*='product']")
	protected WebElement productOverviewButton;

	@FindBy(xpath = "//button[contains(text(), 'Solution')]")
	protected WebElement solutionButton;

	@FindBy(css = ".link-holder a[href*='solution']")
	protected WebElement solutionOverviewButton;

	@FindBy(xpath = "//a[contains(text(), 'Pricing')]")
	protected WebElement pricingButton;

	@FindBy(xpath = "//button[contains(text(), 'Community')]")
	protected WebElement communityButton;

	@FindBy(css = ".link-holder a[href*='vsociety']")
	protected WebElement vsocietyButton;

	@FindBy(xpath = "//button[contains(text(), 'Company')]")
	protected WebElement companyButton;

	@FindBy(css = ".link-holder a[href*='company']")
	protected WebElement aboutButton;

	@FindBy(xpath = "//a[contains(text(), 'Contact')]")
	protected WebElement contactButton;

	@FindBy(xpath = "//span[contains(text(), 'Watch Demo')]")
	protected WebElement watchDemoButton;

	@FindBy(xpath = "//span[contains(text(), 'Analyze')]")
	protected WebElement analyzeCircle;

	@FindBy(xpath = "//span[contains(text(), 'Prioritize')]")
	protected WebElement prioritizeCircle;

	@FindBy(xpath = "//span[contains(text(), 'Act')]")
	protected WebElement actCircle;

	public ProductPage() throws Throwable {
		super();
		ExtendedReporter.log("Waiting for vrx logo to be displayed");
		wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(vrxLogo));
		Asserter.assertTrue(waitForUrlToBe(vicariusProductUrl, 5), "Expected url to be " + vicariusProductUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusProductUrl);
	}

	public void clickAndValidateLogin() throws Throwable {
		ExtendedReporter.log("Clicking and validating login button");
		loginButton.click();
		Thread.sleep(1000);
		focusOnRequestedTab(driver, 1);
		Asserter.assertTrue(waitForUrlToBe("https://sign-in.vicarius.cloud", 5), "Expected url to be " + "https://sign-in.vicarius.cloud" + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + "https://sign-in.vicarius.cloud");
		focusOnRequestedTab(driver, 0);
		closeRequestedTab(driver, 1);
	}

	public void clickAndValidateStartFreeTrial() throws Throwable {
		ExtendedReporter.log("Clicking and validating start free trial button");
		startFreeTrialButton.click();
		Thread.sleep(1000);
		Asserter.assertTrue(waitForUrlToBe(vicariusSignupUrl, 5), "Expected url to be " + vicariusSignupUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusSignupUrl);
		navigateBack();
		Thread.sleep(1000);
		Asserter.assertTrue(waitForUrlToBe(vicariusProductUrl, 5), "Expected url to be " + vicariusProductUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusProductUrl);
	}

	public void hoverOnProduct() throws Throwable {
		ExtendedReporter.log("Hovering on product");
		hoverOverElement(productButton);
		Asserter.assertTrue(isDisplayed(productOverviewButton), "Expected product overview to be displayed, but found that it's not", "Validating that product overview is displayed");
	}

	public void hoverOnSolution() throws Throwable {
		ExtendedReporter.log("Hovering on solution");
		hoverOverElement(solutionButton);
		Asserter.assertTrue(isDisplayed(solutionOverviewButton), "Expected solution overview to be displayed, but found that it's not", "Validating that solution overview is displayed");
	}

	public void hoverOnPricing() throws Throwable {
		ExtendedReporter.log("Hovering on pricing");
		hoverOverElement(pricingButton);
	}

	public void hoverOnCommunity() throws Throwable {
		ExtendedReporter.log("Hovering on community");
		hoverOverElement(communityButton);
		Asserter.assertTrue(isDisplayed(vsocietyButton), "Expected vsociety community to be displayed, but found that it's not", "Validating that vsociety community is displayed");
	}

	public void hoverOnCompany() throws Throwable {
		ExtendedReporter.log("Hovering on product");
		hoverOverElement(companyButton);
		Asserter.assertTrue(isDisplayed(aboutButton), "Expected about to be displayed, but found that it's not", "Validating that about is displayed");
	}

	public void hoverOnContact() throws Throwable {
		ExtendedReporter.log("Hovering on product");
		hoverOverElement(contactButton);
	}

	public void hoverOnAllTabs() throws Throwable {
		ExtendedReporter.log("Hovering on all tabs");
		hoverOnProduct();
		hoverOnSolution();
		hoverOnPricing();
		hoverOnCommunity();
		hoverOnCompany();
		hoverOnContact();
	}

	public boolean isWatchDemoDisplayed() throws Throwable {
		return isDisplayed(watchDemoButton);
	}

	public boolean isAnalyzeCircleDisplayed() throws Throwable {
		return isDisplayed(analyzeCircle);
	}

	public boolean isPrioritizeCircleDisplayed() throws Throwable {
		return isDisplayed(prioritizeCircle);
	}

	public boolean isActCircleDisplayed() throws Throwable {
		return isDisplayed(actCircle);
	}

}
