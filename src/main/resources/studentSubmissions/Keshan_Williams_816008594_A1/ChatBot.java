//816008594
//ChatBot Class
public class ChatBot{
    //Attributes
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
    
    //Constructor
    public ChatBot(){
        ChatBotGenerator chatBotGenerator = new ChatBotGenerator();
        this.chatBotName = chatBotGenerator.generateChatBotLLM(0);
        this.numResponsesGenerated = 0;
    }
    
    public ChatBot(int LLMCode){
        ChatBotGenerator chatBotGenerator = new ChatBotGenerator();
        this.chatBotName = chatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
    }
    
    //Accessors
    public String getChatBotName(){
        return chatBotName;
    }
    
    
    public int getNumResponsesGenerated(){
        return numResponsesGenerated;
    }
    
    //class method
    public static int getTotalNumResponsesGenerated(){
        return messageNumber;
    }
    
    //class method
    public static int getTotalNumMessagesRemaining(){
        return (messageLimit - messageNumber);
    }
    
    //class method
    public static boolean limitReached(){
        return (messageNumber >= messageLimit);
    }
    
    private String generateResponse(){
        
        
        numResponsesGenerated = numResponsesGenerated + 1;
        
        messageNumber = messageNumber + 1;
        
        return ("Message# " + messageNumber + " Response from " + getChatBotName() + "\t>> generatedTextHere");
        
    }
    
    public String prompt(String requestMessage){
        if(limitReached()){
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        }
        else{
            return generateResponse();
            
        }
    }
    
    public String toString(){
        return "ChatBot Name: " + chatBotName + " Number Messages Used: " + numResponsesGenerated;
    }
    
    
}

/*
Sources:
Dr. Phaedra's slides
Access modifiers - https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
Class Methods - https://docs.oracle.com/javase/tutorial/java/javaOO/classvars.html
 */