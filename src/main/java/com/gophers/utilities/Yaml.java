package com.gophers.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Yaml {
    Map<String, Map<String, Integer>> result = new HashMap<>();
    Map<String, Integer> currentSection = null;
    
    public Map<String, Map<String, Integer>> loadAs(InputStream inputStream) throws IOException{
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
