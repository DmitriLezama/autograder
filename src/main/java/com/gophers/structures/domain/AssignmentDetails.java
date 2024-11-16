package com.gophers.structures.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents details of an assignment including the student's details and the
 * assignment grade.
 */
public class AssignmentDetails {
    private AssignmentGrade assignmentGrade;
    private StudentDetails studentDetails;

    /**
     * Constructor to initialize the student's details and the assignment grade.
     *
     * @param studentDetails  Details of the student.
     * @param assignmentGrade Grade details of the assignment.
     */
    public AssignmentDetails(StudentDetails studentDetails, AssignmentGrade assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
        this.studentDetails = studentDetails;
    }

    /**
     * Converts the assignment details into a map format suitable for PDF
     * generation.
     *
     * @return A map containing the PDF data for the assignment details.
     */
    public Map<String, String> toPDFData() {
        Map<String, String> data = new HashMap<>();
        data.putAll(studentDetails.toPDFData());
        data.putAll(assignmentGrade.toPDFData());
        return data;
    }

    /**
     * Provides a string representation of the assignment details.
     *
     * @return A string containing student and grade details.
     */
    public String toString() {
        return "Student - " + studentDetails.toString() + "\n" + assignmentGrade.toString();
    }
}
