package poms.mainpages;

import assertions.Asserter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.base.BasePom;
import reporting.ExtendedReporter;

import static base.SeleniumBaseTest.vicariusBaseUrl;

public class MainPage extends BasePom {

	@FindBy(css = ".header-grey .logo")
	protected WebElement vicariusLogo;

	@FindBy(css = "a[href*='sign-in']")
	protected WebElement loginButton;

	@FindBy(css = "a[href*='sign/up']")
	private WebElement startFreeTrialButton;

	@FindBy(xpath = "//button[contains(text(), 'Product')]")
	protected WebElement productButton;

	@FindBy(css = ".large-link a[href*='product']")
	protected WebElement productOverviewButton;

	public MainPage() throws Throwable {
		super();
		ExtendedReporter.log("Waiting for vrx logo to be displayed");
		wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(vicariusLogo));
		Asserter.assertTrue(driver.getCurrentUrl().contains(vicariusBaseUrl), "Expected url to be " + vicariusBaseUrl + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + vicariusBaseUrl);
	}

	public void clickOnStartFreeTrial() throws Throwable {
		ExtendedReporter.log("Clicking on start free trial button");
		startFreeTrialButton.click();
		Thread.sleep(1000);
	}

	public void hoverOnProduct() throws Throwable {
		ExtendedReporter.log("Hovering on product");
		hoverOverElement(productButton);
		Asserter.assertTrue(isDisplayed(productOverviewButton), "Expected product overview to be displayed, but found that it's not", "Validating that product overview is displayed");
	}

	public void clickOnProductOverview() throws Throwable {
		ExtendedReporter.log("Clicking on product overview");
		productOverviewButton.click();
		Thread.sleep(1000);
	}

	public void hoverAndClickOnProductOverview() throws Throwable {
		hoverOnProduct();
		clickOnProductOverview();
	}

}
