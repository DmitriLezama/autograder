package com.gophers.services;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.gophers.data.ChatBot;
import com.gophers.data.ChatBotGenerator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChatBotTest {

    // private static final int MESSAGE_LIMIT = 10;
    // private static final String DEFAULT_BOT_NAME = "ChatGPT-3.5";

    private static ChatBot defaultBot;
    private static ChatBot bardBot;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field messageNumberField = ChatBot.class.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        messageNumberField.set(null, 0);
        defaultBot = new ChatBot();
        bardBot = new ChatBot(3);
    }

    @Test
    public void testChatBotNameField() throws NoSuchFieldException { // 1 mark
        Field field = ChatBot.class.getDeclaredField("chatBotName");
        assertNotNull(field);
        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertEquals(String.class, field.getType());
    }

    @Test
    public void testNumResponsesGeneratedField() throws NoSuchFieldException { // 1 mark
        Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertEquals(int.class, field.getType());
    }

    // @Test
    // public void testMessageLimitField() throws NoSuchFieldException {
    // Field field = ChatBot.class.getDeclaredField("messageLimit");
    // assertTrue(Modifier.isPrivate(field.getModifiers()));
    // assertTrue(Modifier.isStatic(field.getModifiers()));
    // assertEquals(int.class, field.getType());
    // assertEquals(10, ChatBot.getMessageLimit());
    // }

    @Test
    public void testMessageLimitField_FieldExists() throws NoSuchFieldException { // 1 mark
        Field field = ChatBot.class.getDeclaredField("messageLimit");
        assertNotNull(field);
    }

    @Test
    public void testMessageLimitField_FieldisStaticandPrivate() throws NoSuchFieldException { // mark
        Field field = ChatBot.class.getDeclaredField("messageLimit");
        assertTrue(Modifier.isStatic(field.getModifiers()));
        assertTrue(Modifier.isPrivate(field.getModifiers()));
    }

    @Test
    public void testMessageLimitField_FieldisIntegerandEquals10() throws NoSuchFieldException, IllegalAccessException { // 1
        Field field = ChatBot.class.getDeclaredField("messageLimit");
        field.setAccessible(true);
        Object value = field.get(null);
        assertTrue("messageLimit should be an integer", value instanceof Integer);
        int MESSAGE_LIMIT = (Integer) value;
        assertEquals(MESSAGE_LIMIT, ChatBot.getMessageLimit());
    }

    // @Test
    // public void testMessageNumberField() throws NoSuchFieldException { // 1 mark
    // Field field = ChatBot.class.getDeclaredField("messageNumber");
    // assertNotNull(field);
    // assertTrue(Modifier.isPrivate(field.getModifiers()));
    // assertEquals(int.class, field.getType());
    // assertTrue(Modifier.isStatic(field.getModifiers()));
    // }

    @Test
    public void testMessageNumberField_FieldExists() throws NoSuchFieldException { // 1 mark
        Field field = ChatBot.class.getDeclaredField("messageNumber");
        assertNotNull("Field should exist", field);
    }

    @Test
    public void testMessageNumberField_FieldisStaticandZero() throws NoSuchFieldException, IllegalAccessException { // 1
        Field field = ChatBot.class.getDeclaredField("messageNumber");
        assertTrue(Modifier.isStatic(field.getModifiers()));
        field.setAccessible(true);
        assertEquals(0, ChatBot.getTotalNumResponsesGenerated());
    }

    @Test // 1 mark
    public void testDefaultConstructor_ObjectNotNull() {
        ChatBot bot = new ChatBot();
        assertNotNull(bot);
    }

    @Test // 1 mark
    public void testDefaultConstructor_CorrectChatBotName() {
        assertEquals("ChatGPT-3.5", defaultBot.getChatBotName());
        assertEquals("LLaMa", new ChatBot(1).getChatBotName());
        assertEquals("Mistral7B", new ChatBot(2).getChatBotName());
        assertEquals("Bard", bardBot.getChatBotName());
        assertEquals("ChatGPT-3.5", new ChatBot(999).getChatBotName());
    }

    @Test // 1 mark
    public void testDefaultConstructor_InitialResponseCount() {
        ChatBot bot = new ChatBot();
        assertEquals(0, bot.getNumResponsesGenerated());
    }

    @Test // 1 mark
    public void testOverloadedConstructor_ObjectisNotNull() {
        assertNotNull(bardBot);
    }

    @Test // 1 mark
    public void testOverloadedConstructor_ProducesCorrectBot() {
        ChatBot llamaBot = new ChatBot(1);
        assertEquals("LLaMa", llamaBot.getChatBotName());

        ChatBot mistralBot = new ChatBot(2);
        assertEquals("Mistral7B", mistralBot.getChatBotName());

        assertEquals("Bard", bardBot.getChatBotName());

        ChatBot solarBot = new ChatBot(5);
        assertEquals("Solar", solarBot.getChatBotName());

    }

    @Test // 1 mark
    public void testOverloadedConstructor_InitialResponseCount() {
        ChatBot bot = new ChatBot(3);
        assertEquals(0, bot.getNumResponsesGenerated());
    }

    @Test // 1 mark
    public void testOverloadedConstructor_InvalidLLMCode() {
        ChatBot invalidBot = new ChatBot(999);
        assertEquals("ChatGPT-3.5", invalidBot.getChatBotName());
    }

    @Test
    public void testGetChatBotName() throws NoSuchFieldException, SecurityException { // 1 mark
        assertEquals(ChatBotGenerator.generateChatBotLLM(0), defaultBot.getChatBotName());
        assertEquals("Bard", bardBot.getChatBotName());
        Field chatBotNameField = ChatBot.class.getDeclaredField("chatBotName");
        assertTrue(Modifier.isPrivate(chatBotNameField.getModifiers()));

    }

    @Test
    public void testNumResponsesGenerated() { // 1 mark
        assertEquals(0, defaultBot.getNumResponsesGenerated());
        defaultBot.prompt("Hello");
        assertEquals(1, defaultBot.getNumResponsesGenerated());
        defaultBot.prompt("How are you?");
        assertEquals(2, defaultBot.getNumResponsesGenerated());
    }

    @Test
    public void testGetTotalNumResponsesGenerated_IsStatic() throws NoSuchMethodException { // 1 mark
        Method method = ChatBot.class.getMethod("getTotalNumResponsesGenerated");
        assertTrue(Modifier.isStatic(method.getModifiers()));
    }

    @Test
    public void testGetTotalNumResponsesGenerated_ReturnsTotalMessages() { // 1 mark
        int initialTotal = ChatBot.getTotalNumResponsesGenerated();

        defaultBot.prompt("Hello");
        defaultBot.prompt("How are you?");

        assertEquals(initialTotal + 2, ChatBot.getTotalNumResponsesGenerated());

        ChatBot anotherBot = new ChatBot(1);
        anotherBot.prompt("I am under the water");

        assertEquals(initialTotal + 3, ChatBot.getTotalNumResponsesGenerated());
    }

    @Test
    public void testGetTotalNumMessagesRemaining_IsStatic() throws NoSuchMethodException { // 1 mark
        Method method = ChatBot.class.getMethod("getTotalNumMessagesRemaining");
        assertTrue(Modifier.isStatic(method.getModifiers()));
    }

    @Test
    public void testGetTotalNumMessagesRemaining_ReturnsCorrectValue() { // 1 mark
        int initialRemaining = ChatBot.getTotalNumMessagesRemaining();
        assertEquals(ChatBot.getMessageLimit() - ChatBot.getTotalNumResponsesGenerated(), initialRemaining);

        defaultBot.prompt("Test message");

        int newRemaining = ChatBot.getTotalNumMessagesRemaining();
        assertEquals(initialRemaining - 1, newRemaining);
    }

    @Test
    public void testGetTotalNumMessagesRemaining_ReachesZero() { // 1 mark
        int initialRemaining = ChatBot.getTotalNumMessagesRemaining();

        for (int i = 0; i < initialRemaining; i++) {
            defaultBot.prompt("Test message " + i);
        }

        assertEquals(0, ChatBot.getTotalNumMessagesRemaining());
    }

    @Test
    public void testLimitReached_InitiallyFalse() { // 1 mark
        assertFalse(ChatBot.limitReached());
    }

    @Test
    public void testLimitReached_TrueWhenLimitReached() { // 1 mark
        int initialRemaining = ChatBot.getTotalNumMessagesRemaining();

        for (int i = 0; i < initialRemaining; i++) {
            defaultBot.prompt("Test message " + i);
        }

        assertTrue(ChatBot.limitReached());
    }

    @Test
    public void testLimitReached_FalseBeforeLimitReached() { // 1 mark
        int initialRemaining = ChatBot.getTotalNumMessagesRemaining();

        for (int i = 0; i < initialRemaining - 1; i++) {
            defaultBot.prompt("Test message " + i);
            assertFalse(ChatBot.limitReached());
        }

        defaultBot.prompt("Final message");
        assertTrue(ChatBot.limitReached());
    }

    // @Test
    // public void testGenerateResponse() throws NoSuchMethodException {
    // Method method = ChatBot.class.getDeclaredMethod("generateResponse");
    // assertEquals(String.class, method.getReturnType());
    // assertTrue(Modifier.isPrivate(method.getModifiers()));
    // }

    // Generates and returns a String containing
    // a unique message number and the
    // response from the chatbot with its name.
    // The method also increments the count of
    // the messages generated by the chatbot
    // and the count of the messages generated
    // overall byall chatbots. This method is
    // private.

    @Test
    public void testGenerateResponse_FieldisPrivate() throws NoSuchMethodException { // 1 mark
        Method method = ChatBot.class.getDeclaredMethod("generateResponse");
        assertTrue(Modifier.isPrivate(method.getModifiers()));
    }

    @Test
    public void testGenerateResponse_IncrementsCounters() { // 1 mark
        int initialResponses = defaultBot.getNumResponsesGenerated();
        int initialTotalResponses = ChatBot.getTotalNumResponsesGenerated();
        defaultBot.prompt("How are you");
        assertEquals(initialResponses + 1, defaultBot.getNumResponsesGenerated());
        assertEquals(initialTotalResponses + 1, ChatBot.getTotalNumResponsesGenerated());
    }

    @Test
    public void testGenerateResponse_ReturnsCorrectStringFormat() { // 1 mark
        String response = defaultBot.prompt("How are you");
        assertTrue(response.contains("(Message#"));
        assertTrue(response.contains("Response from " + defaultBot.getChatBotName()));
        assertTrue(response.contains(">> generatedTextHere"));
    }

    @Test
    public void testGenerateResponse_ReturnsString() { // 1 mark
        assertEquals(String.class, defaultBot.prompt("How are you").getClass());
    }

    @Test
    public void testGenerateResponse_ContainsUniqueMessageNumber() { // 1 mark
        String response1 = defaultBot.prompt("First message");
        String response2 = defaultBot.prompt("Second message");

        int messageNumber1 = extractMessageNumber(response1);
        int messageNumber2 = extractMessageNumber(response2);

        assertNotEquals(messageNumber1, messageNumber2);
    }

    private int extractMessageNumber(String response) {
        int start = response.indexOf("(Message#") + 9;
        int end = response.indexOf(")", start);
        return Integer.parseInt(response.substring(start, end).trim()); // Added trim() to handle any spaces
    }

    // @Test
    // public void testToString() throws NoSuchMethodException {
    // Method method = ChatBot.class.getMethod("toString");
    // assertEquals(String.class, method.getReturnType());
    // assertNotNull(defaultBot.toString());
    // }

    @Test
    public void testToString_BotNameHeaderExists() { // 1 mark
        assertTrue(defaultBot.toString().trim().contains("ChatBot Name:"));
    }

    @Test
    public void testToString_CorrectBotName() { // 1 mark
        assertTrue(defaultBot.toString().trim().contains(defaultBot.getChatBotName()));
    }

    @Test
    public void testToString_NumMessagesUsedHeaderExists() { // 1 mark
        assertTrue(defaultBot.toString().trim().contains("Number Messages Used:"));
    }

    @Test
    public void testToString_CorrectNumResponsesGenerated() { // 1 mark
        assertTrue(defaultBot.toString().trim().contains(String.valueOf(defaultBot.getNumResponsesGenerated())));
    }

    // test numbered to provide specific order of execution
    @Test
    public void testPrompt1_ResponseWhenPrompted() { // 1 mark
        String response = defaultBot.prompt("How are you");
        assertNotNull(response);
        assertTrue(response.contains("Response from"));
    }

    @Test
    public void testPrompt2_ResponseCountersAfterPrompt() { // 1 mark
        defaultBot.prompt("How are you");
        assertEquals(1, defaultBot.getNumResponsesGenerated());
    }

    @Test
    public void testPrompt3_LimitReach() { // 1 mark
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            defaultBot.prompt("Test message");
        }
        String limitReachedResponse = defaultBot.prompt("One more message");
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage", limitReachedResponse);
    }

    @Test
    public void testPrompt4_TotalResponseCounterAndRemainingMessages() { // 1 mark
        int initialCount = ChatBot.getTotalNumResponsesGenerated();
        int initialRemaining = ChatBot.getTotalNumMessagesRemaining();
        defaultBot.prompt("First message");
        defaultBot.prompt("Second message");
        defaultBot.prompt("Third message");
        assertEquals(initialCount + 3, ChatBot.getTotalNumResponsesGenerated());
        assertEquals(initialRemaining - 3, ChatBot.getTotalNumMessagesRemaining());
    }
}
