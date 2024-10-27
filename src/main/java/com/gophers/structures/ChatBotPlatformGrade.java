package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotPlatformGrade extends GradeTemplate {
    private static final int totalMarks = 20;

    public ChatBotPlatformGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testBotsCollection", 1);
        super.testMarks.put("testBotsCollectionEmptyInitially", 1);

        super.testMarks.put("testChatBotPlatformConstructor", 1);
        super.testMarks.put("testChatBotPlatformBotsCollectionInitialized", 1);

        super.testMarks.put("testAddFirstChatBot", 1);
        super.testMarks.put("testAddSecondChatBot", 1);
        super.testMarks.put("testAddThirdChatBot", 1);
        super.testMarks.put("testChatBotsCountAfterAdding", 1);

        super.testMarks.put("testLimitReachedAfterAddingChatBots", 1);
        super.testMarks.put("testAddChatBotAfterLimitReached", 1);

        super.testMarks.put("testGetChatBotListContainsBotNumbers", 1);
        super.testMarks.put("testGetChatBotListContainsBotNames", 1);
        super.testMarks.put("testGetChatBotListContainsMessageCountPerBot", 1);
        super.testMarks.put("testGetChatBotListContainsTotalMessagesUsedAndRemaining", 2);

        super.testMarks.put("testInteractWithValidBot", 1);

        super.testMarks.put("testInteractWithBotInvalidNegativeIndex", 1);
        super.testMarks.put("testInteractWithBotInvalidOutOfRangeIndex(", 1);
        super.testMarks.put("testInteractWithBotInvalidIndexEqualToSize", 1);

        super.testMarks.put("testInteractWithBotAfterLimitReached", 1);
    }
}
