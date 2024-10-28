package com.gophers.structures;

import org.junit.runner.Result;

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

}
