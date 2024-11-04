package com.gophers.managers;

import java.util.List;
import org.junit.runner.Result;
import com.gophers.interfaces.PDF;
import com.gophers.structures.AssignmentDetails;
import com.gophers.structures.AssignmentGrade;
import com.gophers.structures.StudentDetails;
import com.gophers.structures.factory.AbstractGradeFactory;
import com.gophers.structures.factory.GradeFactory;
import com.gophers.utilities.AssignmentTestRunner;
import com.gophers.utilities.FileCopier;
import com.gophers.utilities.PDFGenerator;

public class GradeManager {
    private final AbstractGradeFactory gradeFactory = new GradeFactory();

    public AssignmentDetails getAssignmentDetails(String submissionDirectory) {
        FileCopier.copyJavaFiles(submissionDirectory);
        List<Result> results = new AssignmentTestRunner().runAllTest();
        AssignmentGrade assignmentGrade = new AssignmentGrade(gradeFactory.createGrades(results));
        StudentDetails student = new StudentDetails(submissionDirectory);
        // Logging
        System.out.println("Results:\n" + assignmentGrade.toString());
        return new AssignmentDetails(student, assignmentGrade);
    }

    public void generatePDFGrade(AssignmentDetails assignmentDetails) {
        PDF pdf = new PDFGenerator();
        pdf.generate(assignmentDetails);
    }
}
