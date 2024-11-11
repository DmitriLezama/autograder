package com.gophers.performance;

import static org.junit.Assert.assertTrue;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Test;
import com.gophers.interfaces.Facade;
import com.gophers.services.handlers.GradingFacade;
import com.gophers.utilities.ExecutionTimer;
import com.gophers.utilities.PerformanceTestResult;

public class FacadePerformanceTest {
    private static final PrintStream originalOut = System.out;

    private final long performanceThreshold = 20000;

    @Test
    public void testFacadePerformance() throws Exception {
        Facade facade = new GradingFacade();
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    facade.processSubmissions("submissions.zip");
                },
                performanceThreshold,
                "Facade - Process Submissions");
        System.setOut(originalOut);
        assertTrue("Execution of processSubmissions took too long", result.isSuccess());
    }
}
