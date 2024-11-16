package com.gophers.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for parsing a simple YAML structure from an input stream.
 * Parses YAML data where keys represent sections with integer values as entries.
 */
public class Yaml {

    /** Stores parsed YAML data, with section names as keys and maps of key-value pairs as values. */
    Map<String, Map<String, Integer>> result = new HashMap<>();

    /** The current section being populated with key-value pairs. */
    Map<String, Integer> currentSection = null;

    /**
     * Loads and parses YAML data from an input stream into a nested map structure.
     *
     * @param inputStream InputStream containing YAML-formatted data.
     * @return A map where each key represents a section containing a map of key-value pairs.
     * @throws IOException If an I/O error occurs while reading the input stream.
     */
    public Map<String, Map<String, Integer>> loadAs(InputStream inputStream) throws IOException {
        String content = new String(inputStream.readAllBytes());
        
        for (String line : content.split("\n")) {
            String trimmed = line.trim();
            
            if (trimmed.endsWith(":")) {
                result.put(trimmed.substring(0, trimmed.length() - 1), currentSection = new HashMap<>());
            } else if (trimmed.contains(":") && currentSection != null) {
                String[] parts = trimmed.split(":");
                currentSection.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            }
        }
        
        return result;
    }
}
