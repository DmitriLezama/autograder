package com.gophers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileExtractor {

    public void extractZipEntries(InputStream zipStream, String destDirectory) throws IOException {
        ZipInputStream zipIn = new ZipInputStream(zipStream);
        ZipEntry entry;
        while ((entry = zipIn.getNextEntry()) != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (entry.isDirectory())
                new File(filePath).mkdirs();
            else
                extractFile(zipIn, filePath);
            zipIn.closeEntry();
        }
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = zipIn.read(buffer)) != -1)
            stream.write(buffer, 0, bytesRead);
        stream.close();
    }
}
