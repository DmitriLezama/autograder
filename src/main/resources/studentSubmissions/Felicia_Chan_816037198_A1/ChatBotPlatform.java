// 816037198
import java.util.ArrayList;

public class ChatBotPlatform {
    // ChatBotPlatform Attributes
    private ArrayList <ChatBot> bots;
    
    // ChatBotPlatform Constructor
    public ChatBotPlatform () {
        bots = new ArrayList <> ();
    }
    
    // adds bot to platform only if the daily limit has not be reached
    public boolean addChatBot(int LLMcode) {
        if (ChatBot.limitReached()) {
            return false;
        }
        bots.add(new ChatBot(LLMcode));
        return true;
    }
    // returns string of information about the platform
    public String getChatBotList () {
        String ChatBotsInformation = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n";
        ChatBotsInformation += "Your ChatBots\n";
        
        if (bots.size() == 0) {
            ChatBotsInformation += "The platform does not have any ChatBots\n";
        }
        else {
            for (int i = 0; i < bots.size(); i++) {
                ChatBotsInformation += "Bot Number: " + i + " " + bots.get(i).toString();
            }
        }
        ChatBotsInformation += "Total Messages Used: " + ChatBot.getTotalNumResponsesGenerated() + "\n";
        ChatBotsInformation += "Total Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining() + "\n";
        ChatBotsInformation += "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -";
                                
        return ChatBotsInformation;
    }
    // gets a response from the specified ChatBot if it exist
    public String interactWithBot (int botNumber, String message) {
        // ChatBot number specified does not exist within the Platform
        if (botNumber < 0 || botNumber >= bots.size()) {          
            return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        }
        return (bots.get(botNumber)).prompt(message);
    }
}