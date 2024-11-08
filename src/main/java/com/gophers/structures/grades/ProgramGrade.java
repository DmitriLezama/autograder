package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.services.helpers.GradeConfigLoader;
import com.gophers.utilities.Constants;

public class ProgramGrade extends GradeTemplate {
    private static final int totalMarks = 15;

    public ProgramGrade(Result result) {
        super(result, totalMarks);
    }

    @Override
    protected void allocateWeightings() {
        super.testMarks = GradeConfigLoader.getWeightings(Constants.PROGRAM_GRADE);
    }

    @Override
    protected void allocateFeedback() {
        super.testFeedback = GradeConfigLoader.getFeedback(Constants.PROGRAM_GRADE);
    }

}
