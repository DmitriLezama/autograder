package com.gophers.performance;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.gophers.interfaces.Facade;
import com.gophers.services.handlers.GradingFacade;

public class FacadePerformanceTest {

    private final long performanceThreshold = 15000;

    @Test
    public void testFacadePerformance() throws Exception {
        Facade facade = new GradingFacade();
        PerformanceTestResult result = PerformanceChecker.testExecutionTime(
            () -> {
                facade.processSubmissions("submissions.zip");
                return null;
            },
            performanceThreshold,
            "Facade - Process Submissions"
        );
        assertTrue("Execution of processSubmissions took too long", result.isSuccess());
    }
}
