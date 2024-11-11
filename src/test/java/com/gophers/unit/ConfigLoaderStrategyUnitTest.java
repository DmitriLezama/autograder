package com.gophers.unit;

import org.junit.Test;
import org.junit.Assert;

import java.util.Map;

import com.gophers.services.helpers.ConfigLoader;
import com.gophers.structures.domain.TestFeedback;

public class ConfigLoaderStrategyUnitTest{
    private Map<String, Map<String, Integer>> weightings;
    private Map<String, Map<String, TestFeedback>> feedback;

    public ConfigLoaderStrategyUnitTest(){
        ConfigLoader configLoader = new ConfigLoader();
        this.weightings = configLoader.loadWeightings();
        this.feedback = configLoader.loadFeedback();
    }

    @Test
    public void testLoadWeightings(){
        Assert.assertNotNull("Weightings map should not be null", weightings);

        for (Map.Entry<String, Map<String, Integer>> outerEntry : weightings.entrySet()) {
            Assert.assertNotNull("Outer map key should not be null", outerEntry.getKey());
            Map<String, Integer> innerMap = outerEntry.getValue();
            Assert.assertNotNull("Inner map should not be null for key: " + outerEntry.getKey(), innerMap);
            
            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
                Assert.assertNotNull("Inner map key should not be null", innerEntry.getKey());
                Assert.assertNotNull("Value should not be null for key: " + innerEntry.getKey(), innerEntry.getValue());
            }
        }
    }

    @Test
    public void testLoadFeedback(){
        Assert.assertNotNull("Feedback map should not be null", feedback);

        for (Map.Entry<String, Map<String, TestFeedback>> outerEntry : feedback.entrySet()) {
            Assert.assertNotNull("Outer map key should not be null", outerEntry.getKey());
            Map<String, TestFeedback> innerMap = outerEntry.getValue();
            Assert.assertNotNull("Inner map should not be null for key: " + outerEntry.getKey(), innerMap);
            
            for (Map.Entry<String, TestFeedback> innerEntry : innerMap.entrySet()) {
                Assert.assertNotNull("Inner map key should not be null", innerEntry.getKey());
                Assert.assertNotNull("TestFeedback should not be null for key: " + innerEntry.getKey(), innerEntry.getValue());
            }
        }
    }
}