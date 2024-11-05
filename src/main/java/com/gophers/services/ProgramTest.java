package com.gophers.services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gophers.Submission;

public class ProgramTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void test_ChatBotCompiles() {
        assertTrue("ChatBot class should compile", doesClassCompile(Submission.getClass("ChatBot")));
    }

    @Test
    public void test_ChatBotPlatformCompiles() {
        assertTrue("ChatBotPlatform class should compile", doesClassCompile(Submission.getClass("ChatBotPlatform")));
    }

    @Test
    public void test_ChatBotGeneratorCompiles() {
        assertTrue("ChatBotGenerator class should compile", doesClassCompile(Submission.getClass("ChatBotGenerator")));
    }

    @Test
    public void test_ChatBotSimulationCompiles() {
        assertTrue("ChatBotSimulation class should compile",
                doesClassCompile(Submission.getClass("ChatBotSimulation")));
    }

    @Test
    public void testAllCompiles() {
        assertTrue("All required classes should compile",
                doesClassCompile(Submission.getClass("ChatBot")) &&
                        doesClassCompile(Submission.getClass("ChatBotPlatform")) &&
                        doesClassCompile(Submission.getClass("ChatBotGenerator")) &&
                        doesClassCompile(Submission.getClass("ChatBotSimulation")));
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testChatBotSimulationRuns() {
        try {
            Method mainMethod = Submission.getClass("ChatBotSimulation").getMethod("main", String[].class);
            String[] args = null; // Equivalent to null
            mainMethod.invoke(null, (Object) args); // Invoke main method
            assertTrue("ChatBotSimulation.main(null) should run without error", true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("ChatBotSimulation.main(null) threw an exception: " + e.getMessage());
        }
    }

    private static boolean doesClassCompile(Class<?> clazz) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null)
            throw new IllegalStateException("Java compiler not available. Ensure a JDK (not JRE) is used.");
        String baseDir = "src/main/java/";
        String classPath = baseDir + clazz.getName().replace('.', File.separatorChar) + ".java";
        File sourceFile = new File(classPath);

        if (!sourceFile.exists())
            throw new IllegalArgumentException("Source file not found for class: " + clazz.getName());

        int compilationResult = compiler.run(null, null, null, classPath);
        return compilationResult == 0;
    }
}
