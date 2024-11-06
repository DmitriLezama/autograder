package com.gophers;

import java.util.List;

import com.gophers.managers.GradeManager;
import com.gophers.managers.SubmissionManager;
import com.gophers.structures.domain.AssignmentDetails;
import com.gophers.utilities.ZipFileExtractor;

public class App {
    public static void main(String[] args) {
        SubmissionManager submissionManager = new SubmissionManager(new ZipFileExtractor());
        List<String> studentSubmissions = submissionManager.extractSubmissions("submissions.zip");
        GradeManager gradeManager = new GradeManager();

        for (String studentSubmission : studentSubmissions) {
            Submission.resetInstance(studentSubmission);
            AssignmentDetails result = gradeManager.getAssignmentDetails(studentSubmission);
            gradeManager.generatePDFGrade(result);
        }
    }
}
