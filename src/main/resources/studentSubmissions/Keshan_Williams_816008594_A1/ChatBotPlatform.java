//816008594
//ChatBotPlatform

import java.util.ArrayList;

public class ChatBotPlatform{
    //Attributes
    private ArrayList<ChatBot> bots;
    
    //Constructor
    public ChatBotPlatform(){
        this.bots = new ArrayList<ChatBot>();
        
    }
    
    //Accessor
    public ArrayList<ChatBot> getBots(){
        return bots;
    }
    
    //Methods
    public boolean addChatBot(int LLMCode){
        if(bots.size()>0){
            ChatBot botDefault = bots.get(0);
            if(botDefault.limitReached()){
                return false;
            }
        }
        
        ChatBot newBot = new ChatBot(LLMCode);
        bots.add(newBot);
        
        return true;
    }
    
    public String getChatBotList(){
        String sentence = "----------------------\nYour ChatBots\n";
        
        if(bots.size()>0){
            int botCount = 0;
            
            for(ChatBot bot : bots){
                sentence += "Bot Number: " + botCount + " ChatBot Name: " + bot.getChatBotName() + "\tNumber Messages Used: " +
                bot.getNumResponsesGenerated() + "\n";
                botCount = botCount + 1;
            }
            
            ChatBot bot = bots.get(0);
            sentence += "Total Messages Used: " + bot.getTotalNumResponsesGenerated() + "\nTotal Messages remaining: " + bot.getTotalNumMessagesRemaining()
            + "\n----------------------";
        }
        else{
            sentence += "There are no bots\n" + "Total Messsages Used: 0\n" + "Total Messages Remaining: 0" + "\n----------------------";
            
        }
        
        return sentence;
    }
    
    public String interactWithBot(int botNumber,String message){
        
        if(botNumber>=0 && botNumber < bots.size()){
            return bots.get(botNumber).prompt(message);
            
        }
        else{
            return "Incorrect Bot Number("+botNumber+")Selected. Try again";
        }
    }
}

/* 
Sources:
Dr. Phaedra's slides
Methods - https://www.w3schools.com/java/java_methods.asp
ArrayList - handout from my elearning.
Access modifiers - https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
.size() method - https://www.geeksforgeeks.org/list-size-method-in-java-with-examples/

*/
 