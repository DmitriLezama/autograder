package com.gophers.performance;

import com.gophers.interfaces.Grade;
import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.structures.grades.ChatBotGrade;
import com.gophers.utilities.Constants;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;
import com.gophers.utilities.TestConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class GradePerformanceTest {
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
                TestConstants.CRITICAL_PERFORMANCE_THRESHOLD_MS,
                "Grade - getMarks"
        );
        assertTrue("Test execution took too long", result.isSuccess());
    }

    @Test
    public void getTotalMarksPerformance() {
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> grade.getTotalMarks(),
                TestConstants.CRITICAL_PERFORMANCE_THRESHOLD_MS,
                "Grade - getTotalMarks"
        );
        assertTrue("Test execution took too long", result.isSuccess());
    }

    @Test
    public void getFailedFeedbackPerformance() {
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> grade.getFailedFeedback(),
                TestConstants.CRITICAL_PERFORMANCE_THRESHOLD_MS,
                "Grade - getFailedFeedback"
        );
        assertTrue("Test execution took too long", result.isSuccess());
    }
}