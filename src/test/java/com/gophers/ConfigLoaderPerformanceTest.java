package com.gophers;

import com.gophers.interfaces.ConfigLoaderStrategy;
import com.gophers.services.helpers.ConfigLoader;
import com.gophers.structures.domain.TestFeedback;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ConfigLoaderPerformanceTest {

    @Test
    public void testLoadWeightingsPerformance() {
        ConfigLoaderStrategy configLoader = new ConfigLoader();
        long startTime = System.nanoTime();
        Map<String, Map<String, Integer>> weightings = configLoader.loadWeightings();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        Assert.assertNotNull("Weightings data should not be null", weightings);
        long performanceThreshold = 500;
        Assert.assertTrue("Loading weightings took too long: " + duration + " ms", duration < performanceThreshold);
        System.out.println("Weightings load time: " + duration + " ms");
    }

    @Test
    public void testLoadFeedbackPerformance() {
        ConfigLoaderStrategy configLoader = new ConfigLoader();
        long startTime = System.nanoTime();
        Map<String, Map<String, TestFeedback>> feedback = configLoader.loadFeedback();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        Assert.assertNotNull("Feedback data should not be null", feedback);
        long performanceThreshold = 500;
        Assert.assertTrue("Loading feedback took too long: " + duration + " ms", duration < performanceThreshold);
        System.out.println("Feedback load time: " + duration + " ms");
    }
}
