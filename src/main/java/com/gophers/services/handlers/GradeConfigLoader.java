package com.gophers.services.handlers;

import com.gophers.interfaces.ConfigLoaderStrategy;
import com.gophers.services.helpers.ConfigLoader;
import com.gophers.structures.domain.TestFeedback;
import java.util.HashMap;
import java.util.Map;

public class GradeConfigLoader {
	private static final Map<String, Map<String, Integer>> weightings;
	private static final Map<String, Map<String, TestFeedback>> feedback;

	private static final ConfigLoaderStrategy configLoader = new ConfigLoader();

	static {
		weightings = configLoader.loadWeightings();
		feedback = configLoader.loadFeedback();
	}

	public static Map<String, Integer> getWeightings(String gradeType) {
		return weightings.getOrDefault(gradeType, new HashMap<>());
	}

	public static Map<String, TestFeedback> getFeedback(String gradeType) {
		return feedback.getOrDefault(gradeType, new HashMap<>());
	}
}
