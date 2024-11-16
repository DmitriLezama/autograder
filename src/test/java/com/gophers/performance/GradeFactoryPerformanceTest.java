package com.gophers.performance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.Result;
import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.structures.factory.GradeFactory;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;
import com.gophers.utilities.TestConstants;

public class GradeFactoryPerformanceTest {
    @Test
    public void createGradePerformace() {
        GradeFactory factory = new GradeFactory();
        List<Result> results = new AssignmentTestRunner().runAllTests();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    factory.createItems(results);
                },
                TestConstants.CRITICAL_PERFORMANCE_THRESHOLD_MS,
                "GradeFactory - createGrades");
        assertNotNull("Results should not be null", result.getResult());
        assertTrue("Test execution took too long", result.isSuccess());
    }
}