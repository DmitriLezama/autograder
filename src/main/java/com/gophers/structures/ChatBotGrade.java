package com.gophers.structures;

import org.junit.runner.Result;

public class ChatBotGrade extends GradeTemplate {
    /*
     * Has 1 too many test for the constructor
     * Add a limit reached test to ensure its static
     */
    private static final int totalMarks = 36;

    public ChatBotGrade(Result result) {
        super(result, totalMarks);
    }

    @Override
    protected void allocateWeightings() {
        // Field Tests
        super.testMarks.put("testChatBotNameField", 1);
        super.testMarks.put("testNumResponsesGeneratedField", 1);
        super.testMarks.put("testMessageLimitField_FieldExists", 1);
        super.testMarks.put("testMessageLimitField_FieldisStaticandPrivate", 1);
        super.testMarks.put("testMessageLimitField_FieldisIntegerandEquals10", 1);
        super.testMarks.put("testMessageNumberField_FieldExists", 1);
        super.testMarks.put("testMessageNumberField_FieldisStaticandZero", 1);

        // Constructor Tests
        super.testMarks.put("testDefaultConstructor_ObjectNotNull", 1);
        super.testMarks.put("testDefaultConstructor_CorrectChatBotName", 1);
        super.testMarks.put("testDefaultConstructor_InitialResponseCount", 1);

        // Overloaded Constructor Tests
        super.testMarks.put("testOverloadedConstructor_ObjectisNotNull", 1);
        super.testMarks.put("testOverloadedConstructor_ProducesCorrectBot", 1);
        super.testMarks.put("testOverloadedConstructor_InvalidLLMCode", 1);

        // Method Tests
        super.testMarks.put("testGetChatBotName", 1);
        super.testMarks.put("testNumResponsesGenerated", 1);
        super.testMarks.put("testGetTotalNumResponsesGenerated_IsStatic", 1);
        super.testMarks.put("testGetTotalNumResponsesGenerated_ReturnsTotalMessages", 1);
        super.testMarks.put("testGetTotalNumMessagesRemaining_IsStatic", 1);
        super.testMarks.put("testGetTotalNumMessagesRemaining_ReturnsCorrectValue", 1);
        super.testMarks.put("testGetTotalNumMessagesRemaining_ReachesZero", 1);

        super.testMarks.put("testLimitReached_IsStatic", 1);
        super.testMarks.put("testLimitReached_InitiallyFalse", 1);
        super.testMarks.put("testLimitReached_TrueWhenLimitReached", 1);

        // GenerateResponse Tests
        super.testMarks.put("testGenerateResponse_FieldisPrivate", 1);
        super.testMarks.put("testGenerateResponse_IncrementsCounters", 1);
        super.testMarks.put("testGenerateResponse_ReturnsCorrectStringFormat", 1);
        super.testMarks.put("testGenerateResponse_ReturnsString", 1);
        super.testMarks.put("testGenerateResponse_ContainsUniqueMessageNumber", 1);

        // ToString Tests
        super.testMarks.put("testToString_BotNameHeaderExists", 1);
        super.testMarks.put("testToString_CorrectBotName", 1);
        super.testMarks.put("testToString_NumMessagesUsedHeaderExists", 1);
        super.testMarks.put("testToString_CorrectNumResponsesGenerated", 1);

        // Prompt Tests
        super.testMarks.put("testPrompt1_ResponseWhenPrompted", 1);
        super.testMarks.put("testPrompt2_ResponseCountersAfterPrompt", 1);
        super.testMarks.put("testPrompt3_LimitReach", 1);
        super.testMarks.put("testPrompt4_TotalResponseCounterAndRemainingMessages", 1);

    }
}
