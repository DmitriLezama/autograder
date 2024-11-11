package com.gophers.performance;

import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.structures.factory.GradeFactory;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;

import org.junit.runner.Result;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class GradeFactoryPerformanceTest {
    private final long performanceThreshold = 1000;

    @Test
    public void createGradePerformace() {
        GradeFactory factory = new GradeFactory();
        List<Result> results = new AssignmentTestRunner().runAllTests();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    factory.createGrades(results);
                },
                performanceThreshold,
                "GradeFactory - createGrades");
        assertNotNull("Results should not be null", result.getResult());
        assertTrue("Test execution took too long", result.isSuccess());
    }
}