package com.gophers.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileCopier {
    private static final String TARGET_PACKAGE_HEADER = "package com.gophers.data;\n\n";
    private static final String TARGET_DIRECTORY = "src/main/java/com/gophers/data";

    public static void copyJavaFiles(String submissionDirectory) {
        Path sourceDir = Paths.get(submissionDirectory);
        Path targetDir = Paths.get(TARGET_DIRECTORY);
        try {
            prepareTargetDirectory(targetDir);
            copyFiles(sourceDir, targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void prepareTargetDirectory(Path targetDir) throws IOException {
        if (Files.exists(targetDir)) {
            clearDirectory(targetDir);
        } else {
            Files.createDirectories(targetDir);
        }
    }

    private static void clearDirectory(Path targetDir) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(targetDir)) {
            for (Path entry : stream) {
                Files.delete(entry);
            }
        }
    }

    private static void copyFiles(Path sourceDir, Path targetDir) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir, "*.java")) {
            for (Path sourceFile : stream) {
                copyFile(sourceFile, targetDir.resolve(sourceFile.getFileName()));
            }
        }
    }

    private static void copyFile(Path sourcePath, Path targetPath) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath.toFile()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(targetPath.toFile()))) {
            writer.write(TARGET_PACKAGE_HEADER);
            writer.newLine(); // Add a new line after the package header
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
