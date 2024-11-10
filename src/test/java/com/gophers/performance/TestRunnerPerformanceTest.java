package com.gophers.performance;

import com.gophers.interfaces.TestRunner;
import com.gophers.services.handlers.AssignmentTestRunner;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.Result;

import java.util.List;

public class TestRunnerPerformanceTest {
    @Test
    public void testRunAllTestsPerformance() {
        TestRunner testRunner = new AssignmentTestRunner();
        long startTime = System.nanoTime();
        List<Result> results = testRunner.runAllTests();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Execution time for runAllTests: " + duration + " ms");
        Assert.assertNotNull("Results should not be null", results);
        long performanceThreshold = 1000; // 1 seconds performance threshold
        Assert.assertTrue("Test execution took too long: " + duration + " ms", duration < performanceThreshold);
    }
}
