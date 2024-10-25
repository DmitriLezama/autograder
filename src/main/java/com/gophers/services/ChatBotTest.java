package com.gophers.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gophers.data.ChatBot;

public class ChatBotTest {

    private static ChatBot defaultBot;
    private static ChatBot bardBot;

    @BeforeAll
    public static void initializeDefaultBot() {
        defaultBot = new ChatBot();
        bardBot = new ChatBot(3);
        assertNotNull(defaultBot.prompt("Hello Bard"));
        assertNotNull(bardBot.prompt("Hello Chat GPT"));
    }
    
    @Test
    public void testChatBotNameField() throws NoSuchFieldException {
        Field field = ChatBot.class.getDeclaredField("chatBotName");
        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertEquals(String.class, field.getType());
    }

    @Test
    public void testNumResponsesGeneratedField() throws NoSuchFieldException {
        Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertEquals(int.class, field.getType());
    }

    @Test
    public void testMessageLimitField() throws NoSuchFieldException {
        Field field = ChatBot.class.getDeclaredField("messageLimit");
        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertEquals(int.class, field.getType());
        assertTrue(Modifier.isStatic(field.getModifiers()));
        assertEquals(10, defaultBot.getMessageLimit());
    }

    @Test
    public void testMessageNumberField() throws NoSuchFieldException {
        Field field = ChatBot.class.getDeclaredField("messageNumber");
        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertEquals(int.class, field.getType());
        assertTrue(Modifier.isStatic(field.getModifiers()));
    }

    @Test
    public void testDefaultConstructor() {
        ChatBot bot = new ChatBot();
        assertNotNull(bot);
    }

    @Test
    public void testOverloadedConstructor() {
        ChatBot bot = new ChatBot(4);
        assertNotNull(bot);
    }

    @Test
    public void testGetChatBotName() throws NoSuchMethodException {
        assertEquals("ChatGPT-3.5", defaultBot.getChatBotName());
        assertEquals("Bard", bardBot.getChatBotName());
    }

    @Test
    public void testToString() throws NoSuchMethodException {
        Method method = ChatBot.class.getMethod("toString");
        assertEquals(String.class, method.getReturnType());
        assertNotNull(defaultBot.toString());
    }

    @Test
    public void testGenerateResponse() throws NoSuchMethodException {
        Method method = ChatBot.class.getDeclaredMethod("generateResponse");
        assertEquals(String.class, method.getReturnType());
        assertTrue(Modifier.isPrivate(method.getModifiers()));
    }
    
    @Test
    public void testResponseWhenPrompted() throws NoSuchMethodException {
        assertNotNull(defaultBot.prompt("How are you"));
    }

    @Test
    public void testResponseCountersAfterPrompt() throws NoSuchMethodException {
        assertEquals(1, defaultBot.getNumResponsesGenerated());
    }

    @AfterAll
    @Test
    public static void testLimitReach() throws NoSuchMethodException {
        Method method = ChatBot.class.getMethod("limitReached");
        assertTrue(Modifier.isStatic(method.getModifiers()));
        assertFalse(defaultBot.limitReached());
    }

    @AfterAll
    @Test
    public static void testTotalResponseCounterAfterPrompt() throws NoSuchMethodException {
        Method method = ChatBot.class.getMethod("getTotalNumResponsesGenerated");
        assertTrue(Modifier.isStatic(method.getModifiers()));
        assertEquals(3, defaultBot.getTotalNumResponsesGenerated());
    }

    @AfterAll
    @Test
    public static void testRemainingMessagesAfterPrompt() throws NoSuchMethodException {
        Method method = ChatBot.class.getMethod("getTotalNumMessagesRemaining");
        assertTrue(Modifier.isStatic(method.getModifiers()));
        assertEquals(7, defaultBot.getTotalNumMessagesRemaining());
    }
}
