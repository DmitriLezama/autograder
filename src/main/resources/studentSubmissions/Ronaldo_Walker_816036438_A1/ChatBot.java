//816036438

import java.util.Random;

//ChatBox Class

public class ChatBot{

    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit;
    private static int messageNumber;

    //Creating the ChatBox constructors to give all the attributes values
    public ChatBot(){

        chatBotName = "ChatGPT-3.5";
        numResponsesGenerated = 0;
        messageLimit = 10;
        messageNumber = 0;
    }

    public ChatBot(int LLMCode){

        ChatBotGenerator chatbot = new ChatBotGenerator();

        chatBotName = chatbot.generateChatBotLLM(LLMCode);
        numResponsesGenerated = 0;
        messageLimit = 10;
        messageNumber = 0;
    }

    //Creating the Accessors
    public String getChatBotName(){
        return this.chatBotName;
    }

    public int getNumResponsesGenerated(){
        return this.numResponsesGenerated;
    }

    public static int getMessageLimit(){
        return messageLimit;
    }

    public static int getTotalNumResponsesGenerated(){
        return messageNumber;
    }
    
    //Creating mutators to increment attributes;
    public void setNumResponsesGenerated(){
        numResponsesGenerated = numResponsesGenerated + 1;
    }
    
    public static void setMessageNumber(){
        messageNumber = messageNumber + 1;
    }
    
    // Creating the getTotalNumMessagesRemaining method
    public static int getTotalNumMessagesRemaining(){
        
        int messageGen = getTotalNumResponsesGenerated();
        int limit = getMessageLimit();
        
        int remain = limit - messageGen;
        
        return remain;
    }
    
    // Creating the limitReached method
    public static boolean limitReached(){
        
        if(getTotalNumMessagesRemaining() > 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    // Creating the generateResponse method
    private String generateResponse(){
    
        setNumResponsesGenerated();    
        setMessageNumber();
        
        String[] prompts = new String[] {"What's your favourite color ?", "Do you like Computer Science ?", "What's your favourite game ?", "Are you a student ?", "What's your name ?", "Can you sing ?" , "Do you like to draw ?", "How tall are you ?", "Have you eaten yet ?", "Do you like hiking ?", "Do you exercise ?"};
    
        Random rand = new Random();
        int word = rand.nextInt(10);
        
        String response = "(Message# " + getTotalNumResponsesGenerated() + ") Response from " + getChatBotName() + "    >>" + prompts[word];
        
        return response;
    }
    
    //Creating the prompt method
    public String prompt(String requestMessage){
        
        String deny = "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        
        if(limitReached() == false){
            return generateResponse();
        }
        else{
            return deny;
        }
        
    }
    
    //creating the toString method
    public String toString(){
        
        return ("ChatBt Name:" + getChatBotName() + "     Number Messages Used: " + getNumResponsesGenerated());
    }
    
}
