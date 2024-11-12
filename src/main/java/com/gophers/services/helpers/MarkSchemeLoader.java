package com.gophers.services.helpers;

import com.gophers.interfaces.MarkSchemeLoaderStrategy;
import com.gophers.utilities.Constants;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class MarkSchemeLoader implements MarkSchemeLoaderStrategy {
    @Override
    public Map<String, Map<String, Integer>> loadWeightings() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.MARK_SCHEME)) {
            if (inputStream == null) {
                throw new RuntimeException("YAML configuration file not found.");
            }
            return new Yaml().loadAs(inputStream, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Error loading weightings from YAML", e);
        }
    }
}
