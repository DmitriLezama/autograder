package com.gophers.interfaces;

import java.util.Map;

public interface MarkSchemeLoaderStrategy {
    public abstract Map<String, Map<String, Integer>> loadWeightings();
}
