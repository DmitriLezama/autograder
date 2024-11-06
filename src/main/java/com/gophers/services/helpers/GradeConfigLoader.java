package com.gophers.services.helpers;

import com.gophers.structures.domain.TestFeedback;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GradeConfigLoader {
	private static final Map<String, Map<String, Integer>> weightings = new HashMap<>();
	private static final Map<String, Map<String, TestFeedback>> feedback = new HashMap<>();
	static {
		loadConfigurations();
	}

	@SuppressWarnings("unchecked")
	private static void loadConfigurations() {
		Yaml yaml = new Yaml();
		try (InputStream inputStream = GradeConfigLoader.class.getClassLoader().getResourceAsStream("data.yaml")) {
			if (inputStream == null) {
				throw new RuntimeException("YAML configuration file not found.");
			}
			Map<String, Object> config = yaml.load(inputStream);

			Map<String, Map<String, Integer>> weightingsConfig = (Map<String, Map<String, Integer>>) config
					.get("weightings");
			for (Map.Entry<String, Map<String, Integer>> entry : weightingsConfig.entrySet()) {
				weightings.put(entry.getKey(), entry.getValue());
			}

			Map<String, Map<String, Object>> feedbackConfig = (Map<String, Map<String, Object>>) config
					.get("testFeedback");
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

		} catch (Exception e) {
			throw new RuntimeException("Error loading configuration from YAML", e);
		}
	}

	public static Map<String, Integer> getWeightings(String gradeType) {
		return weightings.getOrDefault(gradeType, new HashMap<>());
	}

	public static Map<String, TestFeedback> getFeedback(String gradeType) {
		return feedback.getOrDefault(gradeType, new HashMap<>());
	}
}
