package com.gophers.structures.grades;

import java.util.HashMap;
import org.junit.runner.Result;
import com.gophers.services.handlers.GradeConfigLoader;
import com.gophers.utilities.Constants;

/**
 * A concrete implementation of {@link GradeTemplate} for grading the chatbot platform.
 * This class defines how weightings and feedback are allocated for the chatbot platform grade.
 */
public class ChatBotPlatformGrade extends GradeTemplate {

    /**
     * The total marks available for the chatbot platform grade, as defined in {@link Constants}.
     */
    private static final int totalMarks = Constants.CHATBOT_PLATFORM_GRADE_TOTAL_MARKS;

    /**
     * Constructs a {@link ChatBotPlatformGrade} instance using the result of a test.
     * The constructor calls the parent class constructor and initializes the total marks.
     *
     * @param result The result of the test run for the chatbot platform.
     */
    public ChatBotPlatformGrade(Result result) {
        super(result, totalMarks);
    }

    /**
     * Allocates weightings for each test in the chatbot platform grade. This method loads the
     * weightings from the {@link GradeConfigLoader} based on the configuration defined for
     * chatbot platform grading.
     */
    @Override
    protected void allocateWeightings() {
        super.testMarks = GradeConfigLoader.getWeightings(Constants.CHATBOT_PLATFORM_GRADE);
    }

    /**
     * Allocates feedback for the chatbot platform test. This method initializes the feedback map
     * by copying the weightings from {@link testMarks} into the {@link feedbackMap}, as a baseline 
     * for feedback distribution.
     */
    protected void allocateFeedback() {
        super.feedbackMap = new HashMap<String, Integer>(super.testMarks);
    }
}
