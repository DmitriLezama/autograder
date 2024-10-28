package com.gophers.structures;

import java.util.TreeMap;
import java.util.List;
import java.util.Map;

import com.gophers.interfaces.Grade;

public class AssignmentGrade {
    Map<String, Integer> gradesMap;

    public AssignmentGrade(List<Grade> grades) {
        final String[] critieria = { "Bonus", "ChatBotGenerator", "ChatBot", "ChatBotPlatform", "ChatBotSimulation" };
        gradesMap = new TreeMap<String, Integer>();
        for (int i = 0; i < grades.size(); i++)
            gradesMap.put(critieria[i], grades.get(i).getMarks());
    }

    public String toString() {
        String result = "";
        for (Map.Entry<String, Integer> entry : gradesMap.entrySet())
            result += entry.getKey() + " : " + entry.getValue() + "\n";
        return result;
    }
}
