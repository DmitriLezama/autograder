package com.gophers.services;

import org.junit.Before;
import org.junit.Test;
import com.gophers.data.ChatBotPlatform;
import com.gophers.data.ChatBot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Modifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ChatBotPlatformTest {
    private ChatBotPlatform platform;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        resetStaticFields();
        platform = new ChatBotPlatform();
    }

    // Reset static fields in ChatBot class before each test
    private void resetStaticFields() throws NoSuchFieldException, IllegalAccessException {
        Field messageNumberField = ChatBot.class.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        messageNumberField.set(null, 0);
    }

    // helper method
    @SuppressWarnings("unchecked")
    private List<ChatBot> getBotsCollection() throws NoSuchFieldException, IllegalAccessException {
        Field botsField = platform.getClass().getDeclaredField("bots");
        botsField.setAccessible(true);
        return (List<ChatBot>) botsField.get(platform);
    }

    // ArrayList<ChatBot> - 2 marks
    // 1 mark for testing that it’s a list of ChatBot
    // 1 mark for testing that bots collection is private

    @Test
    public void testBotsCollectionInitialized() throws NoSuchFieldException, IllegalAccessException {
        Field botsField = ChatBotPlatform.class.getDeclaredField("bots");
        botsField.setAccessible(true);
        Object botsCollection = botsField.get(platform);
        assertNotNull("Bots collection should be initialized", botsCollection);
    }

    @Test
    public void testBotsCollectionIsPrivate() throws NoSuchFieldException {
        Field botsField = ChatBotPlatform.class.getDeclaredField("bots");
        assertTrue("Bots collection should be private", Modifier.isPrivate(botsField.getModifiers())); // 1 mark
    }

    @Test
    public void testBotsCollectionEmptyInitially() throws NoSuchFieldException, IllegalAccessException {
        List<ChatBot> bots = getBotsCollection();
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
    public void testAddChatBotAddsFirstBot() throws NoSuchFieldException, IllegalAccessException {
        assertTrue("Should add a first ChatBot", platform.addChatBot(1));
        assertEquals("First ChatBot name should be LLaMa", "LLaMa", getBotsCollection().get(0).getChatBotName());
    }

    @Test
    public void testAddChatBotAddsSecondBot() throws NoSuchFieldException, IllegalAccessException {
        platform.addChatBot(1);
        assertTrue("Should add a second ChatBot", platform.addChatBot(2));
        assertEquals("Second ChatBot name should be Mistral7B", "Mistral7B",
                getBotsCollection().get(1).getChatBotName());
    }

    @Test
    public void testAddChatBotIncrememtsBotCount() throws NoSuchFieldException, IllegalAccessException {
        platform.addChatBot(1);
        assertEquals("Total ChatBots should be 1", 1, getBotsCollection().size());
        platform.addChatBot(2);
        assertEquals("Total ChatBots should be 2", 2, getBotsCollection().size());
        platform.addChatBot(3);
        assertEquals("Total ChatBots should be 3", 3, getBotsCollection().size());
    }

    @Test
    public void testAddChatBotReachesLimit() throws NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % getBotsCollection().size(), "Test message");
        }
        assertTrue("Limit should be reached", ChatBot.limitReached());
    }

    @Test
    public void testAddChatBotAfterLimitReached() throws NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % getBotsCollection().size(), "Test message");
        }
        assertFalse("Should not add more ChatBots when limit is reached", platform.addChatBot(4));
    }

    // getChatBotList() - 6 marks
    // This section is complete as per comments

    @Test
    public void testGetChatBotListContainsBotNumbers() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        String result = platform.getChatBotList();
        assertTrue("Should contain Bot Number: 0", result.contains("Bot Number: 0"));
        assertTrue("Should contain Bot Number: 1", result.contains("Bot Number: 1")); // 1 mark
    }

    @Test
    public void testGetChatBotListContainsBotNames() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        String result = platform.getChatBotList();
        assertTrue("Should contain Name: LLaMa", result.contains("Name: LLaMa"));
        assertTrue("Should contain Name: Mistral7B", result.contains("Name: Mistral7B")); // 1 mark
    }

    @Test
    public void testGetChatBotListContainsMessageCountPerBot() {
        platform.addChatBot(1);
        platform.interactWithBot(0, "Hello");
        platform.interactWithBot(0, "How are you?");
        platform.addChatBot(2);
        platform.interactWithBot(1, "Testing");
        String result = platform.getChatBotList();
        assertTrue("Should contain Number Messages Used: 2", result.contains("Number Messages Used: 2"));
        assertTrue("Should contain Number Messages Used: 1", result.contains("Number Messages Used: 1")); // 1 mark
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesUsed() {
        platform.addChatBot(1);
        platform.interactWithBot(0, "Hello");
        platform.interactWithBot(0, "How are you?");
        platform.addChatBot(2);
        platform.interactWithBot(1, "Testing");
        String result = platform.getChatBotList();
        assertTrue("Should contain Total Messages Used: 3", result.contains("Total Messages Used: 3")); // 1 mark
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesRemaining() {
        platform.addChatBot(1);
        platform.interactWithBot(0, "Hello");
        platform.interactWithBot(0, "How are you?");
        platform.addChatBot(2);
        platform.interactWithBot(1, "Testing");
        String result = platform.getChatBotList();
        assertTrue("Should contain Total Messages Remaining: 7", result.contains("Total Messages Remaining: 7")); // 2
                                                                                                                  // marks
    }

    // interactWithBot() - 5 marks
    // String checks adjusted to allow more leniency

    @Test
    public void testInteractWithValidBot() {
        platform.addChatBot(1);
        String response = platform.interactWithBot(0, "Hello");
        assertTrue("Response should indicate interaction with LLaMa", response.contains("Response from LLaMa"));
        assertTrue("Response should contain generated text", response.contains("generatedTextHere")); // 1 mark
    }

    @Test
    public void testInteractWithBotInvalidNegativeIndex() {
        platform.addChatBot(1);
        String response = platform.interactWithBot(-1, "Hello").trim();
        assertTrue("Response should indicate incorrect bot number", response.contains("Incorrect Bot Number (-1)")); // 1
                                                                                                                     // mark
    }

    @Test
    public void testInteractWithBotInvalidOutOfRangeIndex() {
        platform.addChatBot(1);
        String response = platform.interactWithBot(5, "Hello").trim();
        assertTrue("Response should indicate incorrect bot number", response.contains("Incorrect Bot Number (5)")); // 1
                                                                                                                    // mark
    }

    @Test
    public void testInteractWithBotInvalidIndexEqualToSize() {
        platform.addChatBot(1);
        String response = platform.interactWithBot(1, "Hello").trim();
        assertTrue("Response should indicate incorrect bot number", response.contains("Incorrect Bot Number (1)")); // 1
                                                                                                                    // mark
    }

    @Test
    public void testInteractWithBotAfterLimitReached() {
        platform.addChatBot(1);
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.interactWithBot(0, "Test message");
        }
        String response = platform.interactWithBot(0, "Another message").trim();
        assertTrue("Response should indicate daily limit reached", response.contains("Daily Limit Reached")); // 1 mark
    }
}
