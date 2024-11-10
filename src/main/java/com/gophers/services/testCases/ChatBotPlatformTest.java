package com.gophers.services.testCases;

import org.junit.Before;
import org.junit.Test;

import com.gophers.structures.domain.Submission;
import com.gophers.services.handlers.TextProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Modifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ChatBotPlatformTest {
    private Class<?> chatBotClass;
    private Class<?> chatBotPlatformClass;
    private Object platform;
    private Method addChatBot;
    private Method interactWithBot;
    private Method getChatBotList;

    @Before
    public void setUp() throws Exception {
        chatBotClass = Submission.getClass("ChatBot");
        chatBotPlatformClass = Submission.getClass("ChatBotPlatform");
        platform = chatBotPlatformClass.getConstructor().newInstance();
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

    private void initializeMethods() {
        addChatBot = getMethod(chatBotPlatformClass, "addChatBot", int.class);
        interactWithBot = getMethod(chatBotPlatformClass, "interactWithBot", int.class, String.class);
        getChatBotList = getMethod(chatBotPlatformClass, "getChatBotList");        
    }
    
    public Method getMethod (Class<?> methodClass, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        try {
            method = methodClass.getMethod(methodName, parameterTypes);
        }
        catch (NoSuchMethodException e) {}
        return method;
    }

    @SuppressWarnings("unchecked")
    private List<Object> getBotsCollection() throws NoSuchFieldException, IllegalAccessException {
        Field botsField = chatBotPlatformClass.getDeclaredField("bots");
        botsField.setAccessible(true);
        return (List<Object>) botsField.get(platform);
    }

    // ArrayList<ChatBot> - 2 marks
    // 1 mark for testing that it’s a list of ChatBot
    // 1 mark for testing that bots collection is private

    @Test
    public void testBotsCollectionInitialized() throws NoSuchFieldException, IllegalAccessException {
        Field botsField = chatBotPlatformClass.getDeclaredField("bots");
        botsField.setAccessible(true);
        Object botsCollection = botsField.get(platform);
        assertNotNull("Bots collection should be initialized", botsCollection);
    }

    @Test
    public void testBotsCollectionIsPrivate() throws NoSuchFieldException {
        Field botsField = chatBotPlatformClass.getDeclaredField("bots");
        assertTrue("Bots collection should be private", Modifier.isPrivate(botsField.getModifiers())); // 1 mark
    }

    @Test
    public void testBotsCollectionEmptyInitially() throws NoSuchFieldException, IllegalAccessException {
        List<Object> bots = getBotsCollection();
        assertTrue("Bots collection should be empty initially", bots.isEmpty());
    }

    // Constructor - 2 marks
    // 1 mark for testing the constructor works
    // 1 mark for testing bots collection is correctly initialized as an
    // ArrayList<ChatBot>

    @Test
    public void testChatBotPlatformConstructor() {
        assertNotNull("ChatBotPlatform should be initialized", platform); // 1 mark
    }

    @Test
    public void testChatBotPlatformBotsCollectionInitialized() throws NoSuchFieldException, IllegalAccessException {
        assertTrue("Bots collection should be of type ArrayList<ChatBot>", getBotsCollection() instanceof ArrayList);
    }

    // addChatBot(int LLMcode) - 5 marks
    // 1 mark for testing limit reached
    // 2 marks for testing adding chatbots properly before limit reached
    // 2 marks for error handling when limit is reached

    @Test
    public void testAddChatBotAddsFirstBot() throws Exception {
        boolean result = (boolean) addChatBot.invoke(platform, 1);
        assertTrue("Should add a first ChatBot", result);

        List<Object> botsCollection = getBotsCollection();
        Method getChatBotNameMethod = botsCollection.get(0).getClass().getMethod("getChatBotName");
        String botName = (String) getChatBotNameMethod.invoke(botsCollection.get(0));
        assertEquals("First ChatBot name should be LLaMa", "LLaMa", botName);
    }

    @Test
    public void testAddChatBotAddsSecondBot() throws Exception {
        addChatBot.invoke(platform, 1);
        boolean result = (boolean) addChatBot.invoke(platform, 2);
        assertTrue("Should add a second ChatBot", result);
        List<Object> botsCollection = getBotsCollection();
        Method getChatBotNameMethod = botsCollection.get(1).getClass().getMethod("getChatBotName");
        String botName = (String) getChatBotNameMethod.invoke(botsCollection.get(1));
        assertEquals("Second ChatBot name should be Mistral7B", "Mistral7B", botName);
    }

    @Test
    public void testAddChatBotIncrementsBotCount() throws Exception {
        addChatBot.invoke(platform, 1);
        assertEquals("Total ChatBots should be 1", 1, getBotsCollection().size());
        addChatBot.invoke(platform, 2);
        assertEquals("Total ChatBots should be 2", 2, getBotsCollection().size());
        addChatBot.invoke(platform, 3);
        assertEquals("Total ChatBots should be 3", 3, getBotsCollection().size());
    }

    @Test
    public void testAddChatBotReachesLimit() throws Exception {
        int messageLimit = getMessageLimit();

        for (int i = 0; i < messageLimit; i++) {
            addChatBot.invoke(platform, i % 5 + 1);
            interactWithBot.invoke(platform, i % getBotsCollection().size(), "Test message");
        }

        Method limitReachedMethod = Submission.getClass("ChatBot").getMethod("limitReached");
        boolean limitReached = (boolean) limitReachedMethod.invoke(null);

        assertTrue("Limit should be reached", limitReached);
    }

    @Test
    public void testAddChatBotAfterLimitReached() throws Exception {
        int messageLimit = getMessageLimit();

        for (int i = 0; i < messageLimit; i++) {
            addChatBot.invoke(platform, i % 5 + 1);
            interactWithBot.invoke(platform, i % getBotsCollection().size(), "Test message");
        }
        boolean result = (boolean) addChatBot.invoke(platform, 4);
        assertFalse("Should not add more ChatBots when limit is reached", result);
    }

    @Test
    public void testGetChatBotListContainsBotNumbers() throws Exception {
        addChatBot.invoke(platform, 1);
        addChatBot.invoke(platform, 2);
        String result = (String) getChatBotList.invoke(platform);
        assertTrue("Should contain Bot Number: 0", TextProcessor.compareString("Bot Number: 0", result));
        assertTrue("Should contain Bot Number: 1", TextProcessor.compareString("Bot Number: 1", result));
    }

    @Test
    public void testGetChatBotListContainsBotNames() throws Exception {
        addChatBot.invoke(platform, 1);
        addChatBot.invoke(platform, 2);
        String result = (String) getChatBotList.invoke(platform);
        assertTrue("Should contain Name: LLaMa", TextProcessor.compareString("Name: LLaMa", result));
        assertTrue("Should contain Name: Mistral7B", TextProcessor.compareString("Name: Mistral7B", result));
    }

    @Test
    public void testGetChatBotListContainsMessageCountPerBot() throws Exception {
        addChatBot.invoke(platform, 1);
        interactWithBot.invoke(platform, 0, "Hello");
        interactWithBot.invoke(platform, 0, "How are you?");
        addChatBot.invoke(platform, 2);
        interactWithBot.invoke(platform, 1, "Testing");
        String result = (String) getChatBotList.invoke(platform);
        assertTrue("Should contain Number Messages Used: 2", TextProcessor.compareString("Number Messages Used: 2", result));
        assertTrue("Should contain Number Messages Used: 1", TextProcessor.compareString("Number Messages Used: 1", result));
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesUsed() throws Exception {
        addChatBot.invoke(platform, 1);
        interactWithBot.invoke(platform, 0, "Hello");
        interactWithBot.invoke(platform, 0, "How are you?");
        addChatBot.invoke(platform, 2);
        interactWithBot.invoke(platform, 1, "I am under the water");
        String result = (String) getChatBotList.invoke(platform);
        assertTrue("Should contain Total Messages Used: 3", TextProcessor.compareString("Total Messages Used: 3", result));
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesRemaining() throws Exception {
        addChatBot.invoke(platform, 1);
        interactWithBot.invoke(platform, 0, "Hello");
        interactWithBot.invoke(platform, 0, "How are you?");
        addChatBot.invoke(platform, 2);
        interactWithBot.invoke(platform, 1, "I am under the water");
        String result = (String) getChatBotList.invoke(platform);
        assertTrue("Should contain Total Messages Remaining: 7", TextProcessor.compareString("Total Messages Remaining: 7", result));
    }

    @Test
    public void testInteractWithValidBot() throws Exception {
        addChatBot.invoke(platform, 1);
        String response = (String) interactWithBot.invoke(platform, 0, "Hello");
        assertTrue("Response should indicate interaction with LLaMa", TextProcessor.compareString("Response from LLaMa", response));
        assertTrue("Response should contain generated text", TextProcessor.compareString("generatedTextHere", response));
    }

    @Test
    public void testInteractWithBotInvalidNegativeIndex() throws Exception {
        String response = ((String) interactWithBot.invoke(platform, -1, "Hello")).trim();
        assertTrue("Response should indicate incorrect bot number", TextProcessor.compareString("Incorrect Bot Number (-1)", response));
    }

    @Test
    public void testInteractWithBotInvalidOutOfRangeIndex() throws Exception {
        String response = ((String) interactWithBot.invoke(platform, 5, "Hello")).trim();
        assertTrue("Response should indicate incorrect bot number", TextProcessor.compareString("Incorrect Bot Number (5)", response));
    }

    @Test
    public void testInteractWithBotInvalidIndexEqualToSize() throws Exception {
        String response = ((String) interactWithBot.invoke(platform, 1, "Hello")).trim();
        assertTrue("Response should indicate incorrect bot number", TextProcessor.compareString("Incorrect Bot Number (1)", response));
    }

    @Test
    public void testInteractWithBotAfterLimitReached() throws Exception {
        int messageLimit = getMessageLimit();
        addChatBot.invoke(platform, 1);
        for (int i = 0; i < messageLimit; i++)
            interactWithBot.invoke(platform, 0, "Test message");
        String response = ((String) interactWithBot.invoke(platform, 0, "Another message")).trim();
        assertTrue("Response should indicate daily limit reached", TextProcessor.compareString("Daily Limit Reached", response));
    }

    private int getMessageLimit() throws Exception {
        Field messageLimitField = chatBotClass.getDeclaredField("messageLimit");
        messageLimitField.setAccessible(true);
        return (int) messageLimitField.get(null);
    }
}