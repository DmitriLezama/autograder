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
        Field messageNumberField = ChatBot.class.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        messageNumberField.set(null, 0);
        platform = new ChatBotPlatform();
    }

    @Test
    public void testBotsCollection() {
        List<ChatBot> bots = platform.getChatBots();
        assertNotNull("Bots collection should be initialized", bots);
        assertTrue("Bots collection should be empty", bots.isEmpty());
    }

    @Test
    public void testConstructor() {
        assertNotNull("ChatBotPlatform should be initialized", platform);
        assertNotNull("Bots collection should be initialized", platform.getChatBots());
    }

    @Test
    public void testAddChatBot() throws NoSuchFieldException, IllegalAccessException {
        assertFalse("Limit should not be reached initially", ChatBot.limitReached());

        assertTrue("Should add first ChatBot", platform.addChatBot(1));
        assertTrue("Should add second ChatBot", platform.addChatBot(2));
        assertTrue("Should add third ChatBot", platform.addChatBot(3));

        List<ChatBot> chatBots = getBotsCollection();
        assertEquals("First ChatBot name should be LLaMa", "LLaMa", chatBots.get(0).getChatBotName());
        assertEquals("Second ChatBot name should be Mistral7B", "Mistral7B", chatBots.get(1).getChatBotName());
        assertEquals("Third ChatBot name should be Bard", "Bard", chatBots.get(2).getChatBotName());
        assertEquals("Total ChatBots should be 3", 3, chatBots.size());
    }

    @Test
    public void testAddChatBotLimitReached() {
        for (int i = 0; i <= ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % 5, "I am under the water");
        }

        assertTrue("Limit should be reached", ChatBot.limitReached());
        assertFalse("Should not be able to add more ChatBots", platform.addChatBot(4));
    }

    @Test
    public void testGetChatBotList() {
        platform.addChatBot(1);
        platform.interactWithBot(0, "Hello");
        platform.interactWithBot(0, "How are you?");
        platform.addChatBot(2);
        platform.interactWithBot(1, "I am under the water");

        String result = platform.getChatBotList();
        assertTrue("Should contain Bot Number: 0", result.contains("Bot Number: 0"));
        assertTrue("Should contain Name: LLaMa", result.contains("Name: LLaMa"));
        assertTrue("Should contain Number Messages Used: 2", result.contains("Number Messages Used: 2"));
        assertTrue("Should contain Bot Number: 1", result.contains("Bot Number: 1"));
        assertTrue("Should contain Name: Mistral7B", result.contains("Name: Mistral7B"));
        assertTrue("Should contain Number Messages Used: 1", result.contains("Number Messages Used: 1"));
        assertTrue("Should contain Total Messages Used: 3", result.contains("Total Messages Used: 3"));
        assertTrue("Should contain Total Messages Remaining", result.contains("Total Messages Remaining: 7"));
    }

    @Test
    public void testGetChatBotListEmpty() {
        String result = platform.getChatBotList();
        assertTrue("Should indicate no ChatBots available", result.contains("Total Messages Used: 0"));
        assertTrue("Should indicate remaining messages equal to limit",
                result.contains("Total Messages Remaining: " + ChatBot.getMessageLimit()));
        assertFalse("Should not contain any ChatBot info", result.contains("Bot Number"));
    }

    @Test
    public void testInteractWithBotValidNumber() {
        platform.addChatBot(1);
        String response = platform.interactWithBot(0, "Hello");
        assertTrue("Response should indicate interaction", response.contains("(Message# 1) Response from LLaMa"));
        assertTrue("Response should contain expected text", response.contains("generatedTextHere"));
    }

    @Test
    public void testInteractWithBotInvalidNumber() {
        platform.addChatBot(1);
        assertEquals("Incorrect Bot Number (-1) Selected. Try again", platform.interactWithBot(-1, "Hello").trim());
        assertEquals("Incorrect Bot Number (5) Selected. Try again", platform.interactWithBot(5, "Hello").trim());
        assertEquals("Incorrect Bot Number (1) Selected. Try again", platform.interactWithBot(1, "Hello").trim());
    }

    @Test
    public void testInteractWithBotLimitReached() {
        platform.addChatBot(1);
        for (int i = 0; i <= ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % 5, "I am under the water");
        }

        String response = platform.interactWithBot(0, "please help me").trim();
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage", response);
        assertFalse("Should not be able to add more ChatBots", platform.addChatBot(1));
    }

    @SuppressWarnings("unchecked")
    private List<ChatBot> getBotsCollection() throws NoSuchFieldException, IllegalAccessException {
        Field chatBotsField = platform.getClass().getDeclaredField("bots");
        chatBotsField.setAccessible(true);
        return (List<ChatBot>) chatBotsField.get(platform);
    }
}
