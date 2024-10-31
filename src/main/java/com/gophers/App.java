package com.gophers;

import java.util.List;
import com.gophers.managers.GradeManager;
import com.gophers.managers.SubmissionManager;
import com.gophers.utilities.FileCopier;
import com.gophers.utilities.ZipFileExtractor;

public class App {
    public static void main(String[] args) {
        SubmissionManager submissionManager = new SubmissionManager(new ZipFileExtractor());
        List<String> studentSubmissions = submissionManager.extractSubmissions("submissions.zip");
        GradeManager gradeManager = new GradeManager();

        for (String submission : studentSubmissions) {
            FileCopier.copyJavaFiles(submission);
            System.out.println("Processing " + submission);
            gradeManager.processSubmission(submission);
        }
        FileCopier.copyJavaFiles("src/main/resources/sample");
    }
}
