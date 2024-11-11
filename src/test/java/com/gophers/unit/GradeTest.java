package com.gophers.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.gophers.interfaces.Grade;
import com.gophers.structures.domain.TestFeedback;
import com.gophers.structures.grades.ChatBotGrade;

public class GradeTest {

   
    class TestChatBotGrade extends ChatBotGrade {
        public TestChatBotGrade(Result result) {
            super(result);
        }

        @Override
        protected void allocateWeightings() {
           
            super.testMarks = new HashMap<>();
            super.testMarks.put("testMethod1", 10);
            super.testMarks.put("testMethod2", 20);
        }

        @Override
        protected void allocateFeedback() {
         
            super.testFeedback = new HashMap<>();
            super.testFeedback.put("testMethod1", new TestFeedback("Feedback for testMethod1", 1));
            super.testFeedback.put("testMethod2", new TestFeedback("Feedback for testMethod2", 2));
        }
    }

    
    private Grade createGradeInstance(Result result) {
        return new TestChatBotGrade(result);
    }

    
    private Result createMockResultWithFailures(List<Failure> failures) {
        Result result = new Result() {
            @Override
            public boolean wasSuccessful() {
                return failures.isEmpty();
            }

            @Override
            public List<Failure> getFailures() {
                return failures;
            }
        };
        return result;
    }

    @Test
    public void testGetMarks() {
        List<Failure> failures = new ArrayList<>();
        Result mockResult = createMockResultWithFailures(failures);
        Grade grade = createGradeInstance(mockResult);
        assertEquals(36, grade.getMarks());
    }

    @Test
    public void testGetTotalMarks() {
        Result mockResult = createMockResultWithFailures(new ArrayList<>());
        Grade grade = createGradeInstance(mockResult);
        assertEquals(36, grade.getTotalMarks());
    }

    @Test
    public void testGetFailedFeedback() {
        List<Failure> failures = new ArrayList<>();
        
        Description description1 = Description.createTestDescription("TestClass", "testMethod1");
        Description description2 = Description.createTestDescription("TestClass", "testMethod2");
        
        Failure failure1 = new Failure(description1, new AssertionError("Test case 1 failed"));
        Failure failure2 = new Failure(description2, new AssertionError("Test case 2 failed"));
        failures.add(failure1);
        failures.add(failure2);

        Result mockResult = createMockResultWithFailures(failures);
        Grade grade = createGradeInstance(mockResult);
        List<TestFeedback> failedFeedback = grade.getFailedFeedback();

        assertNotNull(failedFeedback);
        assertEquals(2, failedFeedback.size());
    }
}









