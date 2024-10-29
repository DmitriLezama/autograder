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
        super.testFeedback.put("testMainMethodStartsWithHelloWorld",
                new TestFeedback("Main method does not start with \"Hello World\"", 85));
        super.testFeedback.put("testChatBotPlatformInitialization",
                new TestFeedback("ChatBotPlatform does not initialize", 85));
        super.testFeedback.put("testAllChatBotModelsPresent",
                new TestFeedback("All ChatBot models are not present", 85));
    }

}
