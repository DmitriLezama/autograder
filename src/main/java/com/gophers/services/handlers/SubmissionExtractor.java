package com.gophers.services.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.gophers.services.helpers.SubmissionOrganizer;
import com.gophers.utilities.Constants;
import com.gophers.utilities.ZipFileExtractor;

/**
 * The SubmissionExtractor class handles extracting student submissions from a
 * zip file.
 */
public class SubmissionExtractor {
    /**
     * Extracts submissions from the given zip file and organizes them.
     *
     * @param submissionsZipPath the path to the zip file containing student
     *                           submissions
     * @return a {@link List} of paths to the extracted and organized submissions
     */
    public static List<String> extractSubmissions(String submissionsZipPath) {
        try (InputStream zipStream = loadResource(submissionsZipPath)) {
            ZipFileExtractor.extractZipEntries(zipStream, Constants.OUTPUT_DIRECTORY);
            return SubmissionOrganizer.organizeSubmissions(Constants.OUTPUT_DIRECTORY);
        } catch (IOException e) {
            return List.of();
        }
    }

    /**
     * Loads a resource file as an {@link InputStream}.
     *
     * @param resourceFileName the name of the resource file to load
     * @return an {@link InputStream} for the resource file
     * @throws IOException if the resource file cannot be found or loaded
     */
    private static InputStream loadResource(String resourceFileName) throws IOException {
        InputStream inputStream = SubmissionExtractor.class.getClassLoader().getResourceAsStream(resourceFileName);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourceFileName);
        }
        return inputStream;
    }
}
