package com.gophers.utilities;

public class ExecutionTimer {
    public static <T> PerformanceTestResult testExecutionTime(Runnable task, long threshold, String testName) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1_000_000;
        boolean success = elapsedTime <= threshold;
        System.out.println("Execution Time of " + testName + ": " + elapsedTime + " ms");
        return new PerformanceTestResult(testName, success, elapsedTime);
    }
}
