package com.gophers.performance;

import com.gophers.interfaces.TestRunner;
import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;
import com.gophers.utilities.TestConstants;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestRunnerPerformanceTest {
    @Test
    public void testRunAllTestsPerformance() {
        TestRunner<?> testRunner = new AssignmentTestRunner();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    testRunner.runAllTests();
                },
                TestConstants.HIGH_PRIORITY_THRESHOLD_MS,
                "TestRunner - runAllTests");
        assertNotNull("Results should not be null", result.getResult());
        assertTrue("Test execution took too long", result.isSuccess());
    }
}
