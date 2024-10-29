package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.TestFeedback;

public class ChatBotPlatformGrade extends GradeTemplate {
    private static final int totalMarks = 20;

    public ChatBotPlatformGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        // ArrayList<ChatBot> - 2 marks
        /*
         * 1 mark for confirming that the bots collection is an ArrayList of ChatBot.
         * 1 mark for confirming that the bots collection is private.
         */
        super.testMarks.put("testBotsCollectionInitialized", 1);
        super.testMarks.put("testBotsCollectionIsPrivate", 1);

        // Constructor - 2 marks
        /*
         * 1 mark for testing that the constructor initializes ChatBotPlatform
         * correctly.
         * 1 mark for testing that the ArrayList of ChatBots is properly initialized.
         */
        super.testMarks.put("testChatBotPlatformConstructor", 1);
        super.testMarks.put("testChatBotPlatformBotsCollectionInitialized", 1);

        // addChatBot(int LLMcode) - 5 marks
        /*
         * 1 mark for testing that adding chatbots respects the limit.
         * 2 marks for correctly adding chatbots before reaching the limit.
         * 2 marks for error handling when the limit is reached.
         */
        super.testMarks.put("testAddChatBotAddsFirstBot", 1);
        super.testMarks.put("testAddChatBotAddsSecondBot", 1);
        super.testMarks.put("testAddChatBotIncrememtsBotCount", 1);
        super.testMarks.put("testAddChatBotReachesLimit", 1);
        super.testMarks.put("testAddChatBotAfterLimitReached", 1);

        // getChatBotList() - 6 marks
        /*
         * 1 mark each for containing bot numbers, bot names, and message counts.
         * 1 mark for verifying the total messages used.
         * 2 marks for verifying the remaining messages.
         */
        super.testMarks.put("testGetChatBotListContainsBotNumbers", 1);
        super.testMarks.put("testGetChatBotListContainsBotNames", 1);
        super.testMarks.put("testGetChatBotListContainsMessageCountPerBot", 1);
        super.testMarks.put("testGetChatBotListContainsTotalMessagesUsed", 1);
        super.testMarks.put("testGetChatBotListContainsTotalMessagesRemaining", 2);

        // interactWithBot() - 5 marks
        /*
         * Tests should be less strict on full string matching and instead test for key
         * parts.
         * 1 mark for valid interaction.
         * 1 mark for invalid negative index handling.
         * 1 mark for out-of-range index handling.
         * 1 mark for index equal to size handling.
         * 1 mark for interaction after the limit is reached.
         */
        super.testMarks.put("testInteractWithValidBot", 1);
        super.testMarks.put("testInteractWithBotInvalidNegativeIndex", 1);
        super.testMarks.put("testInteractWithBotInvalidOutOfRangeIndex", 1);
        super.testMarks.put("testInteractWithBotInvalidIndexEqualToSize", 1);
        super.testMarks.put("testInteractWithBotAfterLimitReached", 1);
    }

    @Override
    protected void allocateFeedback() {
        super.testFeedback.put("testChatBotPlatformConstructor",
                new TestFeedback("ChatBotPlatform construct is incorrect", 85));
    }
}
