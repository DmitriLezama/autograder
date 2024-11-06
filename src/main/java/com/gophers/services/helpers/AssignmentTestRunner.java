package com.gophers.services.helpers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.gophers.services.testCases.ChatBotGeneratorTest;
import com.gophers.services.testCases.ChatBotPlatformTest;
import com.gophers.services.testCases.ChatBotSimulationTest;
import com.gophers.services.testCases.ChatBotTest;
import com.gophers.services.testCases.ProgramTest;

public class AssignmentTestRunner {
    private final List<Class<?>> testClasses;

    public AssignmentTestRunner() {
        testClasses = new ArrayList<>();
        testClasses.add(ProgramTest.class);
        testClasses.add(ChatBotGeneratorTest.class);
        testClasses.add(ChatBotTest.class);
        testClasses.add(ChatBotPlatformTest.class);
        testClasses.add(ChatBotSimulationTest.class);
    }

    public Result runTest(Class<?> testname) {
        JUnitCore core = new JUnitCore();
        Result result = core.run(testname);
        return result;
    }

    public List<Result> runAllTest() {
        List<Result> results = new ArrayList<Result>();
        for (Class<?> testname : testClasses) {
            Result result = runTest(testname);
            results.add(result);
        }
        return results;
    }
}
