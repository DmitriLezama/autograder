package com.gophers.data;

public class ChatBot {
    private final static int messageLimit = 10;
    private static int messageNumber = 0;
    private String chatBotName;
    private int numResponsesGenerated;

    public ChatBot() {
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(0);
        this.numResponsesGenerated = 0;
    }

    public ChatBot(int LLMCode) {
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
    }

    public String getChatBotName() {
        return this.chatBotName;
    }

    public int getNumResponsesGenerated() {
        return this.numResponsesGenerated;
    }

    public static int getMessageLimit() {
        return messageLimit;
    }

    public static int getTotalNumResponsesGenerated() {
        return messageNumber;
    }

    public static int getTotalNumMessagesRemaining() {
        int messagesSent = getTotalNumResponsesGenerated();
        return (messageLimit - messagesSent);
    }

    public static boolean limitReached() {
        int messagesSent = getTotalNumResponsesGenerated();
        return (messagesSent >= messageLimit);
    }

    private String generateResponse() {
        messageNumber++;
        numResponsesGenerated++;
        return "(Message# " + getTotalNumResponsesGenerated()
                + ") Response from " + getChatBotName()
                + "\t >> generatedTextHere";
    }

    public String prompt(String requestMessage) {
        if (limitReached())
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        return generateResponse();
    }

    public String toString() {
        return "ChatBot Name: " + getChatBotName() +
                "\tNumber Messages Used: "
                + getNumResponsesGenerated();
    }
}
