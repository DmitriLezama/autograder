package com.gophers.unit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import com.gophers.interfaces.Grade;
import com.gophers.structures.domain.TestFeedback;

public class GradeTest {

    public class TestGradeImplementation implements Grade {
        private int marks = 36;
        private int totalMarks = 36;
        private List<TestFeedback> failedFeedback = new ArrayList<>();

        public TestGradeImplementation() {
            failedFeedback.add(new TestFeedback("Test case 1 failed", 1));
            failedFeedback.add(new TestFeedback("Test case 2 failed", 2));
        }

        @Override
        public int getMarks() {
            return marks;
        }

        @Override
        public int getTotalMarks() {
            return totalMarks;
        }

        @Override
        public List<TestFeedback> getFailedFeedback() {
            return failedFeedback;
        }
    }

    private Grade createGradeInstance() {
        return new TestGradeImplementation();
    }

    @Test
    public void testGetMarks() {
        Grade grade = createGradeInstance();
        int marks = grade.getMarks();
        assertNotNull("Marks should not be null", marks);
        assertEquals(36, marks);
    }

    @Test
    public void testGetTotalMarks() {
        Grade grade = createGradeInstance();
        int totalMarks = grade.getTotalMarks();
        assertNotNull("Total marks should not be null", totalMarks);
        assertEquals(36, totalMarks);
    }

    @Test
    public void testGetFailedFeedback() {
        Grade grade = createGradeInstance();
        List<TestFeedback> feedbackList = grade.getFailedFeedback();
        assertNotNull("Feedback list should not be null", feedbackList);
        assertEquals(2, feedbackList.size());
    }
}















