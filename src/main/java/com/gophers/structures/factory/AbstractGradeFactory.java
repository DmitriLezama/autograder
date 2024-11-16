package com.gophers.structures.factory;

import org.junit.runner.Result;
import java.util.ArrayList;
import java.util.List;

import com.gophers.interfaces.Factory;
import com.gophers.interfaces.Grade;

/**
 * Abstract factory class for creating {@link Grade} objects.
 * Subclasses must implement the {@link #createItem(Result, int)} method.
 */
public abstract class AbstractGradeFactory implements Factory<Grade> {

    /**
     * Creates a list of {@link Grade} objects based on the given test results.
     *
     * @param results A list of JUnit {@link Result} objects containing the test
     *                results.
     * @return A list of {@link Grade} objects created for each test result.
     */
    public List<Grade> createItems(List<Result> results) {
        List<Grade> grades = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            grades.add(createItem(results.get(i), i));
        }
        return grades;
    }

    /**
     * Creates a single {@link Grade} object based on the given test result and
     * index.
     * This method must be implemented by subclasses to specify the type of
     * {@link Grade} to create.
     *
     * @param result The JUnit {@link Result} object containing the test result
     *               details.
     * @param index  The index to determine which grade object to create.
     * @return A {@link Grade} object.
     */
    public abstract Grade createItem(Result result, int index);
}
