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
        data.put("StudentName", studentDetails.getStudentName());
        data.put("StudentID", studentDetails.getStudentID());
        data.put("StudentPercentage", "100%"); // hardcoded for now
        data.putAll(assignmentGrade.getGradesMap());
        return data;
    }

}
