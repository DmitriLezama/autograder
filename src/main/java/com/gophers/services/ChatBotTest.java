package com.gophers.services;

import static org.junit.Assert.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.gophers.Submission;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChatBotTest {
    private Class<?> chatBotClass;
    private Object defaultBot;
    private Object bardBot;

    @Before
    public void setUp() throws Exception {
        chatBotClass = Submission.getClass("ChatBot");
        Field messageNumberField = chatBotClass.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        messageNumberField.set(null, 0); // Resetting static messageNumber field to 0
        Constructor<?> defaultConstructor = chatBotClass.getConstructor();
        defaultBot = defaultConstructor.newInstance();
        Constructor<?> overloadedConstructor = chatBotClass.getConstructor(int.class);
        bardBot = overloadedConstructor.newInstance(3);
    }

    @Test
    public void testChatBotNameField() throws Exception {
        Field chatBotNameField = chatBotClass.getDeclaredField("chatBotName");
        assertNotNull("Field should exist", chatBotNameField);
        assertTrue("Field should be private", Modifier.isPrivate(chatBotNameField.getModifiers()));
        assertEquals("Field should be of type String", String.class, chatBotNameField.getType());
    }

    @Test
    public void testMessageLimitField_StaticPrivateInteger() throws Exception {
        Field messageLimitField = chatBotClass.getDeclaredField("messageLimit");
        assertTrue("Field should be private", Modifier.isPrivate(messageLimitField.getModifiers()));
        assertTrue("Field should be static", Modifier.isStatic(messageLimitField.getModifiers()));
        assertEquals("Field should be of type int", int.class, messageLimitField.getType());

        messageLimitField.setAccessible(true);
        int messageLimitValue = (Integer) messageLimitField.get(null);
        assertEquals("Expected message limit value", 10, messageLimitValue);
    }

    @Test
    public void testDefaultConstructor_ObjectNotNull() {
        assertNotNull("Default ChatBot instance should not be null", defaultBot);
    }

    @Test
    public void testGetChatBotName() throws Exception {
        Method getChatBotNameMethod = chatBotClass.getMethod("getChatBotName");
        String defaultBotName = (String) getChatBotNameMethod.invoke(defaultBot);
        String bardBotName = (String) getChatBotNameMethod.invoke(bardBot);

        assertEquals("ChatGPT-3.5", defaultBotName);
        assertEquals("Bard", bardBotName);
    }

    @Test
    public void testNumResponsesGenerated() throws Exception {
        Method getNumResponsesGenerated = chatBotClass.getMethod("getNumResponsesGenerated");
        Method promptMethod = chatBotClass.getMethod("prompt", String.class);

        assertEquals(0, getNumResponsesGenerated.invoke(defaultBot));
        promptMethod.invoke(defaultBot, "Hello");
        assertEquals(1, getNumResponsesGenerated.invoke(defaultBot));
    }

    @Test
    public void testGetTotalNumResponsesGenerated_IsStatic() throws Exception {
        Method getTotalNumResponsesGenerated = chatBotClass.getMethod("getTotalNumResponsesGenerated");
        assertTrue("Method should be static", Modifier.isStatic(getTotalNumResponsesGenerated.getModifiers()));
    }

    @Test
    public void testGenerateResponse_ReturnsCorrectStringFormat() throws Exception {
        Method promptMethod = chatBotClass.getMethod("prompt", String.class);
        String response = (String) promptMethod.invoke(defaultBot, "How are you");

        assertTrue(response.contains("(Message#"));
        assertTrue(response.contains("Response from"));
        assertTrue(response.contains("generatedTextHere"));
    }

    @Test
    public void testToString_Format() throws Exception {
        Method toStringMethod = chatBotClass.getMethod("toString");
        String toStringResult = (String) toStringMethod.invoke(defaultBot);

        assertTrue(toStringResult.contains("ChatBot Name:"));
        assertTrue(toStringResult.contains("Number Messages Used:"));
    }

    @Test
    public void testPrompt_LimitReach() throws Exception {
        Method getTotalNumMessagesRemaining = chatBotClass.getMethod("getTotalNumMessagesRemaining");
        Method promptMethod = chatBotClass.getMethod("prompt", String.class);

        int remainingMessages = (Integer) getTotalNumMessagesRemaining.invoke(null);
        for (int i = 0; i < remainingMessages; i++) {
            promptMethod.invoke(defaultBot, "Test message " + i);
        }

        String limitReachedResponse = (String) promptMethod.invoke(defaultBot, "Exceed limit message");
        assertTrue(limitReachedResponse.contains("Daily Limit Reached"));
    }
}
