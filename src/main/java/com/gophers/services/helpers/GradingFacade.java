package com.gophers.services.helpers;

import java.util.List;

import org.junit.runner.Result;
import com.gophers.interfaces.Facade;
import com.gophers.structures.domain.AssignmentDetails;
import com.gophers.structures.domain.AssignmentGrade;
import com.gophers.structures.domain.StudentDetails;
import com.gophers.structures.domain.Submission;
import com.gophers.structures.factory.AbstractGradeFactory;
import com.gophers.structures.factory.GradeFactory;
import com.gophers.interfaces.PDF;
import com.gophers.utilities.PDFGenerator;

public class GradingFacade implements Facade {
    private final AbstractGradeFactory gradeFactory = new GradeFactory();
    private final PDF pdf = new PDFGenerator();

    public void processSubmissions(String zipFilePath) {
        List<String> studentSubmissions = SubmissionProcessor.processSubmissions("submissions.zip");
        for (String studentSubmission : studentSubmissions) {
            Submission.resetInstance(studentSubmission);
            AssignmentDetails result = this.processSubmission(studentSubmission);
            pdf.generate(result);
        }
    }

    private AssignmentDetails processSubmission(String submissionDirectory) {
        List<Result> results = new AssignmentTestRunner().runAllTest();
        AssignmentGrade assignmentGrade = new AssignmentGrade(gradeFactory.createGrades(results));
        StudentDetails student = new StudentDetails(submissionDirectory);
        return new AssignmentDetails(student, assignmentGrade);
    }
}
