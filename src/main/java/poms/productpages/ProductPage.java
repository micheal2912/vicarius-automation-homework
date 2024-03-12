package poms.productpages;

import assertions.Asserter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.base.BasePom;
import reporting.ExtendedReporter;

import static base.SeleniumBaseTest.*;
import static utils.WebDriverUtils.*;

public class ProductPage extends BasePom {

	@FindBy(css = ".logo")
	protected WebElement vrxLogo;

	@FindBy(css = "a[href*='sign-in']")
	protected WebElement loginButton;

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

	public WebElement getProductOverviewButton() {
		return productOverviewButton;
	}

	public WebElement getSolutionOverviewButton() {
		return solutionOverviewButton;
	}

	public WebElement getVsocietyButton() {
		return vsocietyButton;
	}

	public WebElement getAboutButton() {
		return aboutButton;
	}

	public void clickOnLogin() throws Throwable {
		ExtendedReporter.log("Clicking on login button");
		loginButton.click();
		Thread.sleep(1000);
	}

	public void hoverOnProduct() throws Throwable {
		ExtendedReporter.log("Hovering on product");
		hoverOverElement(productButton);
	}

	public void hoverOnSolution() throws Throwable {
		ExtendedReporter.log("Hovering on solution");
		hoverOverElement(solutionButton);
	}

	public void hoverOnPricing() throws Throwable {
		ExtendedReporter.log("Hovering on pricing");
		hoverOverElement(pricingButton);
	}

	public void hoverOnCommunity() throws Throwable {
		ExtendedReporter.log("Hovering on community");
		hoverOverElement(communityButton);
	}

	public void hoverOnCompany() throws Throwable {
		ExtendedReporter.log("Hovering on product");
		hoverOverElement(companyButton);
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
