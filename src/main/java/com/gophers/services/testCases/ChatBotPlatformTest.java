package com.gophers.services.testCases;

import org.junit.Before;
import org.junit.Test;

import com.gophers.structures.domain.Submission;

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
    private Object platform;
    private Method addChatBotMethod;
    private Method interactWithBotMethod;
    private Method getChatBotListMethod;
    private Method getMessageLimitMethod;

    @Before
    public void setUp() throws Exception {
        chatBotClass = Submission.getClass("ChatBot");
        var platformClass = Submission.getClass("ChatBotPlatform");
        platform = platformClass.getConstructor().newInstance();

        addChatBotMethod = platform.getClass().getMethod("addChatBot", int.class);
        interactWithBotMethod = platform.getClass().getMethod("interactWithBot", int.class, String.class);
        getChatBotListMethod = platform.getClass().getMethod("getChatBotList");
        getMessageLimitMethod = chatBotClass.getMethod("getMessageLimit");

        resetStaticFields();
    }

    @Before
    public void resetStaticFields() {
        try {
            Field messageNumberField = chatBotClass.getDeclaredField("messageNumber");
            messageNumberField.setAccessible(true);
            messageNumberField.set(null, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Object> getBotsCollection() throws NoSuchFieldException, IllegalAccessException {
        Field botsField = platform.getClass().getDeclaredField("bots");
        botsField.setAccessible(true);
        return (List<Object>) botsField.get(platform);
    }

    // ArrayList<ChatBot> - 2 marks
    // 1 mark for testing that it’s a list of ChatBot
    // 1 mark for testing that bots collection is private

    @Test
    public void testBotsCollectionInitialized() throws NoSuchFieldException, IllegalAccessException {
        Field botsField = platform.getClass().getDeclaredField("bots");
        botsField.setAccessible(true);
        Object botsCollection = botsField.get(platform);
        assertNotNull("Bots collection should be initialized", botsCollection);
    }

    @Test
    public void testBotsCollectionIsPrivate() throws NoSuchFieldException {
        Field botsField = platform.getClass().getDeclaredField("bots");
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
        boolean result = (boolean) addChatBotMethod.invoke(platform, 1);
        assertTrue("Should add a first ChatBot", result);

        List<Object> botsCollection = getBotsCollection();
        Method getChatBotNameMethod = botsCollection.get(0).getClass().getMethod("getChatBotName");
        String botName = (String) getChatBotNameMethod.invoke(botsCollection.get(0));
        assertEquals("First ChatBot name should be LLaMa", "LLaMa", botName);
    }

    @Test
    public void testAddChatBotAddsSecondBot() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        boolean result = (boolean) addChatBotMethod.invoke(platform, 2);
        assertTrue("Should add a second ChatBot", result);
        List<Object> botsCollection = getBotsCollection();
        Method getChatBotNameMethod = botsCollection.get(1).getClass().getMethod("getChatBotName");
        String botName = (String) getChatBotNameMethod.invoke(botsCollection.get(1));
        assertEquals("Second ChatBot name should be Mistral7B", "Mistral7B", botName);
    }

    @Test
    public void testAddChatBotIncrementsBotCount() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        assertEquals("Total ChatBots should be 1", 1, getBotsCollection().size());
        addChatBotMethod.invoke(platform, 2);
        assertEquals("Total ChatBots should be 2", 2, getBotsCollection().size());
        addChatBotMethod.invoke(platform, 3);
        assertEquals("Total ChatBots should be 3", 3, getBotsCollection().size());
    }

    @Test
    public void testAddChatBotReachesLimit() throws Exception {
        int messageLimit = (int) getMessageLimitMethod.invoke(null);

        for (int i = 0; i < messageLimit; i++) {
            addChatBotMethod.invoke(platform, i % 5 + 1);
            interactWithBotMethod.invoke(platform, i % getBotsCollection().size(), "Test message");
        }

        Method limitReachedMethod = Submission.getClass("ChatBot").getMethod("limitReached");
        boolean limitReached = (boolean) limitReachedMethod.invoke(null);

        assertTrue("Limit should be reached", limitReached);
    }

    @Test
    public void testAddChatBotAfterLimitReached() throws Exception {
        int messageLimit = (int) getMessageLimitMethod.invoke(null);

        for (int i = 0; i < messageLimit; i++) {
            addChatBotMethod.invoke(platform, i % 5 + 1);
            interactWithBotMethod.invoke(platform, i % getBotsCollection().size(), "Test message");
        }
        boolean result = (boolean) addChatBotMethod.invoke(platform, 4);
        assertFalse("Should not add more ChatBots when limit is reached", result);
    }

    @Test
    public void testGetChatBotListContainsBotNumbers() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        addChatBotMethod.invoke(platform, 2);
        String result = (String) getChatBotListMethod.invoke(platform);
        assertTrue("Should contain Bot Number: 0", result.contains("Bot Number: 0"));
        assertTrue("Should contain Bot Number: 1", result.contains("Bot Number: 1"));
    }

    @Test
    public void testGetChatBotListContainsBotNames() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        addChatBotMethod.invoke(platform, 2);
        String result = (String) getChatBotListMethod.invoke(platform);
        assertTrue("Should contain Name: LLaMa", result.contains("Name: LLaMa"));
        assertTrue("Should contain Name: Mistral7B", result.contains("Name: Mistral7B"));
    }

    @Test
    public void testGetChatBotListContainsMessageCountPerBot() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        interactWithBotMethod.invoke(platform, 0, "Hello");
        interactWithBotMethod.invoke(platform, 0, "How are you?");
        addChatBotMethod.invoke(platform, 2);
        interactWithBotMethod.invoke(platform, 1, "Testing");
        String result = (String) getChatBotListMethod.invoke(platform);
        assertTrue("Should contain Number Messages Used: 2", result.contains("Number Messages Used: 2"));
        assertTrue("Should contain Number Messages Used: 1", result.contains("Number Messages Used: 1"));
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesUsed() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        interactWithBotMethod.invoke(platform, 0, "Hello");
        interactWithBotMethod.invoke(platform, 0, "How are you?");
        addChatBotMethod.invoke(platform, 2);
        interactWithBotMethod.invoke(platform, 1, "I am under the water");
        String result = (String) getChatBotListMethod.invoke(platform);
        assertTrue("Should contain Total Messages Used: 3", result.contains("Total Messages Used: 3"));
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesRemaining() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        interactWithBotMethod.invoke(platform, 0, "Hello");
        interactWithBotMethod.invoke(platform, 0, "How are you?");
        addChatBotMethod.invoke(platform, 2);
        interactWithBotMethod.invoke(platform, 1, "I am under the water");
        String result = (String) getChatBotListMethod.invoke(platform);
        assertTrue("Should contain Total Messages Remaining: 7", result.contains("Total Messages Remaining: 7"));
    }

    @Test
    public void testInteractWithValidBot() throws Exception {
        addChatBotMethod.invoke(platform, 1);
        String response = (String) interactWithBotMethod.invoke(platform, 0, "Hello");
        assertTrue("Response should indicate interaction with LLaMa", response.contains("Response from LLaMa"));
        assertTrue("Response should contain generated text", response.contains("generatedTextHere"));
    }

    @Test
    public void testInteractWithBotInvalidNegativeIndex() throws Exception {
        String response = ((String) interactWithBotMethod.invoke(platform, -1, "Hello")).trim();
        assertTrue("Response should indicate incorrect bot number", response.contains("Incorrect Bot Number (-1)"));
    }

    @Test
    public void testInteractWithBotInvalidOutOfRangeIndex() throws Exception {
        String response = ((String) interactWithBotMethod.invoke(platform, 5, "Hello")).trim();
        assertTrue("Response should indicate incorrect bot number", response.contains("Incorrect Bot Number (5)"));
    }

    @Test
    public void testInteractWithBotInvalidIndexEqualToSize() throws Exception {
        String response = ((String) interactWithBotMethod.invoke(platform, 1, "Hello")).trim();
        assertTrue("Response should indicate incorrect bot number", response.contains("Incorrect Bot Number (1)"));
    }

    @Test
    public void testInteractWithBotAfterLimitReached() throws Exception {
        int messageLimit = (int) getMessageLimitMethod.invoke(null);
        addChatBotMethod.invoke(platform, 1);
        for (int i = 0; i < messageLimit; i++)
            interactWithBotMethod.invoke(platform, 0, "Test message");
        String response = ((String) interactWithBotMethod.invoke(platform, 0, "Another message")).trim();
        assertTrue("Response should indicate daily limit reached", response.contains("Daily Limit Reached"));
    }
}