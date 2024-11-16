package com.gophers.interfaces;

import java.util.List;

/**
 * The TestRunner interface defines a method for executing test cases.
 *
 * @param <T> the type of the test result produced by the test runner
 */
public interface TestRunner<T> {
    /**
     * Runs all tests and returns their results.
     *
     * @return a {@link List} of results of type {@code T} produced by the executed
     *         tests
     */
    public abstract List<T> runAllTests();
}
