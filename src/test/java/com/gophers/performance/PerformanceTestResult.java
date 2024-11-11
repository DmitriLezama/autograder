package com.gophers.performance;

public class PerformanceTestResult {
    private final String testName;
    private final boolean success;
    private final long executionTime;

    public PerformanceTestResult(String testName, boolean success, long executionTime) {
        this.testName = testName;
        this.success = success;
        this.executionTime = executionTime;
    }

    public String getTestName() {
        return testName;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public Object getResult() {
        return success ? "Test Passed" : "Test Failed";
    }
}
