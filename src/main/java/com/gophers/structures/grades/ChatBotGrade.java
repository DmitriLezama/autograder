package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.TestFeedback;

public class ChatBotGrade extends GradeTemplate {
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

    @Override
    protected void allocateFeedback() {
        // Field Tests Feedback
        super.testFeedback.put("testChatBotNameField", new TestFeedback("Ensure the ChatBot name field exists and is private.", 70));
        super.testFeedback.put("testNumResponsesGeneratedField", new TestFeedback("The numResponsesGenerated field must be private and of type int.", 70));
        super.testFeedback.put("testMessageLimitField_FieldExists", new TestFeedback("The messageLimit field should exist in the ChatBot class.", 60));
        super.testFeedback.put("testMessageLimitField_FieldisStaticandPrivate", new TestFeedback("The messageLimit field should be static and private.", 60));
        super.testFeedback.put("testMessageLimitField_FieldisIntegerandEquals10", new TestFeedback("Ensure messageLimit is an integer with a value of 10.", 80));
        super.testFeedback.put("testMessageNumberField_FieldExists", new TestFeedback("The messageNumber field should exist.", 60));
        super.testFeedback.put("testMessageNumberField_FieldisStaticandZero", new TestFeedback("Ensure messageNumber is static and initialized to zero.", 80));

        // Constructor Tests Feedback
        super.testFeedback.put("testDefaultConstructor_ObjectNotNull", new TestFeedback("Default constructor should create a non-null ChatBot object.", 80));
        super.testFeedback.put("testDefaultConstructor_CorrectChatBotName", new TestFeedback("Default ChatBot name should be 'ChatGPT-3.5' if no code is provided.", 70));
        super.testFeedback.put("testDefaultConstructor_InitialResponseCount", new TestFeedback("Default constructor should set initial response count to 0.", 70));

        // Overloaded Constructor Tests Feedback
        super.testFeedback.put("testOverloadedConstructor_ObjectisNotNull", new TestFeedback("Overloaded constructor should create a valid ChatBot instance.", 80));
        super.testFeedback.put("testOverloadedConstructor_ProducesCorrectBot", new TestFeedback("Overloaded constructor should set correct ChatBot name based on LLM code.", 80));
        super.testFeedback.put("testOverloadedConstructor_InvalidLLMCode", new TestFeedback("Invalid LLM codes should set the name to 'ChatGPT-3.5'.", 70));

        // Method Tests Feedback
        super.testFeedback.put("testGetChatBotName", new TestFeedback("getChatBotName should return the correct bot name for each instance.", 70));
        super.testFeedback.put("testNumResponsesGenerated", new TestFeedback("Ensure numResponsesGenerated increases with each prompt.", 80));
        super.testFeedback.put("testGetTotalNumResponsesGenerated_IsStatic", new TestFeedback("getTotalNumResponsesGenerated should be static.", 60));
        super.testFeedback.put("testGetTotalNumResponsesGenerated_ReturnsTotalMessages", new TestFeedback("Ensure total number of responses includes responses from all ChatBot instances.", 70));
        super.testFeedback.put("testGetTotalNumMessagesRemaining_IsStatic", new TestFeedback("getTotalNumMessagesRemaining should be static.", 60));
        super.testFeedback.put("testGetTotalNumMessagesRemaining_ReturnsCorrectValue", new TestFeedback("Ensure remaining messages count reflects current usage.", 70));
        super.testFeedback.put("testGetTotalNumMessagesRemaining_ReachesZero", new TestFeedback("Remaining messages should eventually reach zero if limit is reached.", 70));

        super.testFeedback.put("testLimitReached_IsStatic", new TestFeedback("limitReached should be a static method.", 60));
        super.testFeedback.put("testLimitReached_InitiallyFalse", new TestFeedback("Ensure limitReached returns false initially.", 60));
        super.testFeedback.put("testLimitReached_TrueWhenLimitReached", new TestFeedback("limitReached should return true when message limit is hit.", 80));

        // GenerateResponse Tests Feedback
        super.testFeedback.put("testGenerateResponse_FieldisPrivate", new TestFeedback("generateResponse should be a private method.", 60));
        super.testFeedback.put("testGenerateResponse_IncrementsCounters", new TestFeedback("generateResponse should increment both instance and total response counters.", 80));
        super.testFeedback.put("testGenerateResponse_ReturnsCorrectStringFormat", new TestFeedback("Response format should include message number and bot name.", 80));
        super.testFeedback.put("testGenerateResponse_ReturnsString", new TestFeedback("generateResponse should return a String.", 70));
        super.testFeedback.put("testGenerateResponse_ContainsUniqueMessageNumber", new TestFeedback("Each response should contain a unique message number.", 80));

        // ToString Tests Feedback
        super.testFeedback.put("testToString_BotNameHeaderExists", new TestFeedback("toString should include 'ChatBot Name' header.", 60));
        super.testFeedback.put("testToString_CorrectBotName", new TestFeedback("Ensure toString displays the correct bot name.", 70));
        super.testFeedback.put("testToString_NumMessagesUsedHeaderExists", new TestFeedback("toString should include 'Number Messages Used' header.", 60));
        super.testFeedback.put("testToString_CorrectNumResponsesGenerated", new TestFeedback("Ensure toString shows the correct number of responses generated.", 70));

        // Prompt Tests Feedback
        super.testFeedback.put("testPrompt1_ResponseWhenPrompted", new TestFeedback("prompt should produce a response that includes 'Response from'.", 70));
        super.testFeedback.put("testPrompt2_ResponseCountersAfterPrompt", new TestFeedback("Each prompt should increment the response counter.", 80));
        super.testFeedback.put("testPrompt3_LimitReach", new TestFeedback("After reaching message limit, prompt should return a limit-reached message.", 80));
        super.testFeedback.put("testPrompt4_TotalResponseCounterAndRemainingMessages", new TestFeedback("Total responses and remaining messages should be updated correctly after each prompt.", 80));
    }
}
