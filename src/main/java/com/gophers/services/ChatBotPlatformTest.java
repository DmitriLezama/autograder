package com.gophers.services;

import org.junit.Before;
import org.junit.Test;
import com.gophers.data.ChatBotPlatform;
import com.gophers.data.ChatBot;
import java.lang.reflect.Field;
import java.util.List;
import static org.junit.Assert.*;

public class ChatBotPlatformTest {
    private ChatBotPlatform platform;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        resetStaticFields();
        platform = new ChatBotPlatform();
    }

    // Resetting static fields to ensure tests run in isolation
    private void resetStaticFields() throws NoSuchFieldException, IllegalAccessException {
        Field messageNumberField = ChatBot.class.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        messageNumberField.set(null, 0);
    }

    // 1. Test bots collection initialization 
    @Test
    public void testBotsCollectionInitialized() {
        List<ChatBot> bots = platform.getChatBots();
        assertNotNull("Bots collection should be initialized", bots);
    }

    @Test
    public void testBotsCollectionEmptyInitially() {
        List<ChatBot> bots = platform.getChatBots();
        assertTrue("Bots collection should be empty initially", bots.isEmpty());
    }

    // 2. Test constructor initialization 
    @Test
    public void testChatBotPlatformConstructor() {
        assertNotNull("ChatBotPlatform should be initialized", platform);
    }

    @Test
    public void testChatBotPlatformBotsCollectionInitialized() {
        assertNotNull("Bots collection should be initialized", platform.getChatBots());
    }

    // 3. Test adding ChatBots individually 
    @Test
    public void testAddFirstChatBot() {
        assertTrue("Should add first ChatBot", platform.addChatBot(1));
        assertEquals("First ChatBot name should be LLaMa", "LLaMa", platform.getChatBots().get(0).getChatBotName());
    }

    @Test
    public void testAddSecondChatBot() {
        platform.addChatBot(1);
        assertTrue("Should add second ChatBot", platform.addChatBot(2));
        assertEquals("Second ChatBot name should be Mistral7B", "Mistral7B", platform.getChatBots().get(1).getChatBotName());
    }

    @Test
    public void testAddThirdChatBot() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        assertTrue("Should add third ChatBot", platform.addChatBot(3));
        assertEquals("Third ChatBot name should be Bard", "Bard", platform.getChatBots().get(2).getChatBotName());
    }

    @Test
    public void testChatBotsCountAfterAdding() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        platform.addChatBot(3);
        assertEquals("Total ChatBots should be 3", 3, platform.getChatBots().size());
    }

    // 4. Test adding ChatBot when message limit is reached 
    @Test
    public void testLimitReachedAfterAddingChatBots() {
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % platform.getChatBots().size(), "Test message");
        }
        assertTrue("Limit should be reached", ChatBot.limitReached());
    }

    @Test
    public void testAddChatBotAfterLimitReached() {
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % platform.getChatBots().size(), "Test message");
        }
        assertFalse("Should not add more ChatBots when limit is reached", platform.addChatBot(4));
    }

    // 5. Test getChatBotList formatting 
    @Test
    public void testGetChatBotListContainsBotNumbers() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        String result = platform.getChatBotList();
        assertTrue("Should contain Bot Number: 0", result.contains("Bot Number: 0"));
        assertTrue("Should contain Bot Number: 1", result.contains("Bot Number: 1"));
    }

    @Test
    public void testGetChatBotListContainsBotNames() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        String result = platform.getChatBotList();
        assertTrue("Should contain Name: LLaMa", result.contains("Name: LLaMa"));
        assertTrue("Should contain Name: Mistral7B", result.contains("Name: Mistral7B"));
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
        assertTrue("Should contain Number Messages Used: 1", result.contains("Number Messages Used: 1"));
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesUsedAndRemaining() {
        platform.addChatBot(1);
        platform.interactWithBot(0, "Hello");
        platform.interactWithBot(0, "How are you?");
        platform.addChatBot(2);
        platform.interactWithBot(1, "Testing");
        String result = platform.getChatBotList();
        assertTrue("Should contain Total Messages Used: 3", result.contains("Total Messages Used: 3"));
        assertTrue("Should contain Total Messages Remaining: 7", result.contains("Total Messages Remaining: 7"));
    }

    // 6. Test interaction with a valid bot number 
    @Test
    public void testInteractWithValidBot() {
        platform.addChatBot(1);
        String response = platform.interactWithBot(0, "Hello");
        assertTrue("Response should indicate interaction", response.contains("(Message# 1) Response from LLaMa"));
        assertTrue("Response should contain generated text", response.contains("generatedTextHere"));
    }

    // 7. Test interaction with invalid bot numbers 
    @Test
    public void testInteractWithBotInvalidNegativeIndex() {
        platform.addChatBot(1);
        assertEquals("Incorrect Bot Number (-1) Selected. Try again", platform.interactWithBot(-1, "Hello").trim());
    }

    @Test
    public void testInteractWithBotInvalidOutOfRangeIndex() {
        platform.addChatBot(1);
        assertEquals("Incorrect Bot Number (5) Selected. Try again", platform.interactWithBot(5, "Hello").trim());
    }

    @Test
    public void testInteractWithBotInvalidIndexEqualToSize() {
        platform.addChatBot(1);
        assertEquals("Incorrect Bot Number (1) Selected. Try again", platform.interactWithBot(1, "Hello").trim());
    }

    // 8. Test interaction when limit is reached 
    @Test
    public void testInteractWithBotAfterLimitReached() {
        platform.addChatBot(1);
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.interactWithBot(0, "Test message");
        }
        String response = platform.interactWithBot(0, "Another message").trim();
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage", response);
    }

    /* 
    @SuppressWarnings("unchecked")
    private List<ChatBot> getBotsCollection() throws NoSuchFieldException, IllegalAccessException {
        Field chatBotsField = platform.getClass().getDeclaredField("bots");
        chatBotsField.setAccessible(true);
        return (List<ChatBot>) chatBotsField.get(platform);
    }
    */
}