package com.gophers.services.handlers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import com.gophers.interfaces.TestRunner;
import com.gophers.services.testCases.*;

/**
 * The AssignmentTestRunner class executes JUnit test cases related to student
 * submissions.
 */
public class AssignmentTestRunner implements TestRunner<Result> {
    private final List<Class<?>> testClasses;

    /**
     * Constructs an AssignmentTestRunner and initializes the list of test classes
     * to run.
     */
    public AssignmentTestRunner() {
        testClasses = List.of(
                ProgramTest.class,
                ChatBotGeneratorTest.class,
                ChatBotTest.class,
                ChatBotPlatformTest.class,
                ChatBotSimulationTest.class);
    }

    /**
     * Runs all predefined JUnit test cases and returns their results.
     *
     * @return a list of {@link Result} objects containing the results of each test
     */
    public List<Result> runAllTests() {
        List<Result> results = new ArrayList<>();
        for (Class<?> testname : testClasses) {
            Result result = runTest(testname);
            results.add(result);
        }
        return results;
    }

    /**
     * Executes a single JUnit test class and returns the result.
     *
     * @param testname the test class to execute
     * @return the {@link Result} of the test execution
     */
    private Result runTest(Class<?> testname) {
        JUnitCore core = new JUnitCore();
        Result result = core.run(testname);
        return result;
    }
}
