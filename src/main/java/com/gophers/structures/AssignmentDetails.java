package com.gophers.structures;

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
        data.put("Bonus", "5, 10, 10, 5");
        data.put("FeedBack", "Good Work");
        return data;
    }
}
