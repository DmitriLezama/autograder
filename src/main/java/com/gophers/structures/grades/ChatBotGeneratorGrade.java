package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.TestFeedback;

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
                new TestFeedback("The method is suppose to be static.", 1));
        testFeedback.put("testGenerateChatBotLLM_ReturnsLLaMaForCode1",
                new TestFeedback("Check for correct return value for code 1.", 2));
        testFeedback.put("testGenerateChatBotLLM_ReturnsMistral7BForCode2",
                new TestFeedback("Verify the response for code 2 is Mistral 7B.", 2));
        testFeedback.put("testGenerateChatBotLLM_ReturnsBardForCode3",
                new TestFeedback("Ensure the output matches Bard for code 3.", 3));
        testFeedback.put("testGenerateChatBotLLM_ReturnsClaudeForCode4",
                new TestFeedback("Check Claude's return value for code 4.", 3));
        testFeedback.put("testGenerateChatBotLLM_ReturnsSolarForCode5",
                new TestFeedback("Confirm Solar response for code 5.", 4));
        testFeedback.put("testGenerateChatBotLLM_ReturnsChatGPT35ForInvalidCodes",
                new TestFeedback("Test the handling of invalid codes.", 5));
    }
}
