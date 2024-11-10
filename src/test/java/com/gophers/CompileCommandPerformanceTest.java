package com.gophers;

import com.gophers.interfaces.CompileCommand;
import com.gophers.services.helpers.JavaCompileCommand;
import org.junit.Assert;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompileCommandPerformanceTest {
    @Test
    public void testCompilePerformance() {
        String submissionDirectory = "src/main/resources/sample";
        Assert.assertTrue("Submission directory does not exist", Files.isDirectory(Paths.get(submissionDirectory)));
        CompileCommand compileCommand = new JavaCompileCommand(submissionDirectory);
        long startTime = System.nanoTime();
        boolean compilationSuccess = compileCommand.compile();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        Assert.assertTrue("Compilation failed", compilationSuccess);
        long performanceThreshold = 5000;
        Assert.assertTrue("Compilation took too long: " + duration + " ms", duration < performanceThreshold);
        System.out.println("Compilation time: " + duration + " ms");
    }
}
