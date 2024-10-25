package com.gophers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class SubmissionManager {
    private static final String OUTPUT_DIRECTORY = "StudentSubmissions";
    private final ZipFileExtractor extractor;

    public SubmissionManager(ZipFileExtractor extractor) {
        this.extractor = extractor;
    }

    public List<String> extractSubmissions(String submissionsZipPath) {
        List<String> studentDirectories = new ArrayList<String>();
        createOutputDirectory();

        try (InputStream zipStream = getResourceInputStream(submissionsZipPath)) {
            validateInputStream(zipStream, submissionsZipPath);
            extractor.extractZipEntries(zipStream, OUTPUT_DIRECTORY);
            studentDirectories = organizeStudentSubmissions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentDirectories;
    }

    private void createOutputDirectory() {
        new File(OUTPUT_DIRECTORY).mkdir();
    }

    private InputStream getResourceInputStream(String resourceFileName) {
        ClassLoader classLoader = App.class.getClassLoader();
        return classLoader.getResourceAsStream(resourceFileName);
    }

    private void validateInputStream(InputStream zipStream, String resourceFileName) throws IOException {
        if (zipStream == null)
            throw new IOException("Resource not found: " + resourceFileName);
    }

    private List<String> organizeStudentSubmissions() throws IOException {
        List<String> studentDirectories = new ArrayList<String>();

        try (Stream<Path> paths = Files.list(Paths.get(OUTPUT_DIRECTORY))) {
            paths.filter(Files::isRegularFile)
                    .forEach(zipFile -> {
                        String studentDirName = zipFile.getFileName().toString().replace(".zip", "");
                        File studentDir = new File(OUTPUT_DIRECTORY, studentDirName);
                        studentDir.mkdir();
                        try {
                            InputStream studentZipStream = Files.newInputStream(zipFile);
                            extractor.extractZipEntries(studentZipStream, studentDir.getAbsolutePath());
                            Files.delete(zipFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        studentDirectories.add(studentDir.getAbsolutePath());
                    });
        }
        return studentDirectories;
    }
}
