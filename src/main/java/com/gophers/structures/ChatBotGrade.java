package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotGrade extends GradeTemplate {
    private static final int totalMarks = 36;

    public ChatBotGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testChatBotNameField", 1);
        super.testMarks.put("testNumResponsesGeneratedField", 1);
        super.testMarks.put("testMessageLimitField", 3);
        super.testMarks.put("testMessageNumberField", 2);
        super.testMarks.put("testDefaultConstructor", 3);
        super.testMarks.put("testOverloadedConstructor", 3);
        super.testMarks.put("testGetChatBotName", 1);
        super.testMarks.put("testToString", 4);
        super.testMarks.put("testGenerateResponse", 5);
        super.testMarks.put("testResponseWhenPrompted", 4);
        super.testMarks.put("testResponseCountersAfterPrompt", 1);
        super.testMarks.put("testLimitReach", 3);
        super.testMarks.put("testTotalResponseCounterAfterPrompt", 2);
        super.testMarks.put("testRemainingMessagesAfterPrompt", 3);
    }
}
