package com.gophers.services.handlers;

import com.gophers.interfaces.MarkSchemeLoaderStrategy;
import com.gophers.services.helpers.MarkSchemeLoader;
import java.util.HashMap;
import java.util.Map;

public class GradeConfigLoader {
	private static final Map<String, Map<String, Integer>> weightings;
	private static final MarkSchemeLoaderStrategy markScheme = new MarkSchemeLoader();

	static {
		weightings = markScheme.loadWeightings();
	}

	public static Map<String, Integer> getWeightings(String gradeType) {
		return weightings.getOrDefault(gradeType, new HashMap<>());
	}
}
