package com.gophers.performance;

import com.gophers.interfaces.Grade;
import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.structures.grades.ChatBotGrade;
import com.gophers.utilities.Constants;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class GradePerformanceTest {
    private final long performanceThreshold = 1000;
    private Grade grade;

    @Before
    public void setUp() {
        List<Result> results = new AssignmentTestRunner().runAllTests();
        grade = new ChatBotGrade(results.get(Constants.CHATBOT_GRADE_INDEX));
    }

    @Test
    public void getMarksPerformance() {
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> grade.getMarks(),
                performanceThreshold,
                "Grade - getMarks"
        );
        assertTrue("Test execution took too long", result.isSuccess());
    }

    @Test
    public void getTotalMarksPerformance() {
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> grade.getTotalMarks(),
                performanceThreshold,
                "Grade - getTotalMarks"
        );
        assertTrue("Test execution took too long", result.isSuccess());
    }

    @Test
    public void getFailedFeedbackPerformance() {
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> grade.getFailedFeedback(),
                performanceThreshold,
                "Grade - getFailedFeedback"
        );
        assertTrue("Test execution took too long", result.isSuccess());
    }
}