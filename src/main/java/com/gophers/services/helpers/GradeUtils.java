package com.gophers.services.helpers;

import java.util.Map;
import java.util.TreeMap;

import com.gophers.utilities.Constants;

/**
 * Utility class for grading-related operations, including calculations and
 * formatting.
 */
public class GradeUtils {
    /**
     * Calculates the total grade based on the provided grades map.
     * Caps the total grade at 100 if it reaches 90 or more.
     *
     * @param gradesMap a {@link Map} with grade criteria as keys and scores as
     *                  values
     * @return the total grade
     */
    public static int calculateTotalGrade(Map<String, Integer> gradesMap) {
        int total = gradesMap.values().stream().mapToInt(Integer::intValue).sum();
        return total >= 90 ? 100 : total;
    }

    /**
     * Calculates the bonus breakdown based on the total score and bonus value.
     *
     * @param totalScore the total score achieved
     * @param bonus      the bonus points awarded
     * @return a {@link String} representing the bonus breakdown
     */
    public static String calculateBonus(int totalScore, int bonus) {
        return totalScore >= 90 ? "5, 10, 10" : bonus >= 5 ? "5, " + (bonus - 5) : "0";
    }

    /**
     * Retrieves a comment based on the total score.
     *
     * @param totalScore the total score achieved
     * @return a {@link String} comment associated with the score range
     */
    public static String getComment(int totalScore) {
        return gradeCommentMap().getOrDefault(totalScore / 10, "Needs Improvement");
    }

    /**
     * Generates feedback statuses (Passed/Failed) for tests.
     *
     * @param feedback a {@link Map} with test names as keys and pass status as
     *                 values
     * @return a {@link Map} with test names as keys and "Passed"/"Failed" as values
     */
    public static Map<String, String> getFeedbackAsStatus(Map<String, Integer> feedback) {
        Map<String, String> feedbackStatus = new TreeMap<>();
        feedback.forEach((test, passed) -> feedbackStatus.put(test, passed > 0 ? Constants.PASSED : Constants.FAILED));
        return feedbackStatus;
    }

    /**
     * Retrieves the total marks for a specific index.
     *
     * @param index the index representing a grading component
     * @return the total marks for the component
     * @throws IllegalArgumentException if the index is invalid
     */
    public static int getTotalMarks(int index) {
        switch (index) {
            case Constants.PROGRAM_GRADE_INDEX:
                return Constants.PROGRAM_GRADE_TOTAL_MARKS;
            case Constants.CHATBOT_GENERATOR_GRADE_INDEX:
                return Constants.CHATBOT_GENERATOR_GRADE_TOTAL_MARKS;
            case Constants.CHATBOT_GRADE_INDEX:
                return Constants.CHATBOT_GRADE_TOTAL_MARKS;
            case Constants.CHATBOT_PLATFORM_GRADE_INDEX:
                return Constants.CHATBOT_PLATFORM_GRADE_TOTAL_MARKS;
            case Constants.CHATBOT_SIMULATION_GRADE_INDEX:
                return Constants.CHATBOT_SIMULATION_GRADE_TOTAL_MARKS;
            default:
                throw new IllegalArgumentException("Unexpected index: " + index);
        }
    }

    /**
     * Formats a test result string.
     *
     * @param criterion   the test criterion
     * @param marksEarned the marks earned for the test
     * @param totalMarks  the total marks for the test
     * @return a formatted string representing the test result
     */
    public static String formatTestResult(String criterion, int marksEarned, int totalMarks) {
        return String.format("%-20s %d/%-3d\n", criterion, marksEarned, totalMarks);
    }

    /**
     * Returns a mapping of score ranges to comments.
     *
     * @return a {@link Map} of score ranges to comments
     */
    private static Map<Integer, String> gradeCommentMap() {
        return Map.of(
                10, "Excellent Work!",
                7, "Great Work!",
                5, "Good Attempt");
    }
}
