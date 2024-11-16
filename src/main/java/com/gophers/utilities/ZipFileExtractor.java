package com.gophers.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Utility class for extracting files from a ZIP archive.
 */
public class ZipFileExtractor {
    private static final int BUFFER_SIZE = 32768; // Buffer size for file extraction

    /**
     * Extracts all entries from a ZIP input stream and saves them to a specified directory.
     *
     * @param zipStream     Input stream of the ZIP file to extract.
     * @param destDirectory Destination directory where files will be extracted.
     * @throws IOException  If an I/O error occurs during extraction.
     */
    public static void extractZipEntries(InputStream zipStream, String destDirectory) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            File destDir = new File(destDirectory);
            destDir.mkdirs();  // Create destination directory if it does not exist
            while ((entry = zipIn.getNextEntry()) != null) {
                File file = new File(destDir, entry.getName());
                if (entry.isDirectory())
                    file.mkdirs(); // Create directories if entry is a folder
                else
                    extractFile(zipIn, file); // Extract file entry
                zipIn.closeEntry();
            }
        }
    }

    /**
     * Extracts a single file from the ZIP input stream and saves it to the specified file location.
     *
     * @param zipIn ZIP input stream containing the file data.
     * @param file  File object representing the target file path.
     * @throws IOException If an I/O error occurs during file writing.
     */
    private static void extractFile(ZipInputStream zipIn, File file) throws IOException {
        file.getParentFile().mkdirs(); // Ensure parent directories are created
        try (BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(file), BUFFER_SIZE)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = zipIn.read(buffer)) != -1)
                outStream.write(buffer, 0, bytesRead); // Write data to file
        }
    }
}
