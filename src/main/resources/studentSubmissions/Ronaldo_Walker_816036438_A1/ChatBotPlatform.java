//816036438

//ChatBotPlatform class

import java.util.ArrayList;

public class ChatBotPlatform{
    
    private ArrayList<ChatBot> bots;
    
    // Creating the ChatBotPlatform class constructor
    public ChatBotPlatform(){
        
        bots = new ArrayList<>();
        
    }
    
    //Creating the ChatBotPlatform accessor
    public ArrayList<ChatBot> getBots(){
        return this.bots;
    }
    
    //Creating the addChatBot method
    public boolean addChatBot(int LLMcode){
        
        ChatBot newBot = new ChatBot(LLMcode);
        
        if(newBot.limitReached()){
            return false;
        }
        else{
            bots.add(newBot);
            
            return true;
        }
        
    }
    
    //Creating the getChatBotList method
    public String getChatBotList(){
        String summary = "------------------------\n";
        summary = summary + "Your ChatBots.\n";
        
        ArrayList<ChatBot> list = getBots();
        
        int size = list.size();
        
        ChatBot temp = list.get(0);
        
        if(size > 0){
            
            for(int i=0; i < size; i++){
            
            temp = list.get(i);
            
            String name = temp.getChatBotName();
            int numMessages = temp.getNumResponsesGenerated();
            
            summary = summary + "Bot Number: " + i + "  " + "ChatBot Name: " + name + "         Number Messages Used: " + numMessages + "\n";
            
            }
            
        }
        
        summary = summary + "Total Messages Used: " + temp.getTotalNumResponsesGenerated() + "\n";
        summary = summary + "Total Messages Remaining: " + temp.getTotalNumMessagesRemaining() + "\n";
        summary = summary + "------------------------";
        
        return summary;
    }
    
    //Creating the interactWithBot method
    public String interactWithBot(int botNumber, String message){
        
        ArrayList<ChatBot> list = getBots();
                
        int size = list.size();
        
        String error = "Incorrect Bot Number(" + botNumber + ") Selected. Try again";
        
        if(botNumber >= size){
            
            return error;
        }
        
        ChatBot bot = list.get(botNumber);
        
        return bot.prompt(message);
        
    }
    
    
    
    
}