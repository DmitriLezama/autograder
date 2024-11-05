package com.gophers.structures.grades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.gophers.interfaces.Grade;
import com.gophers.structures.TestFeedback;

public abstract class GradeTemplate implements Grade {
    protected Map<String, Integer> testMarks;
    protected Map<String, TestFeedback> testFeedback;
    protected Map<String, TestFeedback> failedFeedbackMap;
    private int totalMarks;
    private int marksEarned;

    public GradeTemplate(Result result, int totalMarks) {
        this.testMarks = new HashMap<String, Integer>();
        this.testFeedback = new HashMap<String, TestFeedback>();
        this.failedFeedbackMap = new HashMap<String, TestFeedback>();
        this.totalMarks = totalMarks;
        this.allocateWeightings();
        this.allocateFeedback();
        this.setMarksEarned(result);
    }

    private void setMarksEarned(Result result) {
        this.marksEarned = this.totalMarks;
        if (!result.wasSuccessful())
            adjustMarksForFailures(result.getFailures());
    }

    private void adjustMarksForFailures(List<Failure> failures) {
        for (Failure failure : failures) {
            String methodName = failure.getDescription().getMethodName();
            System.out.println("Failed: " + failure);
            this.marksEarned -= testMarks.getOrDefault(methodName, 0);
            TestFeedback feedback = testFeedback.get(methodName);
            if (feedback != null)
                failedFeedbackMap.put(methodName, feedback);
        }
    }

    public int getMarks() {
        return this.marksEarned;
    }

    public int getTotalMarks() {
        return this.totalMarks;
    }

    public List<TestFeedback> getFailedFeedback() {
        return failedFeedbackMap.values().stream().toList();
    }

    protected abstract void allocateWeightings();

    protected abstract void allocateFeedback();
}
