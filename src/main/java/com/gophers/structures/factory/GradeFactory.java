package com.gophers.structures.factory;

import org.junit.runner.Result;
import com.gophers.interfaces.Grade;
import com.gophers.structures.grades.ChatBotGeneratorGrade;
import com.gophers.structures.grades.ChatBotGrade;
import com.gophers.structures.grades.ChatBotPlatformGrade;
import com.gophers.structures.grades.ChatBotSimulationGrade;
import com.gophers.structures.grades.ProgramGrade;

public class GradeFactory extends AbstractGradeFactory {
    @Override
    public Grade createGrade(Result result, int index) {
        switch (index) {
            case 0:
                return new ProgramGrade(result);
            case 1:
                return new ChatBotGeneratorGrade(result);
            case 2:
                return new ChatBotGrade(result);
            case 3:
                return new ChatBotPlatformGrade(result);
            case 4:
                return new ChatBotSimulationGrade(result);
            default:
                throw new IllegalArgumentException("Unexpected index: " + index);
        }
    }

}
