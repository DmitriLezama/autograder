package com.gophers.structures;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentDetails {
    private String firstName;
    private String lastName;
    private String studentID;

    public StudentDetails(String submission) {
        submission = Paths.get(submission).getFileName().toString();
        final String SUBMISSION_PATTERN = "^([A-Z][a-zA-Z]*)_([A-Z][a-zA-Z]*)_(816\\d{6})_A1$";
        Pattern pattern = Pattern.compile(SUBMISSION_PATTERN);
        Matcher matcher = pattern.matcher(submission);

        if (matcher.matches()) {
            this.firstName = matcher.group(1);
            this.lastName = matcher.group(2);
            this.studentID = matcher.group(3);
        } else {
            throw new IllegalArgumentException("Invalid submission format: " + submission);
        }
    }

    public Map<String, String> toPDFData() {
        Map<String, String> data = new HashMap<>();
        data.put("StudentID", this.studentID);
        data.put("StudentName", this.firstName + " " + this.lastName);
        return data;
    }
}
