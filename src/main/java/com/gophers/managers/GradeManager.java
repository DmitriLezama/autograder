package com.gophers.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GradeManager {
    public void processSubmission(String submissionDirectory) {
        this.runTestsInNewJVM(submissionDirectory);
    }

    private void runTestsInNewJVM(String submissionDirectory) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "java",
                    "-cp",
                    System.getProperty("java.class.path"),
                    "com.gophers.AssignmentTestRunner",
                    submissionDirectory);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
