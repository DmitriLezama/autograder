package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotSimulationGrade extends GradeTemplate {
    private static final int totalMarks = 12;

    public ChatBotSimulationGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testMainMethodStartsWithHelloWorld", 1);
        super.testMarks.put("testChatBotPlatformInitialization", 1);
        super.testMarks.put("testAllChatBotModelsPresent", 1);
        super.testMarks.put("testInitializedPlatformHasMinimumBots", 1);
        super.testMarks.put("testChatBotsSectionPresent", 1);
        super.testMarks.put("testChatBotSummaryStatisticsPresent", 1);
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
