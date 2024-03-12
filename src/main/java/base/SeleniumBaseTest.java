package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import listeners.PriorityInterceptor;
import listeners.TestNGListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import reporting.ExtentReporterNG;
import utils.WebDriverUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({TestNGListener.class, PriorityInterceptor.class})
public class SeleniumBaseTest {
	public static final String USER_DIR = System.getProperty("user.dir"); //Project path
	public static final String OUTPUT_PATH = File.separator + "target" + File.separator + "surefire-reports" + File.separator; //Reports folder path
	public static final String PROPERTIES_PATH = USER_DIR + File.separator + "src" + File.separator + "main" +
			File.separator + "java" + File.separator + "resources" + File.separator + "data.properties"; //Properties file path
	public static final String ADBLOCK_CRX_PATH = USER_DIR + File.separator + "src" + File.separator + "main" +
			File.separator + "java" + File.separator + "resources" + File.separator + "AdBlock-—-best-ad-blocker.crx"; //Properties file path

	public WebDriver driver;
	public static ThreadLocal<WebDriver> driversPool = new ThreadLocal<>();

	public ExtentTest test; //Reporter variable for each test class
	public ExtentTest testNode; //Reporter variable for each test method
	public static ExtentReports extent = ExtentReporterNG.getReportObject(); //Report object
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread of the current test class
	public static ThreadLocal<ExtentTest> extentTestNode = new ThreadLocal<ExtentTest>(); //Thread of the current test method

	private Properties props; //Properties variable in order to load the data from a properties file
	protected static String browserName;
	public static String vicariusBaseUrl = "https://www.vicarius.io";
	public static String vicariusSigninUrl = vicariusBaseUrl + File.separator + "sign" + File.separator + "in";
	public static String vicariusSignupUrl = vicariusBaseUrl + File.separator + "sign" + File.separator + "up";
	public static String vicariusProductUrl = vicariusBaseUrl + File.separator + "product";

	@BeforeSuite(alwaysRun = true)
	protected void beforeSuiteInits() throws Throwable {
		loadPropertiesFile();
	}

	@AfterSuite(alwaysRun = true)
	protected void afterSuiteActions() throws Exception {
		extent.flush(); //Publish reporter object
	}

	@BeforeTest
	protected void setCurrentTestContext(ITestContext context) {
		test = extent.createTest(context.getName()); //Create report before each test class
		extentTest.set(test);
	}

	@BeforeClass
	protected void beforeClassInits(ITestContext context) throws Throwable {
		initializeDriver(); //Create an IOS driver for each test class
	}

	@AfterClass
	protected void afterClassActions() {
		closeDriver(); //Close the IOS driver after each test class
	}

	@BeforeMethod(alwaysRun = true)
	protected void beforeMethodsInits(Method method) {
		testNode = test.createNode(method.getName()); //Create report for each test method
		extentTestNode.set(testNode);
	}

	public void loadPropertiesFile() throws IOException {
		props = new Properties();
		FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
		props.load(fis);
		browserName = props.getProperty("browser");
	}

	public WebDriver initializeDriver() throws Throwable {
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true); // Bypass SSL errors
			// Check if the 'headless' system property is set to true
			if (Boolean.parseBoolean(System.getProperty("headless"))) {
				options.addArguments("--headless");
				options.addArguments("window-size=1920x1080"); // Adjust as needed
			}
			if (Boolean.parseBoolean(System.getProperty("remote"))) {
//				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
				driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
			}
			else {
				driver = new ChromeDriver(options);
			}
			driver.manage().window().maximize();
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driversPool.set(driver);
		driver.get(vicariusBaseUrl);
		return driver;
	}

	protected void closeDriver() {
		driver.quit();
	}


	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = USER_DIR + OUTPUT_PATH + "html" + File.separator + "screenshots" + File.separator + testCaseName + ".png"; //Screenshots path
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

}
