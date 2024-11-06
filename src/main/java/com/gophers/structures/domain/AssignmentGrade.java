package com.gophers.structures.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gophers.interfaces.Grade;

public class AssignmentGrade {
    private static final String[] CRITERIA = { "Bonus", "ChatBotGenerator", "ChatBot", "ChatBotPlatform",
            "ChatBotSimulation" };
    private final Map<String, Integer> gradesMap = new TreeMap<>();
    private final List<TestFeedback> feedback = new ArrayList<>();

    public AssignmentGrade(List<Grade> grades) {
        for (int i = 0; i < grades.size(); i++) {
            gradesMap.put(CRITERIA[i], grades.get(i).getMarks());
            feedback.addAll(grades.get(i).getFailedFeedback());
        }
        feedback.sort(Comparator.comparingInt(TestFeedback::getPriority).reversed());
    }

    public Map<String, String> toPDFData() {
        int sum = gradesMap.values().stream().mapToInt(Integer::intValue).sum();
        sum = sum >= 90 ? 100 : sum;
        Map<String, String> data = new TreeMap<String, String>();
        gradesMap.forEach((key, value) -> data.put(key, String.valueOf(value)));
        data.put("Bonus", determineBonus(sum));
        data.put("Total", String.valueOf(sum));
        data.put("StudentPercentage", sum + "%");
        System.out.println(String.join("; ", getFeedback(5)));
        data.put("FeedBack", sum >= 90 ? "Excellent Work" : String.join("; ", getFeedback(5)));
        return data;
    }

    private String determineBonus(int totalScore) {
        int bonus = gradesMap.getOrDefault("Bonus", 0);
        return totalScore >= 90 ? "5, 10, 10" : bonus >= 5 ? "5, " + (bonus - 5) : "0";
    }

    private List<String> getFeedback(int n) {
        return feedback.stream().limit(n).map(TestFeedback::getFeedback).toList();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        gradesMap.forEach((key, value) -> result.append(key).append(" : ").append(value).append("\n"));
        return result.toString();
    }
}
