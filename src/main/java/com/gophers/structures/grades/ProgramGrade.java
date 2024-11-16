package com.gophers.structures.grades;

import java.util.HashMap;
import org.junit.runner.Result;
import com.gophers.services.handlers.GradeConfigLoader;
import com.gophers.utilities.Constants;

/**
 * Represents a grading structure specific to programming assignments, extending the generic
 * {@code GradeTemplate} with configuration and feedback specific to the program component of a course.
 * This class initializes test weightings and feedback maps based on configurations and calculates
 * the final grade based on the test results provided.
 */
public class ProgramGrade extends GradeTemplate {

    /** Defines the total marks allocated for program grading as specified in {@code Constants}. */
    private static final int totalMarks = Constants.PROGRAM_GRADE_TOTAL_MARKS;

    /**
     * Constructs a new {@code ProgramGrade} with the specified test results.
     * Initializes weightings and feedback for program-specific grading based on configuration.
     *
     * @param result The {@code Result} object containing JUnit test outcomes used for grading.
     */
    public ProgramGrade(Result result) {
        super(result, totalMarks);
    }

    /**
     * Allocates test weightings for program grading by loading configuration values specific
     * to program grading criteria. This mapping is stored in {@code testMarks} and is used to
     * calculate marks deductions for failed tests.
     */
    @Override
    protected void allocateWeightings() {
        super.testMarks = GradeConfigLoader.getWeightings(Constants.PROGRAM_GRADE);
    }

    /**
     * Initializes the feedback map for program grading based on test weightings.
     * This map allows specific feedback entries to be generated for each test case,
     * reflecting the weightings and the status of each test in the grading criteria.
     */
    @Override
    protected void allocateFeedback() {
        super.feedbackMap = new HashMap<>(super.testMarks);
    }
}

