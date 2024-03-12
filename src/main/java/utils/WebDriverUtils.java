package utils;

import assertions.Asserter;
import org.openqa.selenium.WebDriver;
import reporting.ExtendedReporter;

import java.util.ArrayList;

public class WebDriverUtils {

    public static void focusOnRequestedTab(WebDriver driver, int tabIndex) throws Throwable {
        ExtendedReporter.log("Switching to tab with index " + tabIndex);
        ArrayList<String> allCurrentTabs = new ArrayList<String>(driver.getWindowHandles());
        if (allCurrentTabs.size() == 1) {
//            extentTestNode.get().log(Status.ERROR, "Could not focus on a new tab");
            return;
        }
        driver.switchTo().window(allCurrentTabs.get(tabIndex));
        Thread.sleep(1500);
        return;
    }

    public static void closeRequestedTab(WebDriver driver, int tabIndex) throws Throwable {
        ExtendedReporter.log("Closing tab with index " + tabIndex);
        ArrayList<String> allCurrentTabs = new ArrayList<String>(driver.getWindowHandles());
        if (allCurrentTabs.size() == 0) {
//            extentTestNode.get().log(Status.ERROR, "There are no opened tabs");
            return;
        }
        String oldTab;
        oldTab = driver.getWindowHandle();
        focusOnRequestedTab(driver, tabIndex);
        driver.close();
        driver.switchTo().window(oldTab);
        Thread.sleep(1500);
        return;
    }

    public static void navigateToUrl(WebDriver driver, String url) throws Throwable {
        ExtendedReporter.log("Navigating to url " + url);
        driver.get(url);
        Thread.sleep(2000);
    }

    public static void navigateBack(WebDriver driver) throws Throwable {
        ExtendedReporter.log("Navigating back");
        driver.navigate().back();
        Thread.sleep(2000);
    }

}
