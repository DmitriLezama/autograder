package com.gophers.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.gophers.data.ChatBot;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChatBotTest {

    private static ChatBot defaultBot;
    private static ChatBot bardBot;

    @BeforeClass
    public static void initializeDefaultBot() {
        defaultBot = new ChatBot();
        bardBot = new ChatBot(3);
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
        assertEquals(10, ChatBot.getMessageLimit());
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
    public void testGetChatBotName() {
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

    // test numbered to provide specific order of execution
    @Test
    public void test1ResponseWhenPrompted() {
        assertNotNull(defaultBot.prompt("How are you"));
    }

    @Test
    public void test2ResponseCountersAfterPrompt() {
        assertNotNull(defaultBot.prompt("How are you"));
        assertEquals(2, defaultBot.getNumResponsesGenerated());
    }

    @Test
    public void test3LimitReach() throws NoSuchMethodException {
        Method method = ChatBot.class.getMethod("limitReached");
        assertTrue(Modifier.isStatic(method.getModifiers()));
        assertFalse(ChatBot.limitReached());
    }

    @Test
    public void test4TotalResponseCounterAfterPrompt() throws NoSuchMethodException {
        Method method = ChatBot.class.getMethod("getTotalNumResponsesGenerated");
        assertTrue(Modifier.isStatic(method.getModifiers()));
        assertEquals(2, ChatBot.getTotalNumResponsesGenerated());
    }

    @Test
    public void test5RemainingMessagesAfterPrompt() throws NoSuchMethodException {
        Method method = ChatBot.class.getMethod("getTotalNumMessagesRemaining");
        assertTrue(Modifier.isStatic(method.getModifiers()));
        assertEquals(8, ChatBot.getTotalNumMessagesRemaining());
    }
}
