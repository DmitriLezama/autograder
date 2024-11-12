package com.gophers.performance;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.gophers.interfaces.MarkSchemeLoaderStrategy;
import com.gophers.services.helpers.MarkSchemeLoader;
import com.gophers.utilities.*;

public class MarkSchemeLoaderPerformanceTest {
    @Test
    public void testLoadWeightingsPerformance() {
        MarkSchemeLoaderStrategy markScheme = new MarkSchemeLoader();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    markScheme.loadWeightings();
                },
                TestConstants.CRITICAL_PERFORMANCE_THRESHOLD_MS,
                "MarkSchemeLoader - Get Weightings");
        assertTrue("Loading weightings took too long: " + result.getExecutionTime() + " ms", result.isSuccess());
    }
}
