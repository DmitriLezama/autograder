package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.services.helpers.GradeConfigLoader;

public class ProgramGrade extends GradeTemplate {
    private static final int totalMarks = 15;

    public ProgramGrade(Result result) {
        super(result, totalMarks);
    }

    @Override
    protected void allocateWeightings() {
        super.testMarks = GradeConfigLoader.getWeightings("ProgramGrade");
    }

    @Override
    protected void allocateFeedback() {
        super.testFeedback = GradeConfigLoader.getFeedback("ProgramGrade");
    }

}
