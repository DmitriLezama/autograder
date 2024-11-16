package com.gophers.interfaces;

import java.util.Map;

/**
 * The Grade interface defines methods to retrieve grading details.
 */
public interface Grade {
    /**
     * Retrieves the marks awarded for the assignment or task.
     *
     * @return the marks obtained
     */
    public abstract int getMarks();

    /**
     * Retrieves the total marks possible for the assignment or task.
     *
     * @return the total marks available
     */
    public abstract int getTotalMarks();

    /**
     * Retrieves feedback from tests as a mapping of test names to their scores.
     *
     * @return a {@link Map} containing test names as keys and their scores as
     *         values
     */
    public abstract Map<String, Integer> getTestFeedback();
}
