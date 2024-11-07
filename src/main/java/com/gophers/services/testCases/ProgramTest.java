package com.gophers.services.testCases;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import com.gophers.structures.domain.Submission;

public class ProgramTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testCompiles() {
        assertTrue("Submission should compile", Submission.compileAllClasses());
    }

    @Test
    public void testRuns() {
        try {
            Method mainMethod = Submission.getClass("ChatBotSimulation").getMethod("main", String[].class);
            String[] args = null; // Equivalent to null
            mainMethod.invoke(null, (Object) args); // Invoke main method
            assertTrue("ChatBotSimulation.main(null) should run without error", true);
        } catch (Exception e) {
            fail("ChatBotSimulation.main(null) should run without error");
        }
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }
}