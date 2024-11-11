package com.gophers.performance;

public class PerformanceTestResult {
    private boolean success;
    private Object result;
    private long duration;

    public PerformanceTestResult(boolean success, Object result, long duration) {
        this.success = success;
        this.result = result;
        this.duration = duration;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getResult() {
        return result;
    }

    public long getDuration() {
        return duration;
    }
}