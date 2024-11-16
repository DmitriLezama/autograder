package com.gophers.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileExtractor {
    private static final int BUFFER_SIZE = 32768;

    public static void extractZipEntries(InputStream zipStream, String destDirectory) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            File destDir = new File(destDirectory);
            destDir.mkdirs();
            while ((entry = zipIn.getNextEntry()) != null) {
                File file = new File(destDir, entry.getName());
                if (entry.isDirectory())
                    file.mkdirs();
                else
                    extractFile(zipIn, file);
                zipIn.closeEntry();
            }
        }
    }

    private static void extractFile(ZipInputStream zipIn, File file) throws IOException {
        file.getParentFile().mkdirs();
        try (BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(file), BUFFER_SIZE)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = zipIn.read(buffer)) != -1)
                outStream.write(buffer, 0, bytesRead);
        }
    }
}
