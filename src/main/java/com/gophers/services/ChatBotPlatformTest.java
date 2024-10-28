package com.gophers.services;

import org.junit.Before;
import org.junit.Test;
import com.gophers.data.ChatBotPlatform;
import com.gophers.data.ChatBot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

public class ChatBotPlatformTest {
    private ChatBotPlatform platform;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        resetStaticFields();
        platform = new ChatBotPlatform();
    }

    
    private void resetStaticFields() throws NoSuchFieldException, IllegalAccessException {
        Field messageNumberField = ChatBot.class.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        messageNumberField.set(null, 0);
    }


    //ArrayList<ChatBot> - 2 marks
    @Test
    public void testBotsCollectionInitialized() {
        List<ChatBot> bots = platform.getChatBots();
        assertNotNull("Bots collection should be initialized", bots); //1mk
    }

    @Test
    public void testBotsCollectionEmptyInitially() {
        List<ChatBot> bots = platform.getChatBots();
        assertTrue("Bots collection should be empty initially", bots.isEmpty()); //1mk
    }


    //Constructor - 2 marks
    @Test
    public void testChatBotPlatformConstructor() {
        assertNotNull("ChatBotPlatform should be initialized", platform); //1mk
    }

    @Test
    public void testChatBotPlatformBotsCollectionInitialized() {
        assertNotNull("Bots collection should be initialized", platform.getChatBots()); //1mk
    }


    //addChatBot(int LLMcode) - 5 marks
    @Test
    public void testAddFirstChatBot() {
        assertTrue("Should add first ChatBot", platform.addChatBot(1));
        assertEquals("First ChatBot name should be LLaMa", "LLaMa", platform.getChatBots().get(0).getChatBotName()); //1mk
    }

    @Test
    public void testAddSecondChatBot() {
        platform.addChatBot(1);
        assertTrue("Should add second ChatBot", platform.addChatBot(2));
        assertEquals("Second ChatBot name should be Mistral7B", "Mistral7B", platform.getChatBots().get(1).getChatBotName()); //1mk
    }
    /* 
    @Test
    public void testAddThirdChatBot() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        assertTrue("Should add third ChatBot", platform.addChatBot(3));
        assertEquals("Third ChatBot name should be Bard", "Bard", platform.getChatBots().get(2).getChatBotName());
    }
    */
    @Test
    public void testChatBotsCountAfterAdding() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        platform.addChatBot(3);
        assertEquals("Total ChatBots should be 3", 3, platform.getChatBots().size()); //1mk
    }

    
    @Test
    public void testLimitReachedAfterAddingChatBots() {
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % platform.getChatBots().size(), "Test message");
        }
        assertTrue("Limit should be reached", ChatBot.limitReached()); //1mk
    }

    @Test
    public void testAddChatBotAfterLimitReached() {
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.addChatBot(i % 5 + 1);
            platform.interactWithBot(i % platform.getChatBots().size(), "Test message");
        }
        assertFalse("Should not add more ChatBots when limit is reached", platform.addChatBot(4)); //1mk
    }

    
    //getChatBotList() - 6 marks
    @Test
    public void testGetChatBotListContainsBotNumbers() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        String result = platform.getChatBotList();
        assertTrue("Should contain Bot Number: 0", result.contains("Bot Number: 0"));
        assertTrue("Should contain Bot Number: 1", result.contains("Bot Number: 1")); //1mk
    }

    @Test
    public void testGetChatBotListContainsBotNames() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        String result = platform.getChatBotList();
        assertTrue("Should contain Name: LLaMa", result.contains("Name: LLaMa"));
        assertTrue("Should contain Name: Mistral7B", result.contains("Name: Mistral7B")); //1mk
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
        assertTrue("Should contain Number Messages Used: 1", result.contains("Number Messages Used: 1")); //1mk
    }

    @Test
    public void testGetChatBotListContainsTotalMessagesUsed() {
        platform.addChatBot(1);
        platform.interactWithBot(0, "Hello");
        platform.interactWithBot(0, "How are you?");
        platform.addChatBot(2);
        platform.interactWithBot(1, "Testing");
        String result = platform.getChatBotList();
        assertTrue("Should contain Total Messages Used: 3", result.contains("Total Messages Used: 3")); //1 mk
    }
    
    @Test
    public void testGetChatBotListContainsTotalMessagesRemaining() {
        platform.addChatBot(1);
        platform.interactWithBot(0, "Hello");
        platform.interactWithBot(0, "How are you?");
        platform.addChatBot(2);
        platform.interactWithBot(1, "Testing");
        String result = platform.getChatBotList();
        assertTrue("Should contain Total Messages Remaining: 7", result.contains("Total Messages Remaining: 7")); //2 mk
    }
    

    
    //interactWithBot() - 5 marks
    @Test
    public void testInteractWithValidBot() {
        platform.addChatBot(1);
        String response = platform.interactWithBot(0, "Hello");
        assertTrue("Response should indicate interaction", response.contains("(Message# 1) Response from LLaMa"));
        assertTrue("Response should contain generated text", response.contains("generatedTextHere")); //1mk
    }

    @Test
    public void testInteractWithBotInvalidNegativeIndex() {
        platform.addChatBot(1);
        assertEquals("Incorrect Bot Number (-1) Selected. Try again", platform.interactWithBot(-1, "Hello").trim()); //1mk
    }

    @Test
    public void testInteractWithBotInvalidOutOfRangeIndex() {
        platform.addChatBot(1);
        assertEquals("Incorrect Bot Number (5) Selected. Try again", platform.interactWithBot(5, "Hello").trim()); //1mk
    }

    @Test
    public void testInteractWithBotInvalidIndexEqualToSize() {
        platform.addChatBot(1);
        assertEquals("Incorrect Bot Number (1) Selected. Try again", platform.interactWithBot(1, "Hello").trim()); //1mk
    }

    
    @Test
    public void testInteractWithBotAfterLimitReached() {
        platform.addChatBot(1);
        for (int i = 0; i < ChatBot.getMessageLimit(); i++) {
            platform.interactWithBot(0, "Test message");
        }
        String response = platform.interactWithBot(0, "Another message").trim();
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage", response); //1mk
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