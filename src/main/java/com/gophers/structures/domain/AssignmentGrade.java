package com.gophers.structures.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gophers.interfaces.Grade;

public class AssignmentGrade {
    private final Map<String, Integer> gradesMap = new TreeMap<>();
    private final List<TestFeedback> feedback = new ArrayList<>();

    public AssignmentGrade(List<Grade> grades) {
        String[] CRITERIA = { "Bonus", "ChatBotGenerator", "ChatBot", "ChatBotPlatform", "ChatBotSimulation" };
        for (int i = 0; i < grades.size(); i++) {
            gradesMap.put(CRITERIA[i], grades.get(i).getMarks());
            feedback.addAll(grades.get(i).getFailedFeedback());
        }
        feedback.sort(Comparator.comparingInt(TestFeedback::getPriority).reversed());
    }

    public Map<String, String> toPDFData() {
        Map<String, String> data = new TreeMap<String, String>();
        int grade = determineGrade();
        gradesMap.forEach((key, value) -> data.put(key, String.valueOf(value)));
        data.put("Bonus", determineBonus(grade));
        data.put("Total", String.valueOf(grade));
        data.put("StudentPercentage", grade + "%");
        data.put("FeedBack", determineComment(grade));
        return data;
    }

    private int determineGrade () {
        int sum = gradesMap.values().stream().mapToInt(Integer::intValue).sum();
        return sum >= 90 ? 100 : sum;
    }

    private String determineBonus(int totalScore) {
        int bonus = gradesMap.getOrDefault("Bonus", 0);
        return totalScore >= 90 ? "5, 10, 10" : bonus >= 5 ? "5, " + (bonus - 5) : "0";
    }

    private String determineComment(int totalScore) {
        if (totalScore >= 90) {
            return "Excellent Work!";
        }
        if (totalScore >= 70) {
            return "Great Work!";
        }
        if (totalScore >= 50) {
            return "Good Attempt";
        }
        return "Needs Improvement";
    }

    private List<String> getFeedback() {
        return feedback.stream().map(TestFeedback::getFeedback).toList();
    }

    private String getFormattedFeedback () {
        return String.join("; ", getFeedback());
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        gradesMap.forEach((key, value) -> result.append(key).append(" : ").append(value).append("\n"));
        return result.toString();
    }
}
