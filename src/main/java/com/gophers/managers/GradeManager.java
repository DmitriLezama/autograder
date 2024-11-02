package com.gophers.managers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Result;

import com.gophers.interfaces.Grade;
import com.gophers.structures.AssignmentGrade;
import com.gophers.structures.ChatBotGeneratorGrade;
import com.gophers.structures.ChatBotGrade;
import com.gophers.structures.ChatBotPlatformGrade;
import com.gophers.structures.ChatBotSimulationGrade;
import com.gophers.structures.ProgramGrade;
import com.gophers.utilities.AssignmentTestRunner;

public class GradeManager {

    public AssignmentGrade getAssignmentGrade(String submissionDirectory) {
        AssignmentTestRunner assignmentTestRunner = new AssignmentTestRunner();
        List<Result> results = assignmentTestRunner.runAllTest();
        List<Grade> grades = new ArrayList<>();
        Grade programGrade = new ProgramGrade(results.get(0));
        Grade chatBotGeneratorGrade = new ChatBotGeneratorGrade(results.get(1));
        Grade chatBotGrade = new ChatBotGrade(results.get(2));
        Grade chatBotPlatformGrade = new ChatBotPlatformGrade(results.get(3));
        Grade chatBotSimulationGrade = new ChatBotSimulationGrade(results.get(4));
        grades.add(programGrade);
        grades.add(chatBotGeneratorGrade);
        grades.add(chatBotGrade);
        grades.add(chatBotPlatformGrade);
        grades.add(chatBotSimulationGrade);
        AssignmentGrade assignmentGrade = new AssignmentGrade(grades);
        return assignmentGrade;
    }
}
