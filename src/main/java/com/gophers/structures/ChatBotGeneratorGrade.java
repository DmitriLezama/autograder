package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotGeneratorGrade extends GradeTemplate {
    private static final int totalMarks = 7;

    public ChatBotGeneratorGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testMethodIsStatic_generateChatBotLLM", 1);
        super.testMarks.put("testGenerateChatBotLLM_ReturnsLLaMaForCode1", 1);
        super.testMarks.put("testGenerateChatBotLLM_ReturnsMistral7BForCode2", 1);
        super.testMarks.put("testGenerateChatBotLLM_ReturnsBardForCode3", 1);
        super.testMarks.put("testGenerateChatBotLLM_ReturnsClaudeForCode4", 1);
        super.testMarks.put("testGenerateChatBotLLM_ReturnsSolarForCode5", 1);
        super.testMarks.put("testGenerateChatBotLLM_ReturnsChatGPT35ForInvalidCodes", 1);
    }

}
