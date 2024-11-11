package com.gophers.performance;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import com.gophers.services.handlers.SubmissionExtractor;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;

public class SubmissionExtractorPerformanceTest {

    private final long performanceThreshold = 2000;

    @Test
    public void testSubmissionExtractorPerformance() {
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    SubmissionExtractor.extractSubmissions("submissions.zip");
                },
                performanceThreshold,
                "SubmissionExtractor - extractSubmissions");
        assertTrue("Test execution took too long", result.isSuccess());
    }
}
