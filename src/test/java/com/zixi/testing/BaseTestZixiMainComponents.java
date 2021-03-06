package com.zixi.testing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import com.zixi.drivers.drivers.*;
import com.zixi.drivers.setup.SetSutUpTimeDriver;
import com.zixi.drivers.tools.DriverReslut;
import com.zixi.email.drivers.GoogleMailDriver;
import com.zixi.tools.TestlinkIntegration;
import java.sql.*;

public class BaseTestZixiMainComponents {

	// Interface class. All test drivers have to implement this interface.
	protected TestDriver testDriver; // Reference to test driver interface. `
	protected DriverReslut driverReslut = new DriverReslut();
	protected GoogleMailDriver mail;
	// Check SUT up time.
	protected SetSutUpTimeDriver setSutUpTimeDriver;
	protected String crashTestResults = null;
	protected boolean crashFlag;
	protected String testid; // Stores unique test number which is the number provided by TestLink the test
								// management system.
	protected String version = ""; // Stores the SUT version (feeder broadcaster receiver).
	protected String automationTestIdentifiers = ""; // Stores an automation test name and automation suite name, then
														// it will be written to TestLinlk.
	protected ProductAboutDriver productAboutDriver = new ProductAboutDriver(); // Retrieves and stores SUT version
																				// information - Broadcaster build
																				// number.
	protected StringBuffer testFlowDescriptor = new StringBuffer("Test flow: "); // Put it any place in order to
																					// describe a test flow.
	protected String sutProcessId = ""; // Stores a SUT's process id or PID. This files is ONLY relevant to LINUX
										// servers.
	protected double testDuration;

	// Writes test results to the TestLink.
	protected String testLinkTestParameters = "";
	protected String manulDescription = "";
	protected String memOnStart = "";
	protected String memOnEnd = "";
	
	@BeforeTest
	public void startTest(final ITestContext testContext) {
		automationTestIdentifiers = "Test name is: " + testContext.getName() + "\nSuite name is: "
				+ testContext.getSuite().getName();
	}

	// @BeforeMethod: The annotated method will be run before each test method.
	@Parameters({ "testid" })
	@BeforeMethod
	public void beforeTes(String testid) throws TestLinkAPIException, IOException {
		setSutUpTimeDriver = new SetSutUpTimeDriver();
		testDuration = System.currentTimeMillis();
		this.testid = testid;
		System.out.println(this.getClass().getName());
	}

	// Write test results to the TestLink, assess if there was a crash.
	@AfterMethod
	public void afterTest(Method test, ITestResult result) {
		testDuration = System.currentTimeMillis() - testDuration;
		String crashStatus = "";

		TestlinkIntegration tl = new TestlinkIntegration();
		try {
			crashTestResults = setSutUpTimeDriver.continuousUpTimeCheck();
			if (crashTestResults == null)
				throw new NullPointerException("crashTestResults is null !!!");
			if (crashTestResults.equals("pass")) {
				crashFlag = false;
			} else {
				crashFlag = true;
			}

			if (crashFlag) {
				crashStatus = "There was a crash in the recent tests " + crashTestResults;
				tl.setResult(testid, ExecutionStatus.FAILED,
						this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n"
								+ automationTestIdentifiers + "\nTest Parameters: " + testLinkTestParameters
								+ " Manul description: " + manulDescription + testFlowDescriptor
								+ "\nTest duration[ms]: " + testDuration + "\n" + "Test notes "
								+ driverReslut.touchResutlDescription(" ") + "\n" + crashStatus,
						getBuildIdFromFile());

				String message = "Crash detected \n";
				message = message + tl.getTestInfo(Integer.parseInt(testid)).toString() + "\n" + "Test Parameters: "
						+ testLinkTestParameters;
				String subject = "Crash detected";
				GoogleMailDriver.sendToList(
						SetSutUpTimeDriver.getEmailAddressesFromSystemFolder("src/main/resources/email_addresses"),
						subject, message);
			} else {
				if (result.isSuccess()) {
					tl.setResult(testid, ExecutionStatus.PASSED,
							this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n"
									+ automationTestIdentifiers + "\nTest Parameters: " + testLinkTestParameters
									+ "\nManul description: " + manulDescription + testFlowDescriptor
									+ "\nTest duration[ms]: " + testDuration + "\n " + "Test notes "
									+ driverReslut.touchResutlDescription(" ") + "\n" + crashStatus,
							getBuildIdFromFile()); // pass data to a testLink notes in test execution.
				} else {
					tl.setResult(testid, ExecutionStatus.FAILED,
							this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n"
									+ automationTestIdentifiers + "\nTest Parameters: " + testLinkTestParameters
									+ " Manul description: " + manulDescription + testFlowDescriptor
									+ "\nTest duration[ms]: " + testDuration + "\n" + "\n Error is "
									+ result.getThrowable().getMessage() + "\n Exception stack trace: "
									+ result.getThrowable().getStackTrace() + "Test notes "
									+ driverReslut.touchResutlDescription(" ") + "\n" + crashStatus,
							getBuildIdFromFile());

					String message = "Test failed \n";
					message = message + tl.getTestInfo(Integer.parseInt(testid)).toString() + "\n" + "Test Parameters: "
							+ testLinkTestParameters;
					String subject = "Test failed \n";
					GoogleMailDriver.sendToList(
							SetSutUpTimeDriver.getEmailAddressesFromSystemFolder("src/main/resources/email_addresses"),
							subject, message);
				}
			}

			setSutUpTimeDriver.uptimeSet("src/main/resources/uptime", "src/main/resources/current_uptime");
		} catch (Exception e) {
			System.out.println("The error is " + e.getMessage());
		}
	}

	protected String buildTestParametersString(String parametersNmes[], String[] paramertersValues) {
		StringBuffer sb = new StringBuffer();
		int length = parametersNmes.length;
		for (int i = 0; i < length; i++) {
			sb.append("\n").append(parametersNmes[i]).append(" = ").append(paramertersValues[i]);
		}
		testLinkTestParameters = sb.toString();
		return testLinkTestParameters;
	}

	// Get TestLink test plan ID.
	protected int getBuildIdFromFile() throws IOException {
		String line;
		int buildId = -1;
		try (InputStream fis = new FileInputStream("src/main/resources/build");
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				buildId = Integer.parseInt(line);
			}
		}
		return buildId;
	}

	public void connecttoDb(String sutHost, int startMemory, int stopMemory, long timeStemp)
			throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException {
		if (sutHost == null || startMemory == 0 || stopMemory == 0 || timeStemp == 0) {
			throw new NullPointerException();
		}
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("src/main/resources/db_connection.json"));
		JSONObject jsonObject = (JSONObject) object;
		String connector = (String) jsonObject.get("connector");
		String host = (String) jsonObject.get("host");
		String port = (String) jsonObject.get("port");
		String db = (String) jsonObject.get("db");
		String user = (String) jsonObject.get("user");
		String password = (String) jsonObject.get("password");
		Class.forName(connector);
		Connection cononnectionDb = DriverManager.getConnection(host + ":" + port + "/" + db, user, password);
		Statement stmt = cononnectionDb.createStatement();
		String queryString = "INSERT INTO memory_host_usage (hostaddress, memorystart, memorystop, memorydiff, stoptimestemp, testid, testcontext) "
		+ "VALUES ('" + sutHost + "'," + startMemory + "," + stopMemory + ","
		+ (stopMemory - startMemory) + "," + timeStemp + "," + Integer.parseInt(testid) + ",\"" + automationTestIdentifiers + "\")";
		stmt.executeUpdate(queryString);
		cononnectionDb.close();
	}
}
