package com.gophers.services.testCases;

import static org.junit.Assert.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

import com.gophers.structures.domain.Submission;

public class ChatBotTest {
    private Class<?> chatBotClass;
    private Object defaultBot;
    private Object bardBot;
    private Method getChatBotName;
    private Method toString;
    private Method getNumResponsesGenerated;
    private Method getTotalNumResponsesGenerated;
    private Method getTotalNumMessagesRemaining;
    private Method limitReached;
    private Method generateResponse;
    private Method prompt;
    private String GENERATED_RESPONSE_PATTERN = "\\s*\\(Message\\s*#\\s*\\d+\\)\\s*Response\\s*from\\s*.*?\\s*generatedTextHere";
    private String DAILY_LIMIT_REACHED_PATTERN = "\\s*Daily\\s*Limit\\s*Reached\\s*.*?";

    @Before
    public void setUp() throws Exception {
        chatBotClass = Submission.getClass("ChatBot");
        instantiateChatBots();
        initializeMethods();
        resetStaticFields();
    }

    public void resetStaticFields() {
        try {
            Field messageNumberField = chatBotClass.getDeclaredField("messageNumber");
            messageNumberField.setAccessible(true);
            messageNumberField.set(null, 0);
        } catch (Exception e) {}
    }

    public void instantiateChatBots() throws Exception {
        Constructor<?> defaultConstructor = chatBotClass.getConstructor();
        defaultBot = defaultConstructor.newInstance();
        Constructor<?> overloadedConstructor = chatBotClass.getConstructor(int.class);
        bardBot = overloadedConstructor.newInstance(3);
    }

    private void initializeMethods() {
        getChatBotName = getMethod("getChatBotName");
        toString = getMethod("toString");
        getNumResponsesGenerated = getMethod("getNumResponsesGenerated");
        getTotalNumResponsesGenerated = getMethod("getTotalNumResponsesGenerated");
        getTotalNumMessagesRemaining = getMethod("getTotalNumMessagesRemaining");
        limitReached = getMethod("limitReached");
        generateResponse = getMethod("generateResponse");
        prompt = getMethod("prompt", String.class);
    }
    
    public Method getMethod (String methodName, Class<?>... parameterTypes) {
        Method method = null;
        try {
            method = chatBotClass.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
        }
        catch (NoSuchMethodException e) {}
        return method;
    }

    @Test
    public void testChatBotNameField() throws Exception {
        Field chatBotNameField = chatBotClass.getDeclaredField("chatBotName");
        assertNotNull("Field should exist", chatBotNameField);
        assertTrue("Field should be private", Modifier.isPrivate(chatBotNameField.getModifiers()));
        assertEquals("Field should be of type String", String.class, chatBotNameField.getType());
    }

    @Test
    public void testNumResponsesGeneratedField() throws NoSuchFieldException { // 1 mark
        Field numResponsesGeneratedField = chatBotClass.getDeclaredField("numResponsesGenerated");
        numResponsesGeneratedField.setAccessible(true);
        assertTrue("Field should be private", Modifier.isPrivate(numResponsesGeneratedField.getModifiers()));
        assertEquals("Field should be of type int", int.class, numResponsesGeneratedField.getType());
    }

    @Test
    public void testMessageLimitField_Exists() throws Exception { // 1 mark
        Field messageLimitField = chatBotClass.getDeclaredField("messageLimit");
        messageLimitField.setAccessible(true);
        assertNotNull("ChatBot should have messageLimit field", messageLimitField);
    }

    @Test
    public void testMessageLimitField_StaticPrivateInteger() throws Exception {
        Field messageLimitField = chatBotClass.getDeclaredField("messageLimit");
        messageLimitField.setAccessible(true);
        assertTrue("Field should be private", Modifier.isPrivate(messageLimitField.getModifiers()));
        assertTrue("Field should be static", Modifier.isStatic(messageLimitField.getModifiers()));
        assertEquals("Field should be of type int", int.class, messageLimitField.getType());
    }

    @Test
    public void testMessageLimitField_EqualsTen() throws Exception {
        Field messageLimitField = chatBotClass.getDeclaredField("messageLimit");
        messageLimitField.setAccessible(true);
        int messageLimitValue = (int) messageLimitField.get(null);
        assertEquals("Expected message limit value", 10, messageLimitValue);
    }

    @Test
    public void testMessageNumberField_Exists() throws Exception { // 1 mark
        Field messageNumberField = chatBotClass.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        assertNotNull("ChatBot should have messageNumber field", messageNumberField);
    }

    @Test
    public void testMessageNumberField_PrivateStaticEqualsZero() throws Exception { // 1
        Field messageNumberField = chatBotClass.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        assertTrue("Field should be private", Modifier.isPrivate(messageNumberField.getModifiers()));
        assertTrue("Field should be static", Modifier.isStatic(messageNumberField.getModifiers()));
        int messageNumberValue = (int) messageNumberField.get(null);
        assertEquals("Expected message number value", 0, messageNumberValue);
    }

    @Test
    public void testDefaultConstructor_ObjectNotNull() {
        assertNotNull("Default ChatBot instance should not be null", defaultBot);
    }

    @Test
    public void testDefaultConstructor_CorrectName() throws Exception {
        Field chatBotNameField = chatBotClass.getDeclaredField("chatBotName");
        chatBotNameField.setAccessible(true);
        assertEquals("Default ChatBot should be named ChatGPT-3.5", "ChatGPT-3.5", chatBotNameField.get(defaultBot));
    }

    @Test
    public void testDefaultConstructor_InitialResponseCount() throws Exception {
        Field numResponsesGeneratedField = chatBotClass.getDeclaredField("numResponsesGenerated");
        numResponsesGeneratedField.setAccessible(true);
        assertEquals("Initial value of numResponsesGenerated should be 0", 0,
                numResponsesGeneratedField.get(defaultBot));
    }

    @Test // 1 mark
    public void testOverloadedConstructor_ObjectNotNull() {
        assertNotNull(bardBot);
    }

    @Test
    public void testOverloadedConstructor_ValidLLMCodes() throws Exception {
        Constructor<?> overloadedConstructor = chatBotClass.getConstructor(int.class);
        Field chatBotNameField = chatBotClass.getDeclaredField("chatBotName");
        chatBotNameField.setAccessible(true);
        Object llamaBot = overloadedConstructor.newInstance(1);
        assertEquals("LLaMa", chatBotNameField.get(llamaBot));
        Object mistralBot = overloadedConstructor.newInstance(2);
        assertEquals("Mistral7B", chatBotNameField.get(mistralBot));
        assertEquals("Bard", chatBotNameField.get(bardBot));
        Object solarBot = overloadedConstructor.newInstance(5);
        assertEquals("Solar", chatBotNameField.get(solarBot));
    }

    @Test
    public void testOverloadedConstructor_InvalidLLMCode() throws Exception {
        Field chatBotNameField = chatBotClass.getDeclaredField("chatBotName");
        chatBotNameField.setAccessible(true);
        Constructor<?> overloadedConstructor = chatBotClass.getConstructor(int.class);
        Object invalidBot = overloadedConstructor.newInstance(999);
        assertEquals("ChatGPT-3.5", chatBotNameField.get(invalidBot));
    }

    @Test
    public void testGetChatBotName() throws Exception {
        String defaultBotName = (String) getChatBotName.invoke(defaultBot);
        String bardBotName = (String) getChatBotName.invoke(bardBot);

        assertEquals("ChatGPT-3.5", defaultBotName);
        assertEquals("Bard", bardBotName);
    }

    @Test
    public void testNumResponsesGenerated() throws Exception {

        assertEquals(0, getNumResponsesGenerated.invoke(defaultBot));
        prompt.invoke(defaultBot, "Hello");
        assertEquals(1, getNumResponsesGenerated.invoke(defaultBot));
    }

    @Test
    public void testGetTotalNumResponsesGenerated_IsStatic() throws Exception {
        assertTrue("Method should be static", Modifier.isStatic(getTotalNumResponsesGenerated.getModifiers()));
    }

    @Test
    public void testGetTotalNumResponsesGenerated_ReturnsTotalMessages() throws Exception { // 1 mark
        int initialTotal = (int) getTotalNumResponsesGenerated.invoke(null);
        prompt.invoke(defaultBot, "Hello");
        prompt.invoke(bardBot, "How are you?");
        assertEquals(initialTotal + 2, getTotalNumResponsesGenerated.invoke(null));
        prompt.invoke(defaultBot, "I am under the water");
        assertEquals(initialTotal + 3, getTotalNumResponsesGenerated.invoke(null));
    }

    @Test
    public void testGetTotalNumMessagesRemaining_IsStatic() throws Exception { // 1 mark
        assertTrue("getTotalNumMessagesRemaining should be static",
                Modifier.isStatic(getTotalNumMessagesRemaining.getModifiers()));
    }

    @Test
    public void testGetTotalNumMessagesRemaining_ReturnsCorrectValue() throws Exception { // 1 mark
        Field messageLimitField = chatBotClass.getDeclaredField("messageLimit");
        messageLimitField.setAccessible(true);
        int messageLimit = (int) messageLimitField.get(null);
        int initialRemaining = (int) getTotalNumMessagesRemaining.invoke(null);
        assertEquals("Initial remaining messages should equal message limit - responses generated",
                messageLimit - (int) getTotalNumResponsesGenerated.invoke(null),
                initialRemaining);
        prompt.invoke(defaultBot, "Test message");
        int newRemaining = (int) getTotalNumMessagesRemaining.invoke(null);
        assertEquals("Remaining messages should equal message limit - responses generated", initialRemaining - 1,
                newRemaining);
    }

    @Test
    public void testGetTotalNumMessagesRemaining_ReachesZero() throws Exception { // 1 mark

        int initialRemaining = (int) getTotalNumMessagesRemaining.invoke(null);
        for (int i = 0; i < initialRemaining; i++)
            prompt.invoke(defaultBot, "Test message " + i);
        assertEquals("Remaining messages should be 0", 0, (int) getTotalNumMessagesRemaining.invoke(null));
    }

    @Test
    public void testLimitReached_InitiallyFalse() throws Exception { // 1 mark
        assertFalse((boolean) limitReached.invoke(null));
    }

    @Test
    public void testLimitReached_TrueWhenLimitReached() throws Exception { // 1 mark
        int initialRemaining = (int) getTotalNumMessagesRemaining.invoke(null);
        for (int i = 0; i < initialRemaining; i++)
            prompt.invoke(defaultBot, "Test message " + i);
        assertTrue((boolean) limitReached.invoke(null));
    }

    @Test
    public void testLimitReached_IsStatic() throws Exception { // 1 mark
        assertTrue(Modifier.isStatic(limitReached.getModifiers()));
    }

    @Test
    public void testGenerateResponse_FieldIsPrivate() throws Exception { // 1 mark
        assertTrue(Modifier.isPrivate(generateResponse.getModifiers()));
    }

    @Test
    public void testGenerateResponse_IncrementsCounters() throws Exception { // 1 mark
        int initialNumResponses = (int) getNumResponsesGenerated.invoke(defaultBot);
        int initialTotalNumResponses = (int) getTotalNumResponsesGenerated.invoke(null);

        generateResponse.invoke(defaultBot);

        assertEquals(initialNumResponses + 1, (int) getNumResponsesGenerated.invoke(defaultBot));
        assertEquals(initialTotalNumResponses + 1, (int) getTotalNumResponsesGenerated.invoke(null));
    }

    @Test
    public void testGenerateResponse_ReturnsCorrectStringFormat() throws Exception {
        String response = (String) generateResponse.invoke(defaultBot);
        assertTrue(response.toLowerCase().matches(GENERATED_RESPONSE_PATTERN.toLowerCase()));
    }

    @Test
    public void testGenerateResponse_ReturnsString() throws Exception { // 1 mark
        assertEquals(String.class, generateResponse.invoke(defaultBot).getClass());
    }

    @Test
    public void testGenerateResponse_ContainsUniqueMessageNumber() throws Exception { // 1 mark
        String response1 = (String) generateResponse.invoke(defaultBot);
        String response2 = (String) generateResponse.invoke(defaultBot);

        int messageNumber1 = extractMessageNumber(response1);
        int messageNumber2 = extractMessageNumber(response2);
        assertNotEquals(messageNumber1, messageNumber2);
    }

    private int extractMessageNumber(String response) {
        int start = response.indexOf("#");
        int end = response.indexOf(")", start);
        return Integer.parseInt(response.substring(start + 1, end).trim());
    }

    @Test
    public void testToString_BotNameHeaderExists() throws Exception { // 1 mark
        String toStringResult = (String) toString.invoke(defaultBot);
        assertTrue(toStringResult.toLowerCase().contains("ChatBot Name:".toLowerCase()));
    }

    @Test
    public void testToString_CorrectBotName() throws Exception { // 1 mark
        Field chatBotNameField = chatBotClass.getDeclaredField("chatBotName");
        chatBotNameField.setAccessible(true);
        String toStringResult = (String) toString.invoke(defaultBot);

        assertTrue(toStringResult.contains((String) chatBotNameField.get(defaultBot)));
    }

    @Test
    public void testToString_NumMessagesUsedHeaderExists() throws Exception { // 1 mark
        String toStringResult = (String) toString.invoke(defaultBot);
        assertTrue(toStringResult.toLowerCase().contains("Number Messages Used:".toLowerCase()));
    }

    @Test
    public void testToString_CorrectNumResponsesGenerated() throws Exception { // 1 mark
        String toStringResult = (String) toString.invoke(defaultBot);
        assertTrue(toStringResult.contains(String.valueOf(getNumResponsesGenerated.invoke(defaultBot))));
    }

    @Test
    public void testPrompt_ResponseWhenPrompted() throws Exception { // 1 mark
        String response = (String) prompt.invoke(defaultBot, "How are you");
        assertNotNull(response);
        assertTrue(response.toLowerCase().matches(GENERATED_RESPONSE_PATTERN.toLowerCase()));
    }

    @Test
    public void testPrompt_ResponseCountersAfterPrompt() throws Exception { // 1 mark
        prompt.invoke(defaultBot, "How are you");
        assertEquals(1, getNumResponsesGenerated.invoke(defaultBot));
    }

    @Test
    public void testPrompt_LimitReached() throws Exception { // 1 mark
        int remainingMessages = (int) getTotalNumMessagesRemaining.invoke(null);
        for (int i = 0; i < remainingMessages; i++) {
            prompt.invoke(defaultBot, "Test message " + i);
        }
        String limitReachedResponse = (String) prompt.invoke(defaultBot, "Exceed limit message");
        assertTrue(limitReachedResponse.toLowerCase().matches(DAILY_LIMIT_REACHED_PATTERN.toLowerCase()));
    }

    @Test
    public void testPrompt_TotalResponseCounterAndRemainingMessages() throws Exception { // 1 mark
        int initialTotalResponses = (int) getTotalNumResponsesGenerated.invoke(null);
        int initialRemainingMessages = (int) getTotalNumMessagesRemaining.invoke(null);

        prompt.invoke(defaultBot, "First message");
        prompt.invoke(defaultBot, "Second message");
        prompt.invoke(defaultBot, "Third message");

        assertEquals(initialTotalResponses + 3, getTotalNumResponsesGenerated.invoke(null));
        assertEquals(initialRemainingMessages - 3, getTotalNumMessagesRemaining.invoke(null));
    }
}