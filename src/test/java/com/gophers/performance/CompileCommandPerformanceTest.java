package com.gophers.performance;

import static org.junit.Assert.assertTrue;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import com.gophers.interfaces.CompileCommand;
import com.gophers.services.helpers.JavaCompileCommand;
import com.gophers.utilities.*;

public class CompileCommandPerformanceTest {
    @Test
    public void testCompilePerformance() {
        String submissionDirectory = TestConstants.SAMPLE_PATH;
        assertTrue("Submission directory does not exist", Files.isDirectory(Paths.get(submissionDirectory)));
        CompileCommand compileCommand = new JavaCompileCommand(submissionDirectory);
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    compileCommand.compile();
                },
                TestConstants.MAX_ALLOWABLE_THRESHOLD_MS,
                "JavaCompileCommand - Compiler");
        assertTrue("Compilation took too long: " + result.getExecutionTime() + " ms", result.isSuccess());
    }
}
