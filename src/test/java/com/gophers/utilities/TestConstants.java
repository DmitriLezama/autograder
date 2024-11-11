package com.gophers.utilities;

public class TestConstants {
    private TestConstants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }

    public static final String SAMPLE_PATH = "src/main/resources/sample";

    public static final long CRITICAL_PERFORMANCE_THRESHOLD_MS = 1000; // Must meet for ideal performance
    public static final long HIGH_PRIORITY_THRESHOLD_MS = 3000; // High priority, should meet for good user experience
    public static final long TARGET_PERFORMANCE_THRESHOLD_MS = 6000; // Standard goal, acceptable for most functions
    public static final long ACCEPTABLE_LIMIT_THRESHOLD_MS = 8000; // Tolerable not ideal; for low-priority operations
    public static final long MAX_ALLOWABLE_THRESHOLD_MS = 20000; // Maximum allowable time; beyond this is failure
}
