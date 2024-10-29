package com.gophers.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.gophers.interfaces.Grade;

public class AssignmentGrade {
    Map<String, Integer> gradesMap;
    List<TestFeedback> feedback;

    public AssignmentGrade(List<Grade> grades) {
        final String[] criteria = { "Bonus", "ChatBotGenerator", "ChatBot", "ChatBotPlatform", "ChatBotSimulation" };
        gradesMap = new TreeMap<>();
        feedback = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {
            gradesMap.put(criteria[i], grades.get(i).getMarks());
            feedback.addAll(grades.get(i).getFailedFeedback());
        }
        feedback.sort(Comparator.comparingInt(TestFeedback::getPriority));
    }

    public List<String> getFeedback(int n) {
        return feedback.stream().limit(n)
                .map(TestFeedback::getFeedback)
                .toList();
    }

    public Map<String, String> toPDFData() {
        Map<String, String> data = new TreeMap<>();
        int sum = gradesMap.values().stream().mapToInt(Integer::intValue).sum();
        gradesMap.forEach((key, value) -> data.put(key, String.valueOf(value)));
        data.put("StudentPercentage", sum + "%");
        data.put("Total", sum + "");
        return data;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : gradesMap.entrySet())
            result.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        result.append(getFeedback(5));
        return result.toString();
    }
}
