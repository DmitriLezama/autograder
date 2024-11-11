package com.gophers.performance;

import com.gophers.interfaces.ConfigLoaderStrategy;
import com.gophers.services.helpers.ConfigLoader;
import org.junit.Assert;
import org.junit.Test;

import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;

public class ConfigLoaderPerformanceTest {
    
    long performanceThreshold = 1000;

    @Test
    public void testLoadWeightingsPerformance() {
        ConfigLoaderStrategy configLoader = new ConfigLoader();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    configLoader.loadWeightings();
                },
                performanceThreshold,
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
                performanceThreshold,
                "ConfigLoader - Get Feedback");
        Assert.assertTrue("Loading feedback took too long: " + result.getExecutionTime() + " ms", result.isSuccess());
    }
}
