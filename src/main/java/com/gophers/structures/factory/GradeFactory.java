package com.gophers.structures.factory;

import org.junit.runner.Result;
import com.gophers.interfaces.Grade;
import com.gophers.structures.grades.ChatBotGeneratorGrade;
import com.gophers.structures.grades.ChatBotGrade;
import com.gophers.structures.grades.ChatBotPlatformGrade;
import com.gophers.structures.grades.ChatBotSimulationGrade;
import com.gophers.structures.grades.ProgramGrade;
import com.gophers.utilities.Constants;

public class GradeFactory extends AbstractGradeFactory {
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
