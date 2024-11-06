package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.domain.TestFeedback;

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
                super.testMarks.put("testAddChatBotIncrementsBotCount", 1);
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
                // ArrayList<ChatBot> feedback
                super.testFeedback.put("testBotsCollectionInitialized",
                                new TestFeedback("Bots collection should be initialized.", 88));
                super.testFeedback.put("testBotsCollectionIsPrivate",
                                new TestFeedback("Bots collection should be private.",
                                                87));

                // Constructor feedback
                super.testFeedback.put("testChatBotPlatformConstructor",
                                new TestFeedback("ChatBotPlatform was not properly initialized.", 79));
                super.testFeedback.put("testChatBotPlatformBotsCollectionInitialized",
                                new TestFeedback("Bots collection was not initialized as an ArrayList<ChatBot>.", 78));

                // addChatBot feedback
                super.testFeedback.put("testAddChatBotAddsFirstBot",
                                new TestFeedback("First ChatBot was not added as expected.", 71));
                super.testFeedback.put("testAddChatBotAddsSecondBot",
                                new TestFeedback("Second ChatBot was not added correctly.", 70));
                super.testFeedback.put("testAddChatBotIncrememtsBotCount",
                                new TestFeedback("Bots count did not increment correctly after adding ChatBots.", 56));
                super.testFeedback.put("testAddChatBotReachesLimit",
                                new TestFeedback("Limit check failed after reaching the message limit.", 55));
                super.testFeedback.put("testAddChatBotAfterLimitReached",
                                new TestFeedback("ChatBot was incorrectly added after limit was reached.", 54));

                // getChatBotList feedback
                super.testFeedback.put("testGetChatBotListContainsBotNumbers",
                                new TestFeedback("Bot numbers were not correctly included in the list output.", 70));
                super.testFeedback.put("testGetChatBotListContainsBotNames",
                                new TestFeedback("Bot names were missing in the list output.", 75));
                super.testFeedback.put("testGetChatBotListContainsMessageCountPerBot",
                                new TestFeedback("Message counts for each bot were not correctly displayed.", 74));
                super.testFeedback.put("testGetChatBotListContainsTotalMessagesUsed",
                                new TestFeedback("Total messages used were incorrectly calculated or not included.",
                                                73));
                super.testFeedback.put("testGetChatBotListContainsTotalMessagesRemaining",
                                new TestFeedback("Remaining messages were not correctly displayed.", 34));

                // interactWithBot feedback
                super.testFeedback.put("testInteractWithValidBot",
                                new TestFeedback("Valid interaction with bot failed.", 69));
                super.testFeedback.put("testInteractWithBotInvalidNegativeIndex",
                                new TestFeedback(
                                                "Interaction with negative index did not return the expected error message.",
                                                53));
                super.testFeedback.put("testInteractWithBotInvalidOutOfRangeIndex",
                                new TestFeedback(
                                                "Interaction with out-of-range index failed to provide the correct error message.",
                                                52));
                super.testFeedback.put("testInteractWithBotInvalidIndexEqualToSize",
                                new TestFeedback(
                                                "Interaction with an index equal to the list size did not handle the error correctly.",
                                                51));
                super.testFeedback.put("testInteractWithBotAfterLimitReached",
                                new TestFeedback(
                                                "Interaction after message limit reached did not return 'Daily Limit Reached'.",
                                                50));
        }
}
