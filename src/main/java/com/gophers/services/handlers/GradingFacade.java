package com.gophers.services.handlers;

import java.util.List;
import org.junit.runner.Result;
import com.gophers.interfaces.Facade;
import com.gophers.structures.domain.*;
import com.gophers.structures.factory.*;
import com.gophers.interfaces.PDF;

/**
 * The GradingFacade class provides a high-level interface for processing
 * student submissions,
 * running tests, grading, and generating reports.
 */
public class GradingFacade implements Facade {
    private final AbstractGradeFactory gradeFactory = new GradeFactory();
    private final PDF pdf = new PDFGenerator();

    /**
     * Processes student submissions from a zip file. This includes extracting
     * submissions,
     * running tests, grading, and generating PDF reports.
     *
     * @param zipFilePath the path to the zip file containing student submissions
     */
    public void processSubmissions(String zipFilePath) {
        List<String> studentSubmissions = SubmissionExtractor.extractSubmissions(zipFilePath);
        for (String studentSubmission : studentSubmissions) {
            Submission.resetInstance(studentSubmission);
            AssignmentDetails result = this.processSubmission(studentSubmission);
            System.out.println(result.toString());
            pdf.generate(result);
        }
    }

    /**
     * Processes a single student submission by running tests, calculating grades,
     * and preparing assignment details.
     *
     * @param submissionDirectory the directory containing the student's submission
     * @return an {@link AssignmentDetails} object containing the student details
     *         and their grades
     */
    public AssignmentDetails processSubmission(String submissionDirectory) {
        List<Result> results = new AssignmentTestRunner().runAllTests();
        AssignmentGrade assignmentGrade = new AssignmentGrade(gradeFactory.createItems(results));
        StudentDetails student = new StudentDetails(submissionDirectory);
        return new AssignmentDetails(student, assignmentGrade);
    }
}
