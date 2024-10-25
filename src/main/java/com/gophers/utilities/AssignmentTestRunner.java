package com.gophers.utilities;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.gophers.services.ChatBotGeneratorTest;

public class AssignmentTestRunner {
    private final List<Class<?>> testClasses;

    public AssignmentTestRunner() {
        testClasses = new ArrayList<>();
        testClasses.add(ChatBotGeneratorTest.class);
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
