package com.gophers.interfaces;

/**
 * The Facade interface defines a method for processing student submissions.
 */
public interface Facade {
    /**
     * Processes the submissions contained in the provided zip file.
     *
     * @param zipFilePath the path to the zip file containing submissions
     */
    public void processSubmissions(String zipFilePath);
}
