package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotSimulationGrade extends GradeTemplate {
    private static final int totalMarks = 12;

    public ChatBotSimulationGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        // Start with "Hello World!" - 1 mark
        super.testMarks.put("testMainMethodStartsWithHelloWorld", 1);
    
        // Platform initialization - 1 mark
        super.testMarks.put("testChatBotPlatformInitialization", 1);
    
        // Adding ChatBots - 2 marks
        super.testMarks.put("testIndividualChatBotsAdded", 1); 
        super.testMarks.put("testAtLeastOneChatBotAdded", 1); 
    
        // Displaying ChatBot statistics - 2 marks
        super.testMarks.put("testChatBotsSectionPresent", 1); 
        super.testMarks.put("testChatBotSummaryStatisticsPresent", 1); 
    
        // Interacting with ChatBots - 4 marks
        super.testMarks.put("testChatBotHasInteractions", 1); 
        super.testMarks.put("testMessageFormatInInteractions", 1); 
        super.testMarks.put("testIncorrectBotNumberHandling", 1);
        super.testMarks.put("testExactNumberOfInteractions", 1); 
    
        // Final summary statistics - 2 marks
        super.testMarks.put("testFinalSummaryNotEmpty", 1); 
        super.testMarks.put("testFinalSummaryStatistics", 1); 
    }
    
}
