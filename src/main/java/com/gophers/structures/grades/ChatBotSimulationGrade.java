package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.TestFeedback;

public class ChatBotSimulationGrade extends GradeTemplate {
    private static final int totalMarks = 12;

    public ChatBotSimulationGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testMainMethodStartsWithHelloWorld", 1);
        super.testMarks.put("testChatBotPlatformInitialization", 1);

        // Adding ChatBots - 2 marks
        /*
         * need better names
         */
        super.testMarks.put("testAllChatBotModelsPresent", 2);

        // Displaying ChatBot statistics - 2 marks
        /*
         * Need more rigour
         */
        super.testMarks.put("testChatBotsSectionPresent", 1);
        super.testMarks.put("testInitialSummaryStatistics", 1);

        // Interacting with ChatBots - 4 marks
        /*
         * good justneeds better better names
         */
        super.testMarks.put("testAtLeastOneInteractionOccurred", 1);
        super.testMarks.put("testMessageNumberFormatInBotResponses", 1);
        super.testMarks.put("testInvalidBotNumberResponsePresent", 1);
        super.testMarks.put("testSimulationPerformsExactlyFifteenInteractions", 1);
        super.testMarks.put("testFinalSummaryNotEmpty", 1);
        super.testMarks.put("testFinalSummaryStatistics", 1);
    }

    @Override
    protected void allocateFeedback() {
        // Feedback for each test case with detailed explanations.

        // Requirement 1 feedback
        super.testFeedback.put("testMainMethodStartsWithHelloWorld",
                new TestFeedback("Main method output should start with 'Hello World!'", 85));

        // Requirement 2 feedback
        super.testFeedback.put("testChatBotPlatformInitialization",
                new TestFeedback("Expected initialization of ChatBotPlatform was not found.", 85));

        // Requirement 3 feedback
        super.testFeedback.put("testAllChatBotModelsPresent",
                new TestFeedback("Not all ChatBot models were listed in the output (LLaMa, Mistral7B, Bard, Claude, Solar, ChatGPT-3.5)", 85));

        // Requirement 4 feedback
        super.testFeedback.put("testChatBotsSectionPresent",
                new TestFeedback("The 'Your ChatBots' section was missing.", 85));
        super.testFeedback.put("testInitialSummaryStatistics",
                new TestFeedback("Initial summary statistics for 'Total Messages Used: 0' and 'Total Messages Remaining: 10' were missing or incorrect.", 85));

         // Requirement 5 feedback
         super.testFeedback.put("testAtLeastOneInteractionOccurred",
         new TestFeedback("Expected at least one interaction with a ChatBot, but none were found.", 85));
        super.testFeedback.put("testMessageNumberFormatInBotResponses",
         new TestFeedback("Each interaction should start with '(Message#)'", 85));
        super.testFeedback.put("testInvalidBotNumberResponsePresent",
         new TestFeedback("Expected 'Incorrect Bot Number' message was missing.", 85));
        super.testFeedback.put("testSimulationPerformsExactlyFifteenInteractions",
         new TestFeedback("The simulation should interact exactly 15 times with ChatBots.", 85));
        
         // Requirement 6 feedback
        super.testFeedback.put("testFinalSummaryNotEmpty",
                new TestFeedback("The final summary section should contain summary information and not be empty.", 85));
        super.testFeedback.put("testFinalSummaryStatistics",
                new TestFeedback("Final summary statistics were missing or incorrect. Ensure 'Total Messages Used' and 'Total Messages Remaining' reflect the correct values.", 85));
    }

}