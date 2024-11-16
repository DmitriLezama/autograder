package com.gophers.structures.factory;

import org.junit.runner.Result;
import com.gophers.interfaces.Grade;
import com.gophers.structures.grades.ChatBotGeneratorGrade;
import com.gophers.structures.grades.ChatBotGrade;
import com.gophers.structures.grades.ChatBotPlatformGrade;
import com.gophers.structures.grades.ChatBotSimulationGrade;
import com.gophers.structures.grades.ProgramGrade;
import com.gophers.utilities.Constants;

/**
 * A factory class to create specific types of {@link Grade} objects based on
 * the given index.
 */
public class GradeFactory extends AbstractGradeFactory {

    /**
     * Creates a specific {@link Grade} object based on the given index.
     *
     * @param result The JUnit {@link Result} object containing the test result
     *               details.
     * @param index  The index to determine which grade object to create.
     * @return A new instance of a {@link Grade} subclass.
     * @throws IllegalArgumentException if the index does not match any defined
     *                                  grade type.
     */
    @Override
    public Grade createItem(Result result, int index) {
        switch (index) {
            case Constants.PROGRAM_GRADE_INDEX:
                return new ProgramGrade(result);
            case Constants.CHATBOT_GENERATOR_GRADE_INDEX:
                return new ChatBotGeneratorGrade(result);
            case Constants.CHATBOT_GRADE_INDEX:
                return new ChatBotGrade(result);
            case Constants.CHATBOT_PLATFORM_GRADE_INDEX:
                return new ChatBotPlatformGrade(result);
            case Constants.CHATBOT_SIMULATION_GRADE_INDEX:
                return new ChatBotSimulationGrade(result);
            default:
                throw new IllegalArgumentException("Unexpected index: " + index);
        }
    }
}
