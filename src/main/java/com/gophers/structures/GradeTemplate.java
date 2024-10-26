package com.gophers.structures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.gophers.interfaces.Grade;

public abstract class GradeTemplate implements Grade {
    protected Map<String, Integer> testMarks;
    private int totalMarks;
    private int marksEarned;

    public GradeTemplate(Result result, int totalMarks) {
        testMarks = new HashMap<String, Integer>();
        this.totalMarks = totalMarks;
        this.allocateWeightings();
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
        }
    }

    public int getMarks() {
        return this.marksEarned;
    }

    public int getTotalMarks() {
        return this.totalMarks;
    }

    protected abstract void allocateWeightings();
}