package com.gophers.interfaces;

import com.gophers.structures.domain.TestFeedback;
import java.util.Map;

public interface ConfigLoaderStrategy {
    public abstract Map<String, Map<String, Integer>> loadWeightings();

    public abstract Map<String, Map<String, TestFeedback>> loadFeedback();
}
