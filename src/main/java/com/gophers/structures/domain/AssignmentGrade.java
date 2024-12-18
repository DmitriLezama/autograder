package com.gophers.structures.domain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.gophers.interfaces.Grade;
import com.gophers.services.helpers.GradeUtils;
import com.gophers.utilities.Constants;

public class AssignmentGrade {
    private final Map<String, Integer> gradesMap = new TreeMap<>();
    private final Map<String, Integer> feedback = new TreeMap<>();

    public AssignmentGrade(List<Grade> grades) {
        for (int i = 0; i < grades.size(); i++) {
            Grade grade = grades.get(i);
            gradesMap.put(Constants.CRITERIA[i], grade.getMarks());
            feedback.putAll(grade.getTestFeedback());
        }
    }

    public Map<String, String> toPDFData() {
        Map<String, String> data = new TreeMap<>();
        int grade = GradeUtils.calculateTotalGrade(gradesMap);
        gradesMap.forEach((criterion, score) -> data.put(criterion, String.valueOf(score)));
        data.putAll(GradeUtils.getFeedbackAsStatus(feedback));
        data.put(Constants.PASSES_ALL_JUNIT_TESTS, grade > 90 ? Constants.PASSED : Constants.FAILED);
        data.put(Constants.BONUS, GradeUtils.calculateBonus(grade, gradesMap.getOrDefault(Constants.CRITERIA[0], 0)));
        data.put(Constants.TOTAL, String.valueOf(grade));
        data.put(Constants.STUDENT_PERCENTAGE, grade + "%");
        data.put(Constants.FEEDBACK, GradeUtils.getComment(grade));
        return data;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < gradesMap.size(); i++) {
            String criterion = Constants.CRITERIA[i];
            result.append(GradeUtils.formatTestResult(criterion, gradesMap.get(criterion), 
                        GradeUtils.getTotalMarks(i)));
        }
        result.append(GradeUtils.formatTestResult("Total Marks Earned", 
                        GradeUtils.calculateTotalGrade(gradesMap), Constants.ASSIGNMENT_TOTAL_MARKS));
        return result.toString();
    }
}
