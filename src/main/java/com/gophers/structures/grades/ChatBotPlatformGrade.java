package com.gophers.structures.grades;

import org.junit.runner.Result;

public class ChatBotPlatformGrade extends GradeTemplate {
    private static final int totalMarks = 20;

    public ChatBotPlatformGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        // ArrayList<ChatBot> - 2 marks
        /*
         * This test should test that is a a list/array list of ChatBot for one mark
         * And 1 mark for private
         */
        super.testMarks.put("testBotsCollection", 1);
        super.testMarks.put("testBotsCollectionEmptyInitially", 1);

        // Constructor - 2 marks
        /*
         * Should be 1 mark test the constructor and tht it works
         * Another mark test that the ArrayList is correctly initalized such as typeof
         * elements
         */
        super.testMarks.put("testChatBotPlatformConstructor", 1);
        super.testMarks.put("testChatBotPlatformBotsCollectionInitialized", 1);

        // addChatBot(int LLMcode) - 5 marks
        /*
         * Test limit reached for 1 mark
         * Testing adding chatbots properly before limit reached for 2marks
         * Test error handling at limit reached for 2marks
         */
        super.testMarks.put("testAddFirstChatBot", 1);
        super.testMarks.put("testAddSecondChatBot", 1);
        // super.testMarks.put("testAddThirdChatBot", 1);
        super.testMarks.put("testChatBotsCountAfterAdding", 1);
        super.testMarks.put("testLimitReachedAfterAddingChatBots", 1);
        super.testMarks.put("testAddChatBotAfterLimitReached", 1);

        // getChatBotList() - 6 marks
        /*
         * This good
         */
        super.testMarks.put("testGetChatBotListContainsBotNumbers", 1);
        super.testMarks.put("testGetChatBotListContainsBotNames", 1);
        super.testMarks.put("testGetChatBotListContainsMessageCountPerBot", 1);
        super.testMarks.put("testGetChatBotListContainsTotalMessagesUsed", 1);
        super.testMarks.put("testGetChatBotListContainsTotalMessagesRemaining", 2);

        // interactWithBot() - 5 marks
        /*
         * These test are good but you are comparing the Strings too strictly
         * allow more lenicent by testing pieces of the string rather than the full
         * string
         */
        super.testMarks.put("testInteractWithValidBot", 1);
        super.testMarks.put("testInteractWithBotInvalidNegativeIndex", 1);
        super.testMarks.put("testInteractWithBotInvalidOutOfRangeIndex(", 1);
        super.testMarks.put("testInteractWithBotInvalidIndexEqualToSize", 1);
        super.testMarks.put("testInteractWithBotAfterLimitReached", 1);
    }
}
