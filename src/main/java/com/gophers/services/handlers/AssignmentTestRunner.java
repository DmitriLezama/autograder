package com.gophers.services.handlers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import com.gophers.interfaces.TestRunner;
import com.gophers.services.testCases.ChatBotGeneratorTest;
import com.gophers.services.testCases.ChatBotPlatformTest;
import com.gophers.services.testCases.ChatBotSimulationTest;
import com.gophers.services.testCases.ChatBotTest;
import com.gophers.services.testCases.ProgramTest;

public class AssignmentTestRunner implements TestRunner {
    private final List<Class<?>> testClasses;

    public AssignmentTestRunner() {
        testClasses = List.of(
                ProgramTest.class,
                ChatBotGeneratorTest.class,
                ChatBotTest.class,
                ChatBotPlatformTest.class,
                ChatBotSimulationTest.class);
    }

    public List<Result> runAllTests() {
        List<Result> results = new ArrayList<Result>();
        for (Class<?> testname : testClasses) {
            Result result = runTest(testname);
            results.add(result);
        }
        return results;
    }

    private Result runTest(Class<?> testname) {
        JUnitCore core = new JUnitCore();
        Result result = core.run(testname);
        return result;
    }
}