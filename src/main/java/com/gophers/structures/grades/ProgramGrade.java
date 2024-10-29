package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.TestFeedback;

public class ProgramGrade extends GradeTemplate {
    private static final int totalMarks = 15;

    public ProgramGrade(Result result) {
        super(result, totalMarks);
    }

    @Override
    protected void allocateWeightings() {
        super.testMarks.put("test_ChatBotCompiles", 1);
        super.testMarks.put("test_ChatBotPlatformCompiles", 1);
        super.testMarks.put("test_ChatBotGeneratorCompiles", 1);
        super.testMarks.put("test_ChatBotSimulationCompiles", 1);
        super.testMarks.put("testAllCompiles", 1);
        super.testMarks.put("testRuns", 10);
    }

    protected void allocateFeedback() {
        super.testFeedback.put("test_ChatBotCompiles", new TestFeedback("ChatBot does not compile", 85));
        super.testFeedback.put("test_ChatBotPlatformCompiles",
                new TestFeedback("ChatBotPlatform does not compile", 85));
        super.testFeedback.put("test_ChatBotGeneratorCompiles",
                new TestFeedback("ChatBotGenerator does not compile", 85));
        super.testFeedback.put("test_ChatBotSimulationCompiles",
                new TestFeedback("ChatBotSimulation does not compile", 85));
        super.testFeedback.put("testAllCompiles", new TestFeedback("The program does not compile", 95));
        super.testFeedback.put("testRuns", new TestFeedback("The program does not run", 100));
    }

}
