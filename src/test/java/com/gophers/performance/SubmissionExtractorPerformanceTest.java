package com.gophers.performance;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.gophers.services.handlers.SubmissionExtractor;

public class SubmissionExtractorPerformanceTest {
    
    private final long performanceThreshold = 2000;

    @Test
    public void testSubmissionExtractorPerformance() {
        long startTime = System.nanoTime();
        List<String> submissions = SubmissionExtractor.extractSubmissions("submissions.zip");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Execution Time for Submission Extractor: " + duration + " ms");
        assertNotNull(submissions);
        assertTrue("Test execution took too long: " + duration + " ms", duration < performanceThreshold);
    }

}
