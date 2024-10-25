package com.gophers.SampleAssignment;

public class ChatBot {
    private final static int messageLimit = 10;
    private static int messageNumber = 0;
    private String chatBotName;
    private int numResponsesGenerated;

    // constructors
    public ChatBot() {
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(0);
        this.numResponsesGenerated = 0;
    }

    public ChatBot(int LLMCode) {
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
    }

    // accessors
    public String getChatBotName() {
        return this.chatBotName;
    }

    public int getNumResponsesGenerated() {
        return this.numResponsesGenerated;
    }

    // class methods
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

    // methods
    private String generateResponse() {
        messageNumber++;
        numResponsesGenerated++;
        return "(Message #" + getTotalNumResponsesGenerated()
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
                "\tNumber Messages used: "
                + getNumResponsesGenerated();
    }
}
