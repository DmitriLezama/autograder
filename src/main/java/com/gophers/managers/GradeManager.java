package com.gophers.managers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Result;

import com.gophers.interfaces.Grade;
import com.gophers.interfaces.PDF;
import com.gophers.structures.AssignmentDetails;
import com.gophers.structures.AssignmentGrade;
import com.gophers.structures.ChatBotGeneratorGrade;
import com.gophers.structures.ChatBotGrade;
import com.gophers.structures.ChatBotPlatformGrade;
import com.gophers.structures.ChatBotSimulationGrade;
import com.gophers.structures.StudentDetails;
import com.gophers.utilities.AssignmentTestRunner;
import com.gophers.utilities.PDFGenerator;

public class GradeManager {

    public AssignmentDetails getAssignmentDetails(String submissionDirectory) {
        AssignmentTestRunner assignmentTestRunner = new AssignmentTestRunner();
        List<Result> results = assignmentTestRunner.runAllTest();
        List<Grade> grades = new ArrayList<>();
        Grade chatBotGeneratorGrade = new ChatBotGeneratorGrade(results.get(0));
        Grade chatBotGrade = new ChatBotGrade(results.get(1));
        Grade chatBotPlatformGrade = new ChatBotPlatformGrade(results.get(2));
        Grade chatBotSimulationGrade = new ChatBotSimulationGrade(results.get(3));
        grades.add(chatBotGeneratorGrade);
        grades.add(chatBotGrade);
        grades.add(chatBotPlatformGrade);
        grades.add(chatBotSimulationGrade);
        AssignmentGrade assignmentGrade = new AssignmentGrade(grades);
        StudentDetails student = new StudentDetails("816035591", "Dmitri", "Lezama");
        AssignmentDetails assignmentDetails = new AssignmentDetails(student, assignmentGrade);
        return assignmentDetails;
    }

    public void generatePDFGrade(AssignmentDetails assignmentDetails) {
        PDF pdf = new PDFGenerator();
        pdf.generate(assignmentDetails);
    }
}
