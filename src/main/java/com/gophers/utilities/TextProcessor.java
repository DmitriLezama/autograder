package com.gophers.utilities;

public class TextProcessor {

    public static boolean compareString(String expected, String actual) {
        expected = sanitizeString(expected);
        actual = sanitizeString(actual);
        return actual.contains(expected);
    }

    private static String sanitizeString(String input) {
        return input.replaceAll("\\s+", "").toLowerCase();
    }

    public static boolean matchString(String expected, String actual) {
        expected = sanitizeString(expected);
        actual = sanitizeString(actual);
        return actual.equals(expected);
    }

}
