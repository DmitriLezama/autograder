package com.gophers.utilities;

/**
 * Utility class for processing and comparing text strings.
 */
public class TextProcessor {

    /**
     * Compares two strings by checking if the sanitized actual string contains the sanitized expected string.
     *
     * @param expected The expected substring to look for.
     * @param actual   The string to be checked for containing the expected substring.
     * @return True if the actual string contains the expected substring, false otherwise.
     */
    public static boolean compareString(String expected, String actual) {
        expected = sanitizeString(expected);
        actual = sanitizeString(actual);
        return actual.contains(expected);
    }

    /**
     * Removes whitespace and converts the string to lowercase for consistent comparison.
     *
     * @param input The string to be sanitized.
     * @return A sanitized string with no whitespace and in lowercase.
     */
    private static String sanitizeString(String input) {
        return input.replaceAll("\\s+", "").toLowerCase();
    }

    /**
     * Compares two strings by checking if they are identical after sanitization.
     *
     * @param expected The expected string.
     * @param actual   The actual string to compare.
     * @return True if the sanitized strings are identical, false otherwise.
     */
    public static boolean matchString(String expected, String actual) {
        expected = sanitizeString(expected);
        actual = sanitizeString(actual);
        return actual.equals(expected);
    }
}
