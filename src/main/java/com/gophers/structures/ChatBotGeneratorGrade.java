package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotGeneratorGrade extends GradeTemplate {
    private static final int totalMarks = 7;

    public ChatBotGeneratorGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testGenerateChatBotLLMValidCodes", 3);
        super.testMarks.put("testGenerateChatBotLLMInvalidCodes", 4);
    }

}
