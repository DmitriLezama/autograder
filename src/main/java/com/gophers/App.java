package com.gophers;

import java.util.List;

public class App {
    public static void main(String[] args) {
        SubmissionManager submissionManager = new SubmissionManager(new ZipFileExtractor());
        List<String> studentSubmissionDirectories = submissionManager.extractSubmissions("submissions.zip");
        System.out.println("Student Submission Directories: " + studentSubmissionDirectories);
    }
}
