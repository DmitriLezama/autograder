//Sameer Ali 816031872
public class ChatBot{
    //Attributes
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
   
    //constructor
    public ChatBot(){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(0);
        this.numResponsesGenerated = 0;
    }
    
    public ChatBot(int LLMCode) {
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
    }
        
    //Accessors
    public String getChatBotName(){
        return chatBotName;
    }
    public int getNumResponsesGenerated(){
        return numResponsesGenerated;
    }
    public static int getTotalNumResponsesGenerated(){
        return messageNumber;
    }
    public static int getTotalNumMessagesRemaining(){
        return messageLimit - messageNumber;
    }
    public static boolean limitReached(){
        if(messageNumber >= messageLimit)
            return true;
        else 
            return false;
    }    
    private String generateResponse(){
            numResponsesGenerated++;
            messageNumber++;
            return "(Message# " + messageNumber + " ) Response from " 
            + chatBotName + " >>generatedTextHere"; 
    }
    public String prompt(String requestMessage){
        if(!limitReached())
           return generateResponse();
        else 
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
    }
    public String toString(){
        return "ChatBot Name: " + chatBotName + " Number Messages Used: " + numResponsesGenerated;
    }
}