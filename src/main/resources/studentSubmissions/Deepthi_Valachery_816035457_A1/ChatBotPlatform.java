//Student Name: Deepthi Valachery
//Student ID: 816035457

import java.util.ArrayList;

public class ChatBotPlatform {
    private ArrayList<ChatBot> bots;
    
    public ChatBotPlatform(){
        this.bots = new ArrayList<>();  //init bot collection
    }
    
    // Accessors
    public ArrayList<ChatBot> getChatbotList() {
        return bots;
    }
    
    //Methods
    public boolean addChatBot(int LLMcode){
        if(!ChatBot.limitReached()){
            ChatBot newChatBot = new ChatBot(LLMcode);
            bots.add(newChatBot);
            return true;
        } else {
            return false;
        }
    }
    
    public String getChatBotList() {
    String result = "Your Chatbots\n";

    for (int i = 0; i < bots.size(); i++) {
        ChatBot currentBot = bots.get(i);
            result += "Bot number: " + i + currentBot.toString();
    }

    result += "Total messages used: " + ChatBot.getTotalNumResponsesGenerated() + "\nTotal messages remaining: " + ChatBot.getTotalNumMessagesRemaining();

    return result;
}
    
    public String interactWithBot(int botNumber, String message){
        if(botNumber >= 0 && botNumber < bots.size()) {
            ChatBot selectedBot = bots.get(botNumber);
            return selectedBot.prompt(message);
        } else {
            return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        }
    }
}