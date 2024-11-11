package com.gophers.performance;

import org.junit.Assert;
import org.junit.Test;
import com.gophers.interfaces.ConfigLoaderStrategy;
import com.gophers.services.helpers.ConfigLoader;
import com.gophers.utilities.*;

public class ConfigLoaderPerformanceTest {
    @Test
    public void testLoadWeightingsPerformance() {
        ConfigLoaderStrategy configLoader = new ConfigLoader();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    configLoader.loadWeightings();
                },
                TestConstants.CRITICAL_PERFORMANCE_THRESHOLD_MS,
                "ConfigLoader - Get Weightings");
        Assert.assertTrue("Loading weightings took too long: " + result.getExecutionTime() + " ms", result.isSuccess());
    }

    @Test
    public void testLoadFeedbackPerformance() {
        ConfigLoaderStrategy configLoader = new ConfigLoader();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    configLoader.loadFeedback();
                },
                TestConstants.CRITICAL_PERFORMANCE_THRESHOLD_MS,
                "ConfigLoader - Get Feedback");
        Assert.assertTrue("Loading feedback took too long: " + result.getExecutionTime() + " ms", result.isSuccess());
    }
}
