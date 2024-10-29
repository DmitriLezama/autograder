package com.gophers.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.Test;

import com.gophers.data.ChatBotGenerator;

@SuppressWarnings("static-access")
public class ChatBotGeneratorTest {
    private ChatBotGenerator generator;

    @org.junit.Before
    public void setUp() {
        generator = new ChatBotGenerator();
    }

    // Static method check (1 mark)
    @Test
    public void testMethodIsStatic_generateChatBotLLM() {
        try {
            Method method = ChatBotGenerator.class.getMethod("generateChatBotLLM", int.class);
            assertTrue("Method should be static", Modifier.isStatic(method.getModifiers()));
            String result = ChatBotGenerator.generateChatBotLLM(1);
            assertTrue("Static method should return non-null result", result != null);
        } catch (NoSuchMethodException e) {
            fail("generateChatBotLLM method not found");
        }
    }

    // Valid codes (5 marks)
    @Test
    public void testGenerateChatBotLLM_ReturnsLLaMaForCode1() {
        assertEquals("LLaMa", generator.generateChatBotLLM(1));
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsMistral7BForCode2() {
        assertEquals("Mistral7B", generator.generateChatBotLLM(2));
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsBardForCode3() {
        assertEquals("Bard", generator.generateChatBotLLM(3));
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsClaudeForCode4() {
        assertEquals("Claude", generator.generateChatBotLLM(4));
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsSolarForCode5() {
        assertEquals("Solar", generator.generateChatBotLLM(5));
    }

    // Invalid codes (1 mark)
    @Test
    public void testGenerateChatBotLLM_ReturnsChatGPT35ForInvalidCodes() {
        assertEquals("ChatGPT-3.5", generator.generateChatBotLLM(0));
        assertEquals("ChatGPT-3.5", generator.generateChatBotLLM(6));
        assertEquals("ChatGPT-3.5", generator.generateChatBotLLM(-1));
        assertEquals("ChatGPT-3.5", generator.generateChatBotLLM(Integer.MAX_VALUE));
        assertEquals("ChatGPT-3.5", generator.generateChatBotLLM(Integer.MIN_VALUE));
    }

}
