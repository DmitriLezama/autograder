package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.domain.TestFeedback;

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

    protected void allocateFeedback() {
        testFeedback.put("testMethodIsStatic_generateChatBotLLM",
                new TestFeedback("generateChatBotLLM should be static and accessible.", 7));
        testFeedback.put("testGenerateChatBotLLM_ReturnsLLaMaForCode1",
                new TestFeedback("generateChatBotLLM should return 'LLaMa' when the code is 1.", 5));
        testFeedback.put("testGenerateChatBotLLM_ReturnsMistral7BForCode2",
                new TestFeedback("generateChatBotLLM should return 'Mistral7B' when the code is 2.", 4));
        testFeedback.put("testGenerateChatBotLLM_ReturnsBardForCode3",
                new TestFeedback("generateChatBotLLM should return 'Bard' when the code is 3.", 3));
        testFeedback.put("testGenerateChatBotLLM_ReturnsClaudeForCode4",
                new TestFeedback("generateChatBotLLM should return 'Claude' when the code is 4.", 2));
        testFeedback.put("testGenerateChatBotLLM_ReturnsSolarForCode5",
                new TestFeedback("generateChatBotLLM should return 'Solar' when the code is 5.", 1));
        testFeedback.put("testGenerateChatBotLLM_ReturnsChatGPT35ForInvalidCodes",
                new TestFeedback("generateChatBotLLM should return 'ChatGPT-3.5' for any invalid codes.", 6));
    }
}
