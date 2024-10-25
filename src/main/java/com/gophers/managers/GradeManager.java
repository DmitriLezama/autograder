package com.gophers.managers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Result;

import com.gophers.interfaces.Grade;
import com.gophers.structures.AssignmentGrade;
import com.gophers.structures.ChatBotGeneratorGrade;
import com.gophers.structures.ChatBotGrade;
import com.gophers.utilities.AssignmentTestRunner;

public class GradeManager {

    public AssignmentGrade getAssignmentGrade(String submissionDirectory) {
        AssignmentTestRunner assignmentTestRunner = new AssignmentTestRunner();
        List<Result> results = assignmentTestRunner.runAllTest();
        List<Grade> grades = new ArrayList<>();
        Grade chatBotGeneratorGrade = new ChatBotGeneratorGrade(results.get(0));
        Grade chatBotGrade = new ChatBotGrade(results.get(1));
        grades.add(chatBotGeneratorGrade);
        grades.add(chatBotGrade);
        AssignmentGrade assignmentGrade = new AssignmentGrade(grades);
        return assignmentGrade;
    }
}
