package com.gophers.services.testCases;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gophers.structures.domain.Submission;
//comment 2
public class ChatBotGeneratorTest {
    private static Class<?> ChatBotGeneratorClass;
    private static Method generateChatBotLLM;

    @BeforeClass
    public static void initialize() throws Exception {
        ChatBotGeneratorClass = Submission.getClass("ChatBotGenerator");
        generateChatBotLLM = ChatBotGeneratorClass.getMethod("generateChatBotLLM", int.class);
    }

    // Static method check (1 mark)
    @Test
    public void testMethodIsStatic_generateChatBotLLM() {
        try {
            assertTrue("Method should be static", Modifier.isStatic(generateChatBotLLM.getModifiers()));
            Object result = generateChatBotLLM.invoke(null, 1); // invoke static method with null instance
            assertTrue("Static method should return non-null result", result != null);
        }
        catch (Exception e) {
            fail("Method invocation failed: " + e.getMessage());
        }
    }

    // Valid codes (5 marks)
    @Test
    public void testGenerateChatBotLLM_ReturnsLLaMaForCode1() {
        assertChatBotResponse(1, "LLaMa");
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsMistral7BForCode2() {
        assertChatBotResponse(2, "Mistral7B");
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsBardForCode3() {
        assertChatBotResponse(3, "Bard");
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsClaudeForCode4() {
        assertChatBotResponse(4, "Claude");
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsSolarForCode5() {
        assertChatBotResponse(5, "Solar");
    }

    @Test
    public void testGenerateChatBotLLM_ReturnsChatGPT35ForInvalidCodes() {
        try {
            Object instance = Modifier.isStatic(generateChatBotLLM.getModifiers()) ? null
                    : ChatBotGeneratorClass.getDeclaredConstructor().newInstance();
            assertEquals("ChatGPT-3.5", generateChatBotLLM.invoke(instance, 0));
            assertEquals("ChatGPT-3.5", generateChatBotLLM.invoke(instance, 6));
            assertEquals("ChatGPT-3.5", generateChatBotLLM.invoke(instance, -1));
            assertEquals("ChatGPT-3.5", generateChatBotLLM.invoke(instance, Integer.MAX_VALUE));
            assertEquals("ChatGPT-3.5", generateChatBotLLM.invoke(instance, Integer.MIN_VALUE));
        } catch (Exception e) {
            fail("Method invocation failed: " + e.getMessage());
        }
    }

    private void assertChatBotResponse(int code, String expected) {
        try {
            Object instance = Modifier.isStatic(generateChatBotLLM.getModifiers()) ? null
                    : ChatBotGeneratorClass.getDeclaredConstructor().newInstance();
            String result = (String) generateChatBotLLM.invoke(instance, code);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail("Method invocation failed: " + e.getMessage());
        }
    }
}
