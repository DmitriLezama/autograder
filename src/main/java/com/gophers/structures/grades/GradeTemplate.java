package com.gophers.structures.grades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.gophers.interfaces.Grade;

public abstract class GradeTemplate implements Grade {
    protected Map<String, Integer> testMarks;
    protected Map<String, Boolean> feedbackMap;
    private int totalMarks;
    private int marksEarned;

    public GradeTemplate(Result result, int totalMarks) {
        this.feedbackMap = new HashMap<String, Boolean>();
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
            this.marksEarned -= testMarks.getOrDefault(methodName, 0);
            feedbackMap.put(methodName, false);
        }
    }

    public int getMarks() {
        return this.marksEarned;
    }

    public int getTotalMarks() {
        return this.totalMarks;
    }

    public Map<String, Boolean> getTestFeedback() {
        return feedbackMap;
    }

    protected abstract void allocateWeightings();

    protected abstract void allocateFeedback();
}
