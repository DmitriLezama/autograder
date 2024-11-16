package com.gophers.services.handlers;

import com.gophers.interfaces.MarkSchemeLoaderStrategy;
import com.gophers.services.helpers.MarkSchemeLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * The GradeConfigLoader class is responsible for loading and providing grading
 * weightings
 * for various grading types.
 */
public class GradeConfigLoader {
	private static final Map<String, Map<String, Integer>> weightings;
	private static final MarkSchemeLoaderStrategy markScheme = new MarkSchemeLoader();

	// Static block to initialize the weightings map
	static {
		weightings = markScheme.loadWeightings();
	}

	/**
	 * Retrieves the weightings for a specific grade type.
	 *
	 * @param gradeType the grade type for which to retrieve weightings
	 * @return a map of test cases to their respective weightings, or an empty map
	 *         if the grade type is not found
	 */
	public static Map<String, Integer> getWeightings(String gradeType) {
		return weightings.getOrDefault(gradeType, new HashMap<>());
	}
}
