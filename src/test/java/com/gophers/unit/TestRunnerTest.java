package com.gophers.unit;

import java.util.List;
import com.gophers.interfaces.TestRunner;
import com.gophers.services.handlers.AssignmentTestRunner;
import org.junit.Test;
import org.junit.Assert;

public class TestRunnerTest {

    @Test
    public void testRunAllTests() {
        TestRunner<?> testRunner = new AssignmentTestRunner();
        List<?> results = testRunner.runAllTests();
        for (var result : results)
            Assert.assertTrue(result != null);
    }
}
