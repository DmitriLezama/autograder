package com.gophers.services.helpers;

import com.gophers.interfaces.ConfigLoaderStrategy;
import com.gophers.structures.domain.TestFeedback;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader implements ConfigLoaderStrategy {

    @Override
    public Map<String, Map<String, Integer>> loadWeightings() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("data.yaml")))) {
            
            Map<String, Map<String, Integer>> weightings = new HashMap<>();
            String currentCategory = null;
            String line;
            while ((line = reader.readLine()) != null && !line.trim().equals("weightings:"));
            
            while ((line = reader.readLine()) != null && !line.trim().equals("testFeedback:")) {
                if (line.trim().isEmpty()) continue;
                
                int indentLevel = countIndentation(line);
                line = line.trim();
                
                if (indentLevel == 4 && line.endsWith(":")) {
                    currentCategory = line.substring(0, line.length() - 1);
                    weightings.put(currentCategory, new HashMap<>());
                } else if (indentLevel == 8 && line.contains(":")) {
                    String[] parts = line.split(":\\s*");
                    if (parts.length == 2 && currentCategory != null) {
                        try {
                            weightings.get(currentCategory).put(parts[0], Integer.parseInt(parts[1]));
                        } catch (NumberFormatException e) {
                            continue;
                        }
                    }
                }
            }
            return weightings;
        } catch (Exception e) {
            throw new RuntimeException("Error loading weightings", e);
        }
    }

    @Override
    public Map<String, Map<String, TestFeedback>> loadFeedback() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("data.yaml")))) {
            
            Map<String, Map<String, TestFeedback>> feedback = new HashMap<>();
            String currentCategory = null;
            String currentTest = null;
            String feedbackMsg = null;
            Integer priority = null;
            int indentLevel = 0;
            
            String line;
            while ((line = reader.readLine()) != null && !line.trim().equals("testFeedback:"));
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                indentLevel = countIndentation(line);
                line = line.trim();
                
                if (indentLevel == 4 && line.endsWith(":")) {
                    currentCategory = line.substring(0, line.length() - 1);
                    feedback.put(currentCategory, new HashMap<>());
                } else if (indentLevel == 8 && line.endsWith(":")) {
                    currentTest = line.substring(0, line.length() - 1);
                    feedbackMsg = null;
                    priority = null;
                } else if (indentLevel == 12 && line.contains(":")) {
                    String[] parts = line.split(":\\s*");
                    if (parts.length == 2) {
                        if (parts[0].equals("feedback")) {
                            feedbackMsg = parts[1].replace("\"", "");
                        } else if (parts[0].equals("priority")) {
                            priority = Integer.parseInt(parts[1]);
                        }
                        
                        if (feedbackMsg != null && priority != null) {
                            feedback.get(currentCategory).put(currentTest, 
                                new TestFeedback(feedbackMsg, priority));
                            feedbackMsg = null;
                            priority = null;
                        }
                    }
                }
            }
            return feedback;
        } catch (Exception e) {
            throw new RuntimeException("Error loading feedback", e);
        }
    }

    private int countIndentation(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == ' ') {
            count++;
        }
        return count;
    }
}