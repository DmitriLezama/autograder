package com.gophers;

import java.util.List;

import com.gophers.managers.GradeManager;
import com.gophers.services.helpers.SubmissionProcessor;
import com.gophers.structures.domain.AssignmentDetails;
import com.gophers.structures.domain.Submission;

public class App {
    public static void main(String[] args) {
        List<String> studentSubmissions = SubmissionProcessor.processSubmissions("submissions.zip");
        // System.out.println(studentSubmissions);
        GradeManager gradeManager = new GradeManager();

        for (String studentSubmission : studentSubmissions) {
            Submission.resetInstance(studentSubmission);
            AssignmentDetails result = gradeManager.getAssignmentDetails(studentSubmission);
            gradeManager.generatePDFGrade(result);
        }
    }
}
