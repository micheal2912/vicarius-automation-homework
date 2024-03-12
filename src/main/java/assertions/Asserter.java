package assertions;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import reporting.ExtendedReporter;

import static base.SeleniumBaseTest.*;

/**
 * Assert extension class in order to report the steps each time we call an assert method
 */

public class Asserter extends Assert {

    public final static String SUCCESS = "[SUCCESS] "; //Success string in order to log the steps
    public final static String FAILED = "[FAILED] "; //Failure string in order to log the steps

    static public void assertEquals(float actual, float expected, float delta, String message, String step) throws Throwable {
        try {
            ExtendedReporter.log(step); //Log the test method step inside the extent report
            Assert.assertEquals(actual, expected, delta, message); //Assert equals check if actual = expected
            ExtendedReporter.log(SUCCESS + step); //Log success step inside the extent report
        } catch (Throwable e) {
            extentTestNode.get().log(Status.FAIL, FAILED + step + "\n" + message); //Log failure step inside the extent report
            System.out.println(FAILED + step + "\n" + message);
            throw e;
        }
    }

    /**
     * All the other methods have the same explanation
     **/
    static public void assertEquals(double actual, double expected, double delta, String message, String step) throws Throwable {
        try {
            ExtendedReporter.log(step); //Log the test method step inside the extent report
            Assert.assertEquals(actual, expected, delta, message); //Assert equals check if actual = expected
            ExtendedReporter.log(SUCCESS + step); //Log success step inside the extent report
        } catch (Throwable e) {
            extentTestNode.get().log(Status.FAIL, FAILED + step + "\n" + message); //Log failure step inside the extent report
            System.out.println(FAILED + step + "\n" + message);
            throw e;
        }
    }

    static public void assertEquals(String actual, String expected, String message, String step) throws Throwable {
        try {
            ExtendedReporter.log(step); //Log the test method step inside the extent report
            Assert.assertEquals(actual, expected, message); //Assert equals check if actual = expected
            ExtendedReporter.log(SUCCESS + step); //Log success step inside the extent report
        } catch (Throwable e) {
            extentTestNode.get().log(Status.FAIL, FAILED + step + "\n" + message); //Log failure step inside the extent report
            System.out.println(FAILED + step + "\n" + message);
            throw e;
        }
    }

    static public void assertEquals(Object actual, Object expected, String message, String step) throws Throwable {
        try {
            ExtendedReporter.log(step); //Log the test method step inside the extent report
            Assert.assertEquals(actual, expected, message); //Assert equals check if actual = expected
            ExtendedReporter.log(SUCCESS + step); //Log success step inside the extent report
        } catch (Throwable e) {
            extentTestNode.get().log(Status.FAIL, FAILED + step + "\n" + message); //Log failure step inside the extent report
            System.out.println(FAILED + step + "\n" + message);
            throw e;
        }
    }

    static public void assertNotEquals(Object actual, Object expected, String message, String step) throws Throwable {
        try {
            ExtendedReporter.log(step); //Log the test method step inside the extent report
            Assert.assertNotEquals(actual, expected, message); //Assert equals check if actual = expected
            ExtendedReporter.log(SUCCESS + step); //Log success step inside the extent report
        } catch (Throwable e) {
            extentTestNode.get().log(Status.FAIL, FAILED + step + "\n" + message); //Log failure step inside the extent report
            System.out.println(FAILED + step + "\n" + message);
            throw e;
        }
    }

    static public void assertTrue(boolean condition, String message, String step) throws Throwable {
        try {
            ExtendedReporter.log(step);
            Assert.assertTrue(condition, message);
            ExtendedReporter.log(SUCCESS + step);
        } catch (Throwable e) {
            extentTestNode.get().log(Status.FAIL, FAILED + step + "\n" + message);
            System.out.println(FAILED + step + "\n" + message);
            throw e;
        }
    }

    static public void assertFalse(boolean condition, String message, String step) throws Throwable {
        try {
            ExtendedReporter.log(step);
            Assert.assertFalse(condition, message);
            ExtendedReporter.log(SUCCESS + step);
        } catch (Throwable e) {
            extentTestNode.get().log(Status.FAIL, FAILED + step + "\n" + message);
            System.out.println(FAILED + step + "\n" + message);
            throw e;
        }
    }

}
