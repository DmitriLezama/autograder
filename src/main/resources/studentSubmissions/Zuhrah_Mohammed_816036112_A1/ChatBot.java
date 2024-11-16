// 816036112 COMP 2603 Assignment 1

public class ChatBot{
    
    // declaring attributes
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
    
    // Methods
    
    // constructor
    public ChatBot(){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(0);
    }
    
    //constructor
    public ChatBot(int LLMCode){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
    }
    
    //accessor
    public String getChatBotName(){
        return chatBotName;
    }
    
    //accessor
    public int getNumResponsesGenerated(){
        return numResponsesGenerated;
    }
    
    //accessor
    public static int getTotalNumResponsesGenerated(){
        return messageNumber;
    }
    
    
    public static int getTotalNumMessagesRemaining(){
        // calculating and returning difference between messages generated overall and the limit allowed
        return messageLimit - messageNumber;
    }
    
    
    public static boolean limitReached(){
        // checking whether limit of messages has been reached
        if (messageNumber >= messageLimit){
            return true;
        }
        return false;
    }
    
    
    private String generateResponse(){
        
        String response;
        
        messageNumber++;
        numResponsesGenerated++;
        
        response = "(Message# " + messageNumber + 
                   ") Response from " + chatBotName + " >> generatedTextHere";
        
        return response;          
    }
    
    
    public String prompt(String requestMessage){
        
        if (messageNumber >= messageLimit){
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        }
        else {
            return generateResponse();
        }
    }
    
    
    public String toString(){
        return "ChatBot Name: " + chatBotName + "    Number Messages Used: " + numResponsesGenerated;
    }
}