package com.gophers.SampleAssignment;

public class ChatBotGenerator {
    public static String generateChatBotLLM(int LLMCodeNumber) {
        final String[] botNames = { "LLaMa", "Mistral7B", "Bard", "Claude", "Solar" };
        if (LLMCodeNumber >= 1 && LLMCodeNumber <= botNames.length)
            return botNames[LLMCodeNumber - 1];
        return "ChatGPT-3.5";
    }
}