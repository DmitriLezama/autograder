package com.gophers.unit;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import com.gophers.services.helpers.MarkSchemeLoader;

public class MarkSchemeLoaderUnitTest {
    private Map<String, Map<String, Integer>> weightings;

    public MarkSchemeLoaderUnitTest() {
        MarkSchemeLoader configLoader = new MarkSchemeLoader();
        this.weightings = configLoader.loadWeightings();
    }

    @Test
    public void testLoadWeightings() {
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
}