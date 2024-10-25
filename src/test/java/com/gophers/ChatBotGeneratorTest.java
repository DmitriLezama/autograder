package com.gophers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.gophers.SampleAssignment.ChatBotGenerator;

public class ChatBotGeneratorTest {

    @Test
    public void testGenerateChatBotLLMValidCodes() {
        assertEquals("LLaMa", ChatBotGenerator.generateChatBotLLM(1));
        assertEquals("Mistral7B", ChatBotGenerator.generateChatBotLLM(2));
        assertEquals("Bard", ChatBotGenerator.generateChatBotLLM(3));
        assertEquals("Claude", ChatBotGenerator.generateChatBotLLM(4));
        assertEquals("Solar", ChatBotGenerator.generateChatBotLLM(5));
    }

    @Test
    public void testGenerateChatBotLLMInvalidCodes() {
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(0));
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(6));
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(-1));
    }
}
