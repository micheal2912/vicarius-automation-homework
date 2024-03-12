package vicariustests;

import base.VicariusBaseTest;
import org.testng.annotations.*;

public class VicariusSanityTest extends VicariusBaseTest {

	// Signup sanity test covers negative and positive flows
	@Test(priority = 0)
	public void signupSanityTest() throws Throwable {
		signupTest();
	}

	// Signin sanity test covers negative and positive flows
	@Test(priority = 1)
	public void signinSanityTest() throws Throwable {
		signinTest();
	}

	//Product page sanity test is validating login and logout url, it's also hovering and scrolling over elements
	@Test(priority = 2)
	public void productPageSanityTest() throws Throwable {
		productPageTest();
	}

}
