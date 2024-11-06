package com.gophers.services.helpers;

import com.gophers.structures.domain.TestFeedback;
import java.util.HashMap;
import java.util.Map;

public class GradeConfigLoader {

	private static final Map<String, Map<String, Integer>> weightings = new HashMap<>();
	private static final Map<String, Map<String, TestFeedback>> feedback = new HashMap<>();

	static {
		initializeProgramTest();
		initializeChatBotGeneratorConfigTest();
		initializeChatBotConfigTest();
		initializeChatBotSimulationConfigTest();
		initializeChatBotPlatformConfigTest();
	}

	private static void initializeProgramTest() {
		Map<String, Integer> programMarks = new HashMap<>();
		programMarks.put("testCompiles", 5);
		programMarks.put("testRuns", 10);

		Map<String, TestFeedback> programFeedback = new HashMap<>();
		programFeedback.put("testCompiles", new TestFeedback("The program does not compile", 99));
		programFeedback.put("testRuns", new TestFeedback("The program does not run", 100));
		weightings.put("ChatBotGeneratorGrade", programMarks);
		feedback.put("ChatBotGeneratorGrade", programFeedback);
	}

	private static void initializeChatBotGeneratorConfigTest() {
		Map<String, Integer> generatorMarks = new HashMap<>();
		generatorMarks.put("testMethodIsStatic_generateChatBotLLM", 1);
		generatorMarks.put("testGenerateChatBotLLM_ReturnsLLaMaForCode1", 1);
		generatorMarks.put("testGenerateChatBotLLM_ReturnsMistral7BForCode2", 1);
		generatorMarks.put("testGenerateChatBotLLM_ReturnsBardForCode3", 1);
		generatorMarks.put("testGenerateChatBotLLM_ReturnsClaudeForCode4", 1);
		generatorMarks.put("testGenerateChatBotLLM_ReturnsSolarForCode5", 1);
		generatorMarks.put("testGenerateChatBotLLM_ReturnsChatGPT35ForInvalidCodes", 1);

		Map<String, TestFeedback> generatorFeedback = new HashMap<>();
		generatorFeedback.put("testMethodIsStatic_generateChatBotLLM",
				new TestFeedback("generateChatBotLLM should be static and accessible.", 86));
		generatorFeedback.put("testGenerateChatBotLLM_ReturnsLLaMaForCode1",
				new TestFeedback("generateChatBotLLM should return 'LLaMa' when the code is 1.", 72));
		generatorFeedback.put("testGenerateChatBotLLM_ReturnsMistral7BForCode2",
				new TestFeedback("generateChatBotLLM should return 'Mistral7B' when the code is 2.", 71));
		generatorFeedback.put("testGenerateChatBotLLM_ReturnsBardForCode3",
				new TestFeedback("generateChatBotLLM should return 'Bard' when the code is 3.", 70));
		generatorFeedback.put("testGenerateChatBotLLM_ReturnsClaudeForCode4",
				new TestFeedback("generateChatBotLLM should return 'Claude' when the code is 4.", 69));
		generatorFeedback.put("testGenerateChatBotLLM_ReturnsSolarForCode5",
				new TestFeedback("generateChatBotLLM should return 'Solar' when the code is 5.", 68));
		generatorFeedback.put("testGenerateChatBotLLM_ReturnsChatGPT35ForInvalidCodes",
				new TestFeedback("generateChatBotLLM should return 'ChatGPT-3.5' for any invalid codes.", 67));
		weightings.put("ChatBotGeneratorGrade", generatorMarks);
		feedback.put("ChatBotGeneratorGrade", generatorFeedback);
	}

	private static void initializeChatBotConfigTest() {
		Map<String, Integer> chatBotMarks = new HashMap<>();
		chatBotMarks.put("testChatBotNameField", 1);
		chatBotMarks.put("testNumResponsesGeneratedField", 1);
		chatBotMarks.put("testMessageLimitField_Exists", 1);
		chatBotMarks.put("testMessageLimitField_StaticPrivateInteger", 1);
		chatBotMarks.put("testMessageLimitField_EqualsTen", 1);
		chatBotMarks.put("testMessageNumberField_Exists", 1);
		chatBotMarks.put("testMessageNumberField_PrivateStaticEqualsZero", 1);
		chatBotMarks.put("testDefaultConstructor_ObjectNotNull", 1);
		chatBotMarks.put("testDefaultConstructor_CorrectName", 1);
		chatBotMarks.put("testDefaultConstructor_InitialResponseCount", 1);
		chatBotMarks.put("testOverloadedConstructor_ObjectNotNull", 1);
		chatBotMarks.put("testOverloadedConstructor_ValidLLMCodes", 1);
		chatBotMarks.put("testOverloadedConstructor_InvalidLLMCode", 1);
		chatBotMarks.put("testGetChatBotName", 1);
		chatBotMarks.put("testNumResponsesGenerated", 1);
		chatBotMarks.put("testGetTotalNumResponsesGenerated_IsStatic", 1);
		chatBotMarks.put("testGetTotalNumResponsesGenerated_ReturnsTotalMessages", 1);
		chatBotMarks.put("testGetTotalNumMessagesRemaining_IsStatic", 1);
		chatBotMarks.put("testGetTotalNumMessagesRemaining_ReturnsCorrectValue", 1);
		chatBotMarks.put("testGetTotalNumMessagesRemaining_ReachesZero", 1);
		chatBotMarks.put("testLimitReached_IsStatic", 1);
		chatBotMarks.put("testLimitReached_InitiallyFalse", 1);
		chatBotMarks.put("testLimitReached_TrueWhenLimitReached", 1);

		// GenerateResponse Tests
		chatBotMarks.put("testGenerateResponse_FieldIsPrivate", 1);
		chatBotMarks.put("testGenerateResponse_IncrementsCounters", 1);
		chatBotMarks.put("testGenerateResponse_ReturnsCorrectStringFormat", 1);
		chatBotMarks.put("testGenerateResponse_ReturnsString", 1);
		chatBotMarks.put("testGenerateResponse_ContainsUniqueMessageNumber", 1);

		// ToString Tests
		chatBotMarks.put("testToString_BotNameHeaderExists", 1);
		chatBotMarks.put("testToString_CorrectBotName", 1);
		chatBotMarks.put("testToString_NumMessagesUsedHeaderExists", 1);
		chatBotMarks.put("testToString_CorrectNumResponsesGenerated", 1);

		// Prompt Tests
		chatBotMarks.put("testPrompt_ResponseWhenPrompted", 1);
		chatBotMarks.put("testPrompt_ResponseCountersAfterPrompt", 1);
		chatBotMarks.put("testPrompt_LimitReached", 1);
		chatBotMarks.put("testPrompt_TotalResponseCounterAndRemainingMessages", 1);

		Map<String, TestFeedback> chatBotFeedback = new HashMap<>();
		chatBotFeedback.put("testChatBotNameField",
				new TestFeedback("chatBotName field should exist and be a private string.", 98));
		chatBotFeedback.put("testNumResponsesGeneratedField",
				new TestFeedback("numResponsesGenerated field should exist,be private and of type int.",
						97));
		chatBotFeedback.put("testMessageLimitField_FieldExists",
				new TestFeedback("messageLimit field should exist in the ChatBot class.", 96));
		chatBotFeedback.put("testMessageNumberField_FieldExists",
				new TestFeedback("messageNumber field should exist in the ChatBot class.", 95));

		// Field and Method Properties Feedback
		chatBotFeedback.put("testMessageLimitField_FieldisStaticandPrivate",
				new TestFeedback("messageLimit field should be static and private.", 94));
		chatBotFeedback.put("testGetTotalNumResponsesGenerated_IsStatic",
				new TestFeedback("getTotalNumResponsesGenerated should be static.", 93));
		chatBotFeedback.put("testGetTotalNumMessagesRemaining_IsStatic",
				new TestFeedback("getTotalNumMessagesRemaining should be static.", 92));
		chatBotFeedback.put("testLimitReached_IsStatic",
				new TestFeedback("limitReached should be static.", 91));
		chatBotFeedback.put("testGenerateResponse_FieldisPrivate",
				new TestFeedback("generateResponse should be private.", 90));
		chatBotFeedback.put("testMessageLimitField_FieldisIntegerandEquals10",
				new TestFeedback("messageLimit should be an integer with a value of 10.", 89));
		chatBotFeedback.put("testMessageNumberField_FieldisStaticandZero",
				new TestFeedback("messageNumber should be static and initialized to zero.", 88));

		// Constructor Feedback
		chatBotFeedback.put("testDefaultConstructor_ObjectNotNull",
				new TestFeedback("Default ChatBot constructor should create a non-null ChatBot object.",
						85));
		chatBotFeedback.put("testOverloadedConstructor_ObjectisNotNull",
				new TestFeedback("Overloaded constructor should create a valid ChatBot instance.", 84));
		chatBotFeedback.put("testOverloadedConstructor_ProducesCorrectBot",
				new TestFeedback(
						"Overloaded constructor should set correct ChatBot name based on LLM code.",
						83));
		chatBotFeedback.put("testOverloadedConstructor_InvalidLLMCode",
				new TestFeedback("Invalid LLM codes should set the name to 'Chat        GPT-3.5'.", 82));
		chatBotFeedback.put("testDefaultConstructor_CorrectChatBotName",
				new TestFeedback("Default ChatBot name should be 'ChatGPT-3.5' if no code is provided.",
						81));
		chatBotFeedback.put("testDefaultConstructor_InitialResponseCount",
				new TestFeedback("Default constructor should set initial response count to 0.", 80));

		// Basic Method Behavior Feedback
		chatBotFeedback.put("testGetChatBotName",
				new TestFeedback("getChatBotName should return the correct bot name for each instance.",
						79));
		chatBotFeedback.put("testNumResponsesGenerated",
				new TestFeedback("numResponsesGenerated should increase with each prompt.", 78));
		chatBotFeedback.put("testPrompt_ResponseWhenPrompted",
				new TestFeedback("prompt should produce a response that includes 'Response from'.",
						77));
		chatBotFeedback.put("testGenerateResponse_ReturnsString",
				new TestFeedback("generateResponse should return a String.", 76));
		chatBotFeedback.put("testToString_BotNameHeaderExists",
				new TestFeedback("toString should include 'ChatBot Name' header.", 75));
		chatBotFeedback.put("testToString_NumMessagesUsedHeaderExists",
				new TestFeedback("toString should include 'Number Messages Used' header.", 74));
		chatBotFeedback.put("testLimitReached_InitiallyFalse",
				new TestFeedback("limitReached should return false initially.", 73));
		chatBotFeedback.put("testLimitReached_TrueWhenLimitReached",
				new TestFeedback("limitReached should return true when message limit is hit.", 72));

		// Complex Method Behavior Feedback
		chatBotFeedback.put("testGenerateResponse_IncrementsCounters",
				new TestFeedback(
						"generateResponse should increment both instance and total response counters.",
						62));
		chatBotFeedback.put("testGetTotalNumResponsesGenerated_ReturnsTotalMessages", new TestFeedback(
				"Total number of responses should include responses from all ChatBot instances.", 61));
		chatBotFeedback.put("testGetTotalNumMessagesRemaining_ReturnsCorrectValue",
				new TestFeedback("Remaining messages count should reflect current usage.", 60));
		chatBotFeedback.put("testGetTotalNumMessagesRemaining_ReachesZero",
				new TestFeedback("Remaining messages should eventually reach zero if limit is reached.",
						59));
		chatBotFeedback.put("testGenerateResponse_ReturnsCorrectStringFormat",
				new TestFeedback(
						"Response format should match 'Response from [botName]: Message #[number]'",
						58));

		// Output Format Feedback
		chatBotFeedback.put("testGenerateResponse_ContainsUniqueMessageNumber",
				new TestFeedback("Each response should contain a unique message number.", 33));
		chatBotFeedback.put("testToString_CorrectBotName",
				new TestFeedback("toString should display the correct bot name.", 32));
		chatBotFeedback.put("testToString_CorrectNumResponsesGenerated",
				new TestFeedback("toString should show the correct number of responses generated.",
						31));
		chatBotFeedback.put("testPrompt_ResponseCountersAfterPrompt",
				new TestFeedback("Response counters should update correctly after each prompt.", 30));
		chatBotFeedback.put("testPrompt3_LimitReach",
				new TestFeedback("System should handle reaching message limit.", 29));
		chatBotFeedback.put("testPrompt_TotalResponseCounterAndRemainingMessages",
				new TestFeedback("Total response counter and remaining messages should be consistent.",
						28));
		weightings.put("ChatBotGrade", chatBotMarks);
		feedback.put("ChatBotGrade", chatBotFeedback);
	}

	private static void initializeChatBotPlatformConfigTest() {
		Map<String, Integer> platformMarks = new HashMap<>();
		platformMarks.put("testBotsCollectionInitialized", 1);
		platformMarks.put("testBotsCollectionIsPrivate", 1);
		platformMarks.put("testChatBotPlatformConstructor", 1);
		platformMarks.put("testChatBotPlatformBotsCollectionInitialized", 1);
		platformMarks.put("testAddChatBotAddsFirstBot", 1);
		platformMarks.put("testAddChatBotAddsSecondBot", 1);
		platformMarks.put("testAddChatBotIncrementsBotCount", 1);
		platformMarks.put("testAddChatBotReachesLimit", 1);
		platformMarks.put("testAddChatBotAfterLimitReached", 1);
		platformMarks.put("testGetChatBotListContainsBotNumbers", 1);
		platformMarks.put("testGetChatBotListContainsBotNames", 1);
		platformMarks.put("testGetChatBotListContainsMessageCountPerBot", 1);
		platformMarks.put("testGetChatBotListContainsTotalMessagesUsed", 1);
		platformMarks.put("testGetChatBotListContainsTotalMessagesRemaining", 2);
		platformMarks.put("testInteractWithValidBot", 1);
		platformMarks.put("testInteractWithBotInvalidNegativeIndex", 1);
		platformMarks.put("testInteractWithBotInvalidOutOfRangeIndex", 1);
		platformMarks.put("testInteractWithBotInvalidIndexEqualToSize", 1);
		platformMarks.put("testInteractWithBotAfterLimitReached", 1);
		Map<String, TestFeedback> platformFeedback = new HashMap<>();
		platformFeedback.put("testBotsCollectionInitialized",
				new TestFeedback("Bots collection should be initialized.", 88));
		platformFeedback.put("testBotsCollectionIsPrivate",
				new TestFeedback("Bots collection should be private.",
						87));
		platformFeedback.put("testChatBotPlatformConstructor",
				new TestFeedback("ChatBotPlatform was not properly initialized.", 79));
		platformFeedback.put("testChatBotPlatformBotsCollectionInitialized",
				new TestFeedback("Bots collection was not initialized as an ArrayList<ChatBot>.", 78));

		// addChatBot feedback
		platformFeedback.put("testAddChatBotAddsFirstBot",
				new TestFeedback("First ChatBot was not added as expected.", 71));
		platformFeedback.put("testAddChatBotAddsSecondBot",
				new TestFeedback("Second ChatBot was not added correctly.", 70));
		platformFeedback.put("testAddChatBotIncrememtsBotCount",
				new TestFeedback("Bots count did not increment correctly after adding ChatBots.", 56));
		platformFeedback.put("testAddChatBotReachesLimit",
				new TestFeedback("Limit check failed after reaching the message limit.", 55));
		platformFeedback.put("testAddChatBotAfterLimitReached",
				new TestFeedback("ChatBot was incorrectly added after limit was reached.", 54));

		// getChatBotList feedback
		platformFeedback.put("testGetChatBotListContainsBotNumbers",
				new TestFeedback("Bot numbers were not correctly included in the list output.", 70));
		platformFeedback.put("testGetChatBotListContainsBotNames",
				new TestFeedback("Bot names were missing in the list output.", 75));
		platformFeedback.put("testGetChatBotListContainsMessageCountPerBot",
				new TestFeedback("Message counts for each bot were not correctly displayed.", 74));
		platformFeedback.put("testGetChatBotListContainsTotalMessagesUsed",
				new TestFeedback("Total messages used were incorrectly calculated or not included.",
						73));
		platformFeedback.put("testGetChatBotListContainsTotalMessagesRemaining",
				new TestFeedback("Remaining messages were not correctly displayed.", 34));

		// interactWithBot feedback
		platformFeedback.put("testInteractWithValidBot",
				new TestFeedback("Valid interaction with bot failed.", 69));
		platformFeedback.put("testInteractWithBotInvalidNegativeIndex",
				new TestFeedback(
						"Interaction with negative index did not return the expected error message.",
						53));
		platformFeedback.put("testInteractWithBotInvalidOutOfRangeIndex",
				new TestFeedback(
						"Interaction with out-of-range index failed to provide the correct error message.",
						52));
		platformFeedback.put("testInteractWithBotInvalidIndexEqualToSize",
				new TestFeedback(
						"Interaction with an index equal to the list size did not handle the error correctly.",
						51));
		platformFeedback.put("testInteractWithBotAfterLimitReached",
				new TestFeedback(
						"Interaction after message limit reached did not return 'Daily Limit Reached'.",
						50));

		weightings.put("ChatBotPlatformGrade", platformMarks);
		feedback.put("ChatBotPlatformGrade", platformFeedback);
	}

	private static void initializeChatBotSimulationConfigTest() {
		Map<String, Integer> simulationMarks = new HashMap<>();
		simulationMarks.put("testMainMethodStartsWithHelloWorld", 1);
		simulationMarks.put("testChatBotPlatformInitialization", 1);
		simulationMarks.put("testAllChatBotModelsPresent", 2);
		simulationMarks.put("testChatBotsSectionPresent", 1);
		simulationMarks.put("testInitialSummaryStatistics", 1);
		simulationMarks.put("testAtLeastOneInteractionOccurred", 1);
		simulationMarks.put("testMessageNumberFormatInBotResponses", 1);
		simulationMarks.put("testInvalidBotNumberResponsePresent", 1);
		simulationMarks.put("testSimulationPerformsExactlyFifteenInteractions", 1);
		simulationMarks.put("testFinalSummaryNotEmpty", 1);
		simulationMarks.put("testFinalSummaryStatistics", 1);

		Map<String, TestFeedback> simulationFeedback = new HashMap<>();
		simulationFeedback.put("testMainMethodStartsWithHelloWorld",
				new TestFeedback("Main method output should start with 'Hello World!'", 43));

		// Requirement 2 feedback
		simulationFeedback.put("testChatBotPlatformInitialization",
				new TestFeedback("Expected initialization of ChatBotPlatform was not found.", 84));

		// Requirement 3 feedback
		simulationFeedback.put("testAllChatBotModelsPresent",
				new TestFeedback("Not all ChatBot models were listed in the output.", 42));

		// Requirement 4 feedback
		simulationFeedback.put("testChatBotsSectionPresent",
				new TestFeedback("The 'Your ChatBots' section was missing.", 41));
		simulationFeedback.put("testInitialSummaryStatistics",
				new TestFeedback("Initial summary statistics were missing or incorrect.", 40));

		// Requirement 5 feedback
		simulationFeedback.put("testAtLeastOneInteractionOccurred",
				new TestFeedback("Expected at least one interaction with a ChatBot.", 39));
		simulationFeedback.put("testMessageNumberFormatInBotResponses",
				new TestFeedback("Each interaction should start with '(Message#)'", 38));
		simulationFeedback.put("testInvalidBotNumberResponsePresent",
				new TestFeedback("Expected 'Incorrect Bot Number' message was missing.", 37));
		simulationFeedback.put("testSimulationPerformsExactlyFifteenInteractions",
				new TestFeedback("The simulation should interact exactly 15 times with ChatBots.", 57));

		// Requirement 6 feedback
		simulationFeedback.put("testFinalSummaryNotEmpty",
				new TestFeedback("The final summary section should contain summary information.", 36));
		simulationFeedback.put("testFinalSummaryStatistics",
				new TestFeedback("Final summary statistics were missing or incorrect.", 35));

		weightings.put("ChatBotSimulationGrade", simulationMarks);
		feedback.put("ChatBotSimulationGrade", simulationFeedback);
	}

	public static Map<String, Integer> getWeightings(String gradeType) {
		return weightings.getOrDefault(gradeType, new HashMap<>());
	}

	public static Map<String, TestFeedback> getFeedback(String gradeType) {
		return feedback.getOrDefault(gradeType, new HashMap<>());
	}
}
