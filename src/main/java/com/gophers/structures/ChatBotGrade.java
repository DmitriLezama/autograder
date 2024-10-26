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
        super.testMarks.put("test1ResponseWhenPrompted", 4);
        super.testMarks.put("test2ResponseCountersAfterPrompt", 1);
        super.testMarks.put("test3LimitReach", 3);
        super.testMarks.put("test4TotalResponseCounterAfterPrompt", 2);
        super.testMarks.put("test5RemainingMessagesAfterPrompt", 3);
    }
}
