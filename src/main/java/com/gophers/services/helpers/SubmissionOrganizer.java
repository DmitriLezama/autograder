package com.gophers.services.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.gophers.utilities.Constants;
import com.gophers.utilities.ZipFileExtractor;

/**
 * The SubmissionOrganizer class organizes student submissions by unzipping
 * files and preparing directories.
 */
public class SubmissionOrganizer {
    /**
     * Organizes submissions in the specified output directory.
     *
     * @param outputDirectory the directory where unzipped submissions will be
     *                        stored
     * @return a {@link List} of paths to organized student directories
     * @throws IOException if an error occurs during file operations
     */
    public static List<String> organizeSubmissions(String outputDirectory) throws IOException {
        List<String> studentDirectories = new ArrayList<>();
        try (Stream<Path> paths = Files.list(Paths.get(outputDirectory))) {
            paths.filter(Files::isRegularFile).forEach(zipFile -> {
                String studentDirName = zipFile.getFileName().toString().replace(Constants.ZIP_EXTENSION, "");
                File studentDir = new File(outputDirectory, studentDirName);
                studentDir.mkdir();
                unzipFileToDirectory(zipFile, studentDir);
                studentDirectories.add(studentDir.getAbsolutePath());
            });
        }
        return studentDirectories;
    }

    /**
     * Extracts a zip file to the specified directory.
     *
     * @param zipFile         the path to the zip file
     * @param outputDirectory the directory to extract the files to
     */
    private static void unzipFileToDirectory(Path zipFile, File outputDirectory) {
        try {
            InputStream studentZipStream = Files.newInputStream(zipFile);
            ZipFileExtractor.extractZipEntries(studentZipStream, outputDirectory.getAbsolutePath());
            Files.delete(zipFile);
        } catch (IOException e) {
            // Log or handle the exception as needed
        }
    }
}
