package com.gophers.interfaces;

import java.util.List;
import org.junit.runner.Result;

/**
 * The Factory interface defines methods for creating items or a collection of
 * items
 * based on JUnit test results.
 *
 * @param <T> the type of item to be created by the factory
 */
public interface Factory<T> {
    /**
     * Creates a list of items based on a list of JUnit test results.
     *
     * @param results the list of JUnit {@link Result} objects from which to create
     *                items
     * @return a list of items of type {@code T} created based on the given results
     */
    public abstract List<T> createItems(List<Result> results);

    /**
     * Creates a single item based on a JUnit test result and its index.
     *
     * @param result the JUnit {@link Result} object to base the item on
     * @param index  the index of the result in the list
     * @return an item of type {@code T} created based on the given result and index
     */
    public abstract T createItem(Result result, int index);
}
