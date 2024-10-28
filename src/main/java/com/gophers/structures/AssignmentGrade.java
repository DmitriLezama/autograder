package com.gophers.structures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gophers.interfaces.Grade;

public class AssignmentGrade {
    final String[] critieria = { "ChatBotGenerator", "ChatBot", "ChatBotPlatform", "ChatBotSimulation" };
    Map<String, Integer> gradesMap;

    public AssignmentGrade(List<Grade> grades) {
        gradesMap = new HashMap<String, Integer>();
        for (int i = 0; i < grades.size(); i++)
            gradesMap.put(critieria[i], grades.get(i).getMarks());
    }

    public Map<String, String> toPDFData() {
        Map<String, String> data = new HashMap<>();
        int sum = gradesMap.values().stream().mapToInt(Integer::intValue).sum();
        gradesMap.forEach((key, value) -> data.put(key, String.valueOf(value)));
        data.put("StudentPercentage", sum + "%");
        data.put("Total", sum + "");
        return data;
    }

    public String toString() {
        String output = "";
        for (String key : gradesMap.keySet())
            output += key + " : " + gradesMap.get(key) + "\n";
        return output;
    }
}
