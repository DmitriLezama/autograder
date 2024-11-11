package com.gophers.performance;

import static org.junit.Assert.assertTrue;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Test;
import com.gophers.interfaces.Facade;
import com.gophers.services.handlers.GradingFacade;
import com.gophers.utilities.*;

public class FacadePerformanceTest {
    private static final PrintStream originalOut = System.out;

    @Test
    public void testFacadePerformance() throws Exception {
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        Facade facade = new GradingFacade();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> {
                    facade.processSubmissions("submissions.zip");
                },
                TestConstants.MAX_ALLOWABLE_THRESHOLD_MS,
                "Facade - Process Submissions");
        System.setOut(originalOut);
        assertTrue("Execution of processSubmissions took too long", result.isSuccess());
    }
}
