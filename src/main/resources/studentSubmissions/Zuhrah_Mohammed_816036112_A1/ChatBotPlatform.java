// 816036112 COMP 2603 Assignment 1

import java.util.ArrayList;

public class ChatBotPlatform{
    
    //attribute
    private ArrayList<ChatBot> bots;
    
    //Methods
    
    //constructor
    public ChatBotPlatform(){
        this.bots = new ArrayList<>();
    }
    
    //accessor
    public ArrayList<ChatBot> getBots(){
        return bots;
    }
    
    
    public boolean addChatBot(int LLMcode){
        
        //checking if platform has exceeded max number of chatbots
        if (bots.size() >= 10){
            return false;
        }
        
        //checking whether the limit has been reached for the number of messages that can be sent by the platform
        if (ChatBot.limitReached()){
            return false;
        }
        
        // creating new chatbot object based on LLM code supplied
        ChatBot newBot = new ChatBot(LLMcode);
        
        //adding new chatbot object to the bots collection using add method from ArrayList
        bots.add(newBot);
        // method successful in adding new chatbot object to bots collection based on LLM code
        return true;
    }
    
    
    public String getChatBotList(){
        
        String chatBotList = "Your ChatBots \n";
        
        //iterating through the chatbots
        for (int i = 0; i < bots.size(); i++){
            // get function returns element at position i in the array list and prints in format specified in toString method
            chatBotList += "Bot Number: " + i +" " + bots.get(i).toString() + "\n";
        }
        
        //calculating summary usage statistics
        int totalUsedMessages = 0;
        for (int i = 0; i < bots.size(); i++){
            // getting current object from bots at index i
            ChatBot bot = bots.get(i);
    
            totalUsedMessages += bot.getNumResponsesGenerated();
        }
        
        
        int totalMessagesRemaining = ChatBot.getTotalNumMessagesRemaining();

        chatBotList += "Total Messages Used: " + totalUsedMessages + "\n";
        chatBotList += "Total Messages Remaining: " + totalMessagesRemaining + "\n";

        return chatBotList;
    }
    
    
    public String interactWithBot(int botNumber, String message){
        
        // checking if supplied bot number is valid
        if (botNumber < 0 || botNumber >= bots.size()){
            return "Incorrect Bot Number (" + botNumber + ") selected:( Try again";
        }
        
        // getting chatbot at index indicated by botNumber
        ChatBot bot = bots.get(botNumber);
        
        //passing message to given chatbot and returning its response
        return bot.prompt(message);
    }
}


// Resources

// https://www.w3schools.com/java/
// https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html