package com.gophers.performance;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import com.gophers.services.handlers.SubmissionExtractor;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;
import com.gophers.utilities.TestConstants;

public class SubmissionExtractorPerformanceTest {
    @Test
    public void testSubmissionExtractorPerformance() {
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    SubmissionExtractor.extractSubmissions("submissions.zip");
                },
                TestConstants.HIGH_PRIORITY_THRESHOLD_MS,
                "SubmissionExtractor - extractSubmissions");
        assertTrue("Test execution took too long", result.isSuccess());
    }
}
