package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotSimulationGrade extends GradeTemplate {
    private static final int totalMarks = 12;

    public ChatBotSimulationGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        // Start with "Hello World!" - 1 mark
        // good
        super.testMarks.put("testMainMethodStartsWithHelloWorld", 1);

        // Platform initialization - 1 mark
        // good
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

        // Final summary statistics - 2 marks
        super.testMarks.put("testFinalSummaryNotEmpty", 1);
        super.testMarks.put("testFinalSummaryStatistics", 1);
    }

}
