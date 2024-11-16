//816034693
import java.util.ArrayList;

public class ChatBotPlatform {
    private ArrayList<ChatBot> bots;

    public ChatBotPlatform() {
        bots = new ArrayList<ChatBot>();
    }

    public boolean addChatBot(int LLMcode) {
        if (ChatBot.limitReached()) {
            return false;
        } else {
            ChatBot newBot = new ChatBot(LLMcode);
            bots.add(newBot);
            return true;
        }
    }

    public String getChatBotList() {
        String s = "--------------------\n";
        for (ChatBot c : bots) {
            s += "Bot Number: " + (bots.indexOf(c) + 1);
            s += " Name: " + c.getChatBotName();
            s += " Number Messages Used: " + c.getNumResponsesGenerated() + "\n";
        }
        s += "Total Messages Used: " + ChatBot.getMessageLimit() + "\n";
        s += "Total Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining() + "\n";
        s += "--------------------\n";
        return s;
    }

    public String interactwithBot(int botNumber, String message) {
        String s;
        if (botNumber >= bots.size() || botNumber < 0)
            s = "Invalid Bot Number (" + botNumber +  ") selected. Try again";
        else
            s = bots.get(botNumber).prompt(message);
        return s;
    }
}