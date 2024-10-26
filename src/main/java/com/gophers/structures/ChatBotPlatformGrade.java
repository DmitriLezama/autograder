package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotPlatformGrade extends GradeTemplate {
    private static final int totalMarks = 20;

    public ChatBotPlatformGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testBotsCollection", 2);
        super.testMarks.put("testConstructor", 2);

        super.testMarks.put("testAddChatBot", 3);
        super.testMarks.put("testAddChatBotLimitReached", 2);

        super.testMarks.put("testGetChatBotList", 3);
        super.testMarks.put("testGetChatBotListEmpty", 3);

        super.testMarks.put("testInteractWithBotValidNumber", 1);
        super.testMarks.put("testInteractWithBotInvalidNumber", 2);
        super.testMarks.put("testInteractWithBotLimitReached", 2);
    }
}
