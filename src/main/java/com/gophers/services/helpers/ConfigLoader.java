package com.gophers.services.helpers;

import com.gophers.interfaces.ConfigLoaderStrategy;
import com.gophers.structures.domain.TestFeedback;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader implements ConfigLoaderStrategy {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Map<String, Integer>> loadWeightings() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.yaml")) {
            if (inputStream == null) {
                throw new RuntimeException("YAML configuration file not found.");
            }
            Map<String, Object> config = yaml.load(inputStream);
            return (Map<String, Map<String, Integer>>) config.getOrDefault("weightings", new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException("Error loading weightings from YAML", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Map<String, TestFeedback>> loadFeedback() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.yaml")) {
            if (inputStream == null) {
                throw new RuntimeException("YAML configuration file not found.");
            }
            Map<String, Object> config = yaml.load(inputStream);
            Map<String, Map<String, Object>> feedbackConfig = (Map<String, Map<String, Object>>) config
                    .getOrDefault("testFeedback", new HashMap<>());

            Map<String, Map<String, TestFeedback>> feedback = new HashMap<>();
            for (Map.Entry<String, Map<String, Object>> entry : feedbackConfig.entrySet()) {
                Map<String, TestFeedback> feedbackMap = new HashMap<>();
                for (Map.Entry<String, Object> feedbackEntry : entry.getValue().entrySet()) {
                    Map<String, Object> feedbackData = (Map<String, Object>) feedbackEntry.getValue();
                    String message = (String) feedbackData.get("feedback");
                    int score = (int) feedbackData.get("priority");
                    feedbackMap.put(feedbackEntry.getKey(), new TestFeedback(message, score));
                }
                feedback.put(entry.getKey(), feedbackMap);
            }
            return feedback;
        } catch (Exception e) {
            throw new RuntimeException("Error loading feedback from YAML", e);
        }
    }
}
