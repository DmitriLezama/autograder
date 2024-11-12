package com.gophers.services.helpers;

import com.gophers.interfaces.MarkSchemeLoaderStrategy;
import com.gophers.utilities.Constants;
import com.gophers.utilities.Yaml;

import java.io.InputStream;
import java.util.Map;

public class MarkSchemeLoader implements MarkSchemeLoaderStrategy {
    public Map<String, Map<String, Integer>> loadWeightings() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.MARK_SCHEME)) {
            if (inputStream == null) {
                throw new IllegalStateException("Could not find mark scheme resource: " + Constants.MARK_SCHEME);
            }
            return new Yaml().loadAs(inputStream);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load mark scheme weightings", e);
        }
    }
}