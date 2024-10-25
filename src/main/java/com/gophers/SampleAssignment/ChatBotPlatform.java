package com.gophers.SampleAssignment;

import java.util.ArrayList;

public class ChatBotPlatform {
    private ArrayList<ChatBot> bots;

    public ChatBotPlatform() {
        bots = new ArrayList<ChatBot>();
    }

    public ArrayList<ChatBot> getChatBots() {
        return this.bots;
    }

    public String getChatBotList() {
        String line = "-----------------------------\n";
        String botList = line + "Your ChatBots\n";
        for (ChatBot bot : bots)
            botList += "Bot Number: " + bots.indexOf(bot) + " " + bot.toString() + "\n";

        int total = ChatBot.getTotalNumResponsesGenerated();
        int remaining = ChatBot.getTotalNumMessagesRemaining();

        botList += "Total Messages Used: " + total + "\n";
        botList += "Total Messages Remaining: " + remaining + "\n";
        botList += line;
        return botList;
    }

    public boolean addChatBot(int LLMcode) {
        if (ChatBot.limitReached())
            return false;
        ChatBot bot = new ChatBot(LLMcode);
        return bots.add(bot);
    }

    public String interactWithBot(int botNumber, String message) {
        if (botNumber < 0 || botNumber >= bots.size())
            return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        ChatBot bot = bots.get(botNumber);
        return bot.prompt(message);
    }
}