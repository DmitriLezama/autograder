package com.gophers.performance;

import com.gophers.interfaces.CompileCommand;
import com.gophers.services.helpers.JavaCompileCommand;
import org.junit.Assert;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;


public class CompileCommandPerformanceTest {
    private long performanceThreshold = 10000;
    @Test
    public void testCompilePerformance() {
        String submissionDirectory = "src/main/resources/sample";
        Assert.assertTrue("Submission directory does not exist", Files.isDirectory(Paths.get(submissionDirectory)));
        CompileCommand compileCommand = new JavaCompileCommand(submissionDirectory);
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
            () -> {
                compileCommand.compile();
            },
            performanceThreshold,
            "JavaCompileCommand - Compiler"
        );
        Assert.assertTrue("Compilation took too long: " + result.getExecutionTime() + " ms", result.isSuccess());
    }
}
