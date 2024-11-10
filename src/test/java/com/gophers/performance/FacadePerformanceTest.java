package com.gophers.performance;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import com.gophers.interfaces.Facade;
import com.gophers.services.handlers.GradingFacade;

public class FacadePerformanceTest {

    private static final PrintStream originalOut = System.out;
    private final long performanceThreshold = 15000;

    @Test
    public void testFacadePerformance() {
        System.setOut(new PrintStream(OutputStream.nullOutputStream())); // hiding output from processSubmissions
        Facade facade = new GradingFacade();
        long startTime = System.nanoTime();
        facade.processSubmissions(null);
        long endTime = System.nanoTime();
        System.setOut(originalOut);
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Execution Time for Facade: " + duration + " ms");
        Assert.assertTrue("Test execution took too long: " + duration + " ms", duration < performanceThreshold);
    }
}
