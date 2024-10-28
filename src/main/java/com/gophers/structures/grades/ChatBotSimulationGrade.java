package com.gophers.structures.grades;

import org.junit.runner.Result;

public class ChatBotSimulationGrade extends GradeTemplate {
    private static final int totalMarks = 12;

    public ChatBotSimulationGrade(Result result) {
        super(result, totalMarks);
    }

    protected void allocateWeightings() {
        super.testMarks.put("testMainMethodStartsWithHelloWorld", 1);
        super.testMarks.put("testChatBotPlatformInitialization", 1);
        /*
         * tbh this just hadda lockin ICL need to test the results/output accurately and
         * disseminate marks
         */
        super.testMarks.put("testChatBotsAdded", 2);
        super.testMarks.put("testChatBotInteraction", 2);
        super.testMarks.put("testChatBotSummaryStatistics", 4);
        super.testMarks.put("testChatBotFinalSummary", 2);
    }
}
