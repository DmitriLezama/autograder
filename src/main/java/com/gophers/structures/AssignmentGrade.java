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

    public String toString() {
        String result = "";
        for (var entry : gradesMap.entrySet())
            result += entry.getKey() + " : " + entry.getValue() + "\n";
        return result;
    }
}
