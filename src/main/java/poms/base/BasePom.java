package poms.base;

import assertions.Asserter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.ExtendedReporter;

import static base.SeleniumBaseTest.driversPool;

public class BasePom {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(css = ".type-validation")
    protected WebElement validationErrorNotificationPopup;

    @FindBy(css = "button.notification-close")
    protected WebElement closeNotificationPopupButton;

    public BasePom() {
        driver = driversPool.get();
        PageFactory.initElements(driver, this);

    }

    public boolean isDisplayed(WebElement elm) {
        try {
            return elm.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForElementToBeClickable(By locator, int timeout) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementToBeVisible(By locator, int timeout) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        scrollToElement(element);
    }

    protected void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        hoverOverElement(element);
    }

    protected void waitForElementToBeClickable(WebElement element, int timeout) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeVisible(WebElement element, int timeout) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void navigateToUrl(String url) throws Throwable {
        driver.get(url);
        Asserter.assertTrue(waitForUrlToBe(url, 5), "Expected url to be " + url + ", but found that it's " + driver.getCurrentUrl(), "Validating that url is " + url);
    }

    // Wait for URL to be a specific value
    public boolean waitForUrlToBe(String expectedUrl, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        // Use a lambda expression to define the ExpectedCondition
        try {
            return wait.until((ExpectedCondition<Boolean>) d -> d.getCurrentUrl().contains(expectedUrl));
        }
        catch (TimeoutException e) {
            return false;
        }
    }

    public void scrollBy(int xOffset, int yOffset) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(" + xOffset + "," + yOffset + ")");
    }

    public void navigateBack() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(2000);
    }

    /**
     * Returns the parent element of the given WebElement.
     * @param childElement The WebElement whose parent you want to find.
     * @return The parent WebElement.
     */
    protected WebElement getElementParent(WebElement childElement) {
        return childElement.findElement(By.xpath("./.."));
    }

    protected WebElement getElementGrandfather(WebElement childElement) {
        // Using XPath to navigate up two levels in the DOM tree to get the grandparent element
        return childElement.findElement(By.xpath("./../.."));
    }

    public boolean isValidationNotificationPopupDisplayed() throws Throwable {
        return isDisplayed(validationErrorNotificationPopup);
    }

    public String getValidationNotificationPopupText() throws Throwable {
        return validationErrorNotificationPopup.getText();
    }

    public void clickOnCloseNotificationPopupButton() throws Throwable {
        ExtendedReporter.log("Clicking on close notification button");
        closeNotificationPopupButton.click();
        Thread.sleep(1000);
    }

}
