package com.gophers.interfaces;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Map;
import com.gophers.services.helpers.ConfigLoader;
import com.gophers.structures.domain.TestFeedback;

public class ConfigLoaderStrategyTest {
    private ConfigLoaderStrategy loader;
    
    @Before
    public void setUp() {
        loader = new ConfigLoader();
    }

    @Test
    public void testConfigLoader_ConsistentKeySet() {
        Map<String, Map<String, Integer>> weightings = loader.loadWeightings();
        Map<String, Map<String, TestFeedback>> feedback = loader.loadFeedback();
        
        assertEquals("Categories in weightings and feedback must match",
            weightings.keySet(), feedback.keySet());
    }


    @Test
    public void testLoadWeightings_RequiredWeightingFields() {
        Map<String, Map<String, Integer>> weightings = loader.loadWeightings();
        
        for (Map<String, Integer> category : weightings.values()) {
            for (Integer weight : category.values()) {
                assertTrue("weights must be more than 0", weight > 0);
                assertNotNull("Weight should not be null", weight);
                assertTrue("Weight should be greater than 0", weight > 0);
            }
        }
    }

    @Test
    public void testLoadFeedback_RequiredFeedbackFields() {
        Map<String, Map<String, TestFeedback>> feedback = loader.loadFeedback();
        
        for (Map<String, TestFeedback> category : feedback.values()) {
            for (TestFeedback testFeedback : category.values()) {
                assertNotNull("Feedback message should not be null", testFeedback.getFeedback());
                assertFalse("Feedback message should not be empty", testFeedback.getFeedback().isEmpty());
                assertTrue("Priority should be between 0 and 100", 
                    testFeedback.getPriority() >= 0 && testFeedback.getPriority() <= 100);
            }
        }
    }


}