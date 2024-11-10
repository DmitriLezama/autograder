package com.gophers.unit;

import com.gophers.interfaces.CompileCommand;
import com.gophers.services.helpers.JavaCompileCommand;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompileCommandUnitTest {
    private static final String TEST_DIRECTORY = "testSubmissions";

    @BeforeClass
    public static void setup() {
        File directory = new File(TEST_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @After
    public void cleanup() {
        File directory = new File(TEST_DIRECTORY);
        for (File file : directory.listFiles()) {
            file.delete();
        }
    }

    @AfterClass
    public static void tearDown() {
        File directory = new File(TEST_DIRECTORY);
        directory.delete();
    }

    @Test
    public void testCompileWithValidJavaFile() throws IOException {
        File javaFile = new File(TEST_DIRECTORY + "/ValidClass.java");
        try (FileWriter writer = new FileWriter(javaFile)) {
            writer.write("public class ValidClass { public static void main(String[] args) {} }");
        }

        CompileCommand compiler = new JavaCompileCommand(TEST_DIRECTORY);
        boolean result = compiler.compile();

        assertTrue("Compilation should succeed for a valid Java file.", result);
    }

    @Test
    public void testCompileWithInvalidJavaFile() throws IOException {
        File javaFile = new File(TEST_DIRECTORY + "/InvalidClass.java");
        try (FileWriter writer = new FileWriter(javaFile)) {
            writer.write("public class InvalidClass { public static void main(String[] args) {"); 
        }

        CompileCommand compiler = new JavaCompileCommand(TEST_DIRECTORY);
        boolean result = compiler.compile();

        assertFalse("Compilation should fail for an invalid Java file.", result);
    }

    @Test
    public void testCompileWithNoJavaFiles() {
        CompileCommand compiler = new JavaCompileCommand(TEST_DIRECTORY);
        boolean result = compiler.compile();

        assertFalse("Compilation should fail when no Java files are present.", result);
    }

    @Test
    public void testCompileWithNonExistentDirectory() {
        CompileCommand compiler = new JavaCompileCommand("nonExistentDirectory");
        boolean result = compiler.compile();

        assertFalse("Compilation should fail for a non-existent directory.", result);
    }
}

