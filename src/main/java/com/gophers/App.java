package com.gophers;

import java.util.List;

import com.gophers.managers.GradeManager;
import com.gophers.managers.SubmissionManager;
import com.gophers.utilities.FileCopier;
import com.gophers.utilities.ZipFileExtractor;

public class App {

    private static final String samplePath = "src/main/resources/sample";

    public static void main(String[] args) {
        // FileCopier.copyJavaFiles(samplePath);

        SubmissionManager submissionManager = new SubmissionManager(new ZipFileExtractor());
        List<String> studentSubmissions = submissionManager.extractSubmissions("submissions.zip");

        for (String studentSubmission : studentSubmissions) {
            Submission.resetInstance(studentSubmission);
            Class<?> chatBot = Submission.getInstance(studentSubmission).getClass("ChatBot");
            // if (chatBot != null) {
            // Submission.getInstance(studentSubmission).invokeMethod("ChatBot", "toString",
            // new Class[] {},
            // new Object[] {});
            // System.out.println(Submission.getInstance(studentSubmissions.get(0)).toString());
            // }
            // GradeManager gradeManager = new GradeManager();
            // var result = gradeManager.getAssignmentDetails(studentSubmissions.get(1));
            // gradeManager.generatePDFGrade(result);
            // FileCopier.copyJavaFiles(samplePath);
        }
    }
}
