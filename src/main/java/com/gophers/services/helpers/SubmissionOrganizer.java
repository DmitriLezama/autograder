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

public class SubmissionOrganizer {
    public static List<String> organizeSubmissions(String outputDirectory) throws IOException {
        List<String> studentDirectories = new ArrayList<String>();
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

    private static void unzipFileToDirectory(Path zipFile, File outputDirectory) {
        try {
            InputStream studentZipStream = Files.newInputStream(zipFile);
            ZipFileExtractor.extractZipEntries(studentZipStream, outputDirectory.getAbsolutePath());
            Files.delete(zipFile);
        } catch (IOException e) {
            // System.err.println("File could not be unzipped");
        }
    }
}
