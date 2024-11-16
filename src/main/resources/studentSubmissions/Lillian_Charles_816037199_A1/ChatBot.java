//Student ID: 816037199

public class ChatBot{
    //Attributes
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit;
    private static int messageNumber;
    
    //Constructor
    public ChatBot(){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(0);
        this.numResponsesGenerated = 0;
        this.messageLimit = 10;
        this.messageNumber = 0;
    }
    
    //Overloaded Constructor
    public ChatBot(int LLMCode){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
        this.messageLimit = 10;
        this.messageNumber = 0;
    }
    
    //Accessors
    public String getChatBotName() {return chatBotName;}
    public int getNumResponsesGenerated(){return numResponsesGenerated;}
    public static int getTotalNumResponsesGenerated(){return ChatBot.messageNumber;} 
    
    
    //Calculates number of messages that can still be sent
    public static int getTotalNumMessagesRemaining(){
        return ChatBot.messageLimit - getTotalNumResponsesGenerated();
    }
    
    //Checks if daily message limit is reached
    public static boolean limitReached(){
        int remaining = getTotalNumMessagesRemaining();
        if(remaining == 0){
            return true;
        }
        return false;
    }
    
    //Generates a response for a Chatbot
    private String generateResponse(){
        this.numResponsesGenerated += 1;
        ChatBot.messageNumber += 1;
        
        String response = "generatedTextHere";
        return ("(Message# " + ChatBot.messageNumber + ") Response from " + this.getChatBotName() + "      >>" + response); 
    }
    
    //Checks if the daily Limit is reached. If it isn't a
    //response is generated and returned
    public String prompt(String requestMessage){
        if(limitReached() == false){
        return generateResponse();
        }
        return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
    }
    
    
    public String toString(){
        return ("ChatBot Name: " + getChatBotName() + "     Number Messages Used: " + getNumResponsesGenerated());
    }
}


//Sources
//https://www.javatpoint.com/difference-between-static-and-non-static-in-java#:~:text=Static%3A%20Static%20members%20can%20be,member%20name%20(e.g.%2C%20objectReference.
//https://ioflood.com/blog/java-format/#:~:text=String%20Formatting%20in%20Java%3A%20The%20Basics,-In%20Java%2C%20the&text=The%20format%20string%20includes%20placeholders,f%20for%20floating%2Dpoint%20numbers.





