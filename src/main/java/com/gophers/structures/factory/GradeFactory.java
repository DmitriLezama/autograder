package com.gophers.structures.factory;

import org.junit.runner.Result;
import com.gophers.interfaces.Grade;
import com.gophers.structures.grades.ChatBotGeneratorGrade;
import com.gophers.structures.grades.ChatBotGrade;
import com.gophers.structures.grades.ChatBotPlatformGrade;
import com.gophers.structures.grades.ChatBotSimulationGrade;

public class GradeFactory extends AbstractGradeFactory {
    @Override
    public Grade createGrade(Result result, int index) {
        switch (index) {
            case 0:
                return new ChatBotGeneratorGrade(result);
            case 1:
                return new ChatBotGrade(result);
            case 2:
                return new ChatBotPlatformGrade(result);
            case 3:
                return new ChatBotSimulationGrade(result);
            default:
                throw new IllegalArgumentException("Unexpected index: " + index);
        }
    }

}
