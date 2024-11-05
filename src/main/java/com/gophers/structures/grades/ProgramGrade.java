package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.domain.TestFeedback;

public class ProgramGrade extends GradeTemplate {
    private static final int totalMarks = 15;

    public ProgramGrade(Result result) {
        super(result, totalMarks);
    }

    @Override
    protected void allocateWeightings() {
        super.testMarks.put("testCompiles", 5);
        super.testMarks.put("testRuns", 10);
    }

    @Override
    protected void allocateFeedback() {
        super.testFeedback.put("testCompiles", new TestFeedback("The program does not compile", 99));
        super.testFeedback.put("testRuns", new TestFeedback("The program does not run", 100));
    }
}
