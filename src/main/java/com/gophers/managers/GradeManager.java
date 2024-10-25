package com.gophers.managers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Result;

import com.gophers.interfaces.Grade;
import com.gophers.structures.AssignmentGrade;
import com.gophers.structures.ChatBotGeneratorGrade;
import com.gophers.utilities.AssignmentTestRunner;

public class GradeManager {

    public AssignmentGrade getAssignmentGrade(String submissionDirectory) {
        AssignmentTestRunner assignmentTestRunner = new AssignmentTestRunner();
        List<Result> results = assignmentTestRunner.runAllTest();
        List<Grade> grades = new ArrayList<>();
        Grade grade = new ChatBotGeneratorGrade(results.get(0));
        grades.add(grade);
        AssignmentGrade assignmentGrade = new AssignmentGrade(grades);
        return assignmentGrade;
    }
}
