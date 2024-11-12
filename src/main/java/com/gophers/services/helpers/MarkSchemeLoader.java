package com.gophers.services.helpers;

import com.gophers.interfaces.MarkSchemeLoaderStrategy;
import com.gophers.utilities.Constants;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MarkSchemeLoader implements MarkSchemeLoaderStrategy {
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Map<String, Integer>> loadWeightings() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.MARK_SCHEME)) {
            if (inputStream == null) {
                throw new RuntimeException("YAML configuration file not found.");
            }
            Map<String, Object> config = yaml.load(inputStream);
            return (Map<String, Map<String, Integer>>) config.getOrDefault("weightings", new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException("Error loading weightings from YAML", e);
        }
    }
}
