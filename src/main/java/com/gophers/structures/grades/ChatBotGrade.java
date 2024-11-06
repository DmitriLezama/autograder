package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.structures.domain.TestFeedback;

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
		super.testMarks.put("testMessageLimitField_Exists", 1);
		super.testMarks.put("testMessageLimitField_StaticPrivateInteger", 1);
		super.testMarks.put("testMessageLimitField_EqualsTen", 1);
		super.testMarks.put("testMessageNumberField_Exists", 1);
		super.testMarks.put("testMessageNumberField_PrivateStaticEqualsZero", 1);

		// Constructor Tests
		super.testMarks.put("testDefaultConstructor_ObjectNotNull", 1);
		super.testMarks.put("testDefaultConstructor_CorrectName", 1);
		super.testMarks.put("testDefaultConstructor_InitialResponseCount", 1);

		// Overloaded Constructor Tests
		super.testMarks.put("testOverloadedConstructor_ObjectNotNull", 1);
		super.testMarks.put("testOverloadedConstructor_ValidLLMCodes", 1);
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
		super.testMarks.put("testGenerateResponse_FieldIsPrivate", 1);
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
		super.testMarks.put("testPrompt_ResponseWhenPrompted", 1);
		super.testMarks.put("testPrompt_ResponseCountersAfterPrompt", 1);
		super.testMarks.put("testPrompt_LimitReached", 1);
		super.testMarks.put("testPrompt_TotalResponseCounterAndRemainingMessages", 1);
	}

	@Override
	protected void allocateFeedback() {
		// Field Existence Feedback
		super.testFeedback.put("testChatBotNameField",
				new TestFeedback("chatBotName field should exist and be a private string.", 98));
		super.testFeedback.put("testNumResponsesGeneratedField",
				new TestFeedback("numResponsesGenerated field should exist,be private and of type int.",
						97));
		super.testFeedback.put("testMessageLimitField_FieldExists",
				new TestFeedback("messageLimit field should exist in the ChatBot class.", 96));
		super.testFeedback.put("testMessageNumberField_FieldExists",
				new TestFeedback("messageNumber field should exist in the ChatBot class.", 95));

		// Field and Method Properties Feedback
		super.testFeedback.put("testMessageLimitField_FieldisStaticandPrivate",
				new TestFeedback("messageLimit field should be static and private.", 94));
		super.testFeedback.put("testGetTotalNumResponsesGenerated_IsStatic",
				new TestFeedback("getTotalNumResponsesGenerated should be static.", 93));
		super.testFeedback.put("testGetTotalNumMessagesRemaining_IsStatic",
				new TestFeedback("getTotalNumMessagesRemaining should be static.", 92));
		super.testFeedback.put("testLimitReached_IsStatic",
				new TestFeedback("limitReached should be static.", 91));
		super.testFeedback.put("testGenerateResponse_FieldisPrivate",
				new TestFeedback("generateResponse should be private.", 90));
		super.testFeedback.put("testMessageLimitField_FieldisIntegerandEquals10",
				new TestFeedback("messageLimit should be an integer with a value of 10.", 89));
		super.testFeedback.put("testMessageNumberField_FieldisStaticandZero",
				new TestFeedback("messageNumber should be static and initialized to zero.", 88));

		// Constructor Feedback
		super.testFeedback.put("testDefaultConstructor_ObjectNotNull",
				new TestFeedback("Default ChatBot constructor should create a non-null ChatBot object.",
						85));
		super.testFeedback.put("testOverloadedConstructor_ObjectisNotNull",
				new TestFeedback("Overloaded constructor should create a valid ChatBot instance.", 84));
		super.testFeedback.put("testOverloadedConstructor_ProducesCorrectBot",
				new TestFeedback(
						"Overloaded constructor should set correct ChatBot name based on LLM code.",
						83));
		super.testFeedback.put("testOverloadedConstructor_InvalidLLMCode",
				new TestFeedback("Invalid LLM codes should set the name to 'Chat        GPT-3.5'.", 82));
		super.testFeedback.put("testDefaultConstructor_CorrectChatBotName",
				new TestFeedback("Default ChatBot name should be 'ChatGPT-3.5' if no code is provided.",
						81));
		super.testFeedback.put("testDefaultConstructor_InitialResponseCount",
				new TestFeedback("Default constructor should set initial response count to 0.", 80));

		// Basic Method Behavior Feedback
		super.testFeedback.put("testGetChatBotName",
				new TestFeedback("getChatBotName should return the correct bot name for each instance.",
						79));
		super.testFeedback.put("testNumResponsesGenerated",
				new TestFeedback("numResponsesGenerated should increase with each prompt.", 78));
		super.testFeedback.put("testPrompt_ResponseWhenPrompted",
				new TestFeedback("prompt should produce a response that includes 'Response from'.",
						77));
		super.testFeedback.put("testGenerateResponse_ReturnsString",
				new TestFeedback("generateResponse should return a String.", 76));
		super.testFeedback.put("testToString_BotNameHeaderExists",
				new TestFeedback("toString should include 'ChatBot Name' header.", 75));
		super.testFeedback.put("testToString_NumMessagesUsedHeaderExists",
				new TestFeedback("toString should include 'Number Messages Used' header.", 74));
		super.testFeedback.put("testLimitReached_InitiallyFalse",
				new TestFeedback("limitReached should return false initially.", 73));
		super.testFeedback.put("testLimitReached_TrueWhenLimitReached",
				new TestFeedback("limitReached should return true when message limit is hit.", 72));

		// Complex Method Behavior Feedback
		super.testFeedback.put("testGenerateResponse_IncrementsCounters",
				new TestFeedback(
						"generateResponse should increment both instance and total response counters.",
						62));
		super.testFeedback.put("testGetTotalNumResponsesGenerated_ReturnsTotalMessages", new TestFeedback(
				"Total number of responses should include responses from all ChatBot instances.", 61));
		super.testFeedback.put("testGetTotalNumMessagesRemaining_ReturnsCorrectValue",
				new TestFeedback("Remaining messages count should reflect current usage.", 60));
		super.testFeedback.put("testGetTotalNumMessagesRemaining_ReachesZero",
				new TestFeedback("Remaining messages should eventually reach zero if limit is reached.",
						59));
		super.testFeedback.put("testGenerateResponse_ReturnsCorrectStringFormat",
				new TestFeedback(
						"Response format should match 'Response from [botName]: Message #[number]'",
						58));

		// Output Format Feedback
		super.testFeedback.put("testGenerateResponse_ContainsUniqueMessageNumber",
				new TestFeedback("Each response should contain a unique message number.", 33));
		super.testFeedback.put("testToString_CorrectBotName",
				new TestFeedback("toString should display the correct bot name.", 32));
		super.testFeedback.put("testToString_CorrectNumResponsesGenerated",
				new TestFeedback("toString should show the correct number of responses generated.",
						31));
		super.testFeedback.put("testPrompt_ResponseCountersAfterPrompt",
				new TestFeedback("Response counters should update correctly after each prompt.", 30));
		super.testFeedback.put("testPrompt3_LimitReach",
				new TestFeedback("System should handle reaching message limit.", 29));
		super.testFeedback.put("testPrompt_TotalResponseCounterAndRemainingMessages",
				new TestFeedback("Total response counter and remaining messages should be consistent.",
						28));
	}
}