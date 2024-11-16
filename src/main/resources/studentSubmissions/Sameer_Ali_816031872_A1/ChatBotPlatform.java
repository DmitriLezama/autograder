//Sameer Ali 816031872
import java.util.ArrayList;

public class ChatBotPlatform {
    public ArrayList<ChatBot> bots; //used public so the chatBotPlatform.bots.size() could be accessed in ChatBotSimulation
    
    // Constructor
    public ChatBotPlatform() {
        this.bots = new ArrayList<>();
    }
    
    public boolean addChatBot(int LLMcode) {
        if (!ChatBot.limitReached()) {
            ChatBot newChatBot = new ChatBot(LLMcode);
            bots.add(newChatBot);
            return true;
        } 
        else 
            return false;
    }
    
    public String getChatBotList() {
    String result = "Your ChatBots\n";

    for (int i = 0; i < bots.size(); i++) {
        ChatBot bot = bots.get(i);
        result += "Bot Number: " + i
                + " " + bot.toString()
                + "\n";
    }

    result += "Total Messages Used: " + ChatBot.getTotalNumResponsesGenerated()
                + "\nTotal Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining();

    return result;
    }

    public String interactWithBot(int botNumber, String message) {
        if (botNumber >= 0 && botNumber < bots.size()) {
            ChatBot selectedBot = bots.get(botNumber);
            return selectedBot.prompt(message);
        } 
        else {
            return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        }
    } 
}

//https://www.geeksforgeeks.org/arraylist-size-method-in-java-with-examples/
//used array list size method