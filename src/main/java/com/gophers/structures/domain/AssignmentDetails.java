package com.gophers.structures.domain;

import java.util.HashMap;
import java.util.Map;

public class AssignmentDetails {
    private AssignmentGrade assignmentGrade;
    private StudentDetails studentDetails;

    public AssignmentDetails(StudentDetails studentDetails, AssignmentGrade assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
        this.studentDetails = studentDetails;
    }

    public Map<String, String> toPDFData() {
        Map<String, String> data = new HashMap<String, String>();
        data.putAll(studentDetails.toPDFData());
        data.putAll(assignmentGrade.toPDFData());
        return data;
    }
}
