package com.gophers.structures.domain;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents details of a student derived from a submission file name.
 */
public class StudentDetails {
    private String firstName;
    private String lastName;
    private String studentID;

    /**
     * Constructor to parse student details from the submission file name.
     *
     * @param submission File path or name of the student's submission.
     * @throws IllegalArgumentException if the submission name does not match the
     *                                  required format.
     */
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

    /**
     * Converts the student details into a map format suitable for PDF generation.
     *
     * @return A map containing the PDF data for the student's details.
     */
    public Map<String, String> toPDFData() {
        Map<String, String> data = new HashMap<>();
        data.put("StudentID", this.studentID);
        data.put("StudentName", this.firstName + " " + this.lastName);
        return data;
    }

    /**
     * Provides a string representation of the student details.
     *
     * @return A string containing the student's ID, first name, and last name.
     */
    public String toString() {
        return studentID + " " + firstName + " " + lastName;
    }
}
