package com.gophers.interfaces;

import java.util.Map;

/**
 * The MarkSchemeLoaderStrategy interface defines a method for loading grading
 * weightings.
 */
public interface MarkSchemeLoaderStrategy {
    /**
     * Loads the weightings for each test case or grading component.
     *
     * @return a {@link Map} where the keys are component names and the values are
     *         maps
     *         containing test names and their respective weightings
     */
    public abstract Map<String, Map<String, Integer>> loadWeightings();
}
