package utils;

import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

public class WebDriverUtils {

    public static void focusOnRequestedTab(WebDriver driver, int tabIndex) throws InterruptedException {
        ArrayList<String> allCurrentTabs = new ArrayList<String>(driver.getWindowHandles());
        if (allCurrentTabs.size() == 1) {
//            extentTestNode.get().log(Status.ERROR, "Could not focus on a new tab");
            return;
        }
        driver.switchTo().window(allCurrentTabs.get(tabIndex));
        Thread.sleep(1500);
        return;
    }

    public static void closeRequestedTab(WebDriver driver, int tabIndex) throws InterruptedException {
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

}
