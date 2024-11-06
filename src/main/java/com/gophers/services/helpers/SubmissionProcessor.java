package com.gophers.services.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.gophers.utilities.Constants;
import com.gophers.utilities.ZipFileExtractor;

public class SubmissionProcessor {
    public static List<String> processSubmissions(String submissionsZipPath) {
        try (InputStream zipStream = loadResource(submissionsZipPath)) {
            ZipFileExtractor.extractZipEntries(zipStream, Constants.OUTPUT_DIRECTORY);
            return SubmissionOrganizer.organizeSubmissions(Constants.OUTPUT_DIRECTORY);
        } catch (IOException e) {
            return List.of();
        }
    }

    private static InputStream loadResource(String resourceFileName) throws IOException {
        InputStream inputStream = SubmissionProcessor.class.getClassLoader().getResourceAsStream(resourceFileName);
        if (inputStream == null)
            throw new IOException("Resource not found: " + resourceFileName);
        return inputStream;
    }
}
