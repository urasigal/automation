package com.zixi.testing;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.TestDriver;
import com.zixi.tools.TestlinkIntegration;

public class BaseTest {
	protected TestDriver testDriver;
	String testid;
	@AfterMethod
    public void afterTest(Method test, ITestResult result)
            throws MalformedURLException {
        TestlinkIntegration tl = new TestlinkIntegration();
        if (result.isSuccess()) {
 
            tl.setResult(testid,
                    ExecutionStatus.PASSED);
        } else {
            tl.setResult(testid,
                    ExecutionStatus.FAILED);
        }
    }
}
