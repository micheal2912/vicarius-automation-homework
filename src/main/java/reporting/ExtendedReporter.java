package reporting;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import static base.SeleniumBaseTest.extentTestNode;

/**
 * Assert extension class in order to report the steps each time we call an assert method
 */

public class ExtendedReporter extends Assert {

    static public void log(String step) throws Throwable {
        extentTestNode.get().log(Status.INFO, step); //Log the test method step inside the extent report
        System.out.println("INFO: " + step);
    }

}
