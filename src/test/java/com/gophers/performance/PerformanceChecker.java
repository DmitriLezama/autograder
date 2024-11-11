package com.gophers.performance;

import java.util.concurrent.Callable;

public class PerformanceChecker {
    
    public static PerformanceTestResult testExecutionTime(Callable<?> method, long performanceThreshold, String testName) {
        try {
            long startTime = System.nanoTime();
            Object result = method.call();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println("Execution Time of " + testName + ": " + duration + " ms");
            return new PerformanceTestResult(duration > performanceThreshold, result, duration);
        }
        catch (Exception e) {
            System.err.println("Execution of " + testName + " failed");
        }
        return null;
    }
}
