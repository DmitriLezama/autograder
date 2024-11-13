package com.gophers.services.helpers;

import java.util.Map;
import java.util.TreeMap;

import com.gophers.utilities.Constants;

public class GradeUtils {
    public static int calculateTotalGrade(Map<String, Integer> gradesMap) {
        int total = gradesMap.values().stream().mapToInt(Integer::intValue).sum();
        return total >= 90 ? 100 : total;
    }

    public static String calculateBonus(int totalScore, int bonus) {
        return totalScore >= 90 ? "5, 10, 10" : bonus >= 5 ? "5, " + (bonus - 5) : "0";
    }

    public static String getComment(int totalScore) {
        return gradeCommentMap().getOrDefault(totalScore / 10, "Needs Improvement");
    }

    private static Map<Integer, String> gradeCommentMap() {
        return Map.of(
                10, "Excellent Work!",
                7, "Great Work!",
                5, "Good Attempt");
    }

    public static Map<String, String> getFeedbackAsStatus(Map<String, Integer> feedback) {
        Map<String, String> feedbackStatus = new TreeMap<String, String>();
        feedback.forEach((test, passed) -> feedbackStatus.put(test, passed > 0 ? Constants.PASSED : Constants.FAILED));
        return feedbackStatus;
    }
}
