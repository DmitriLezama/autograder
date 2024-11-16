//Student ID- 816035867
import java.util.Scanner;

public class ChatBot{
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
    
    public ChatBot(){
        this.chatBotName = "ChatGPT-3.5";
        this.numResponsesGenerated = 0 ;
        this.messageLimit = 10;
        this.messageNumber = 0;

    }

    public ChatBot(int LLMCode){
        ChatBotGenerator generator = new ChatBotGenerator();
        this.chatBotName = generator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0 ;
        this.messageLimit = 10;
        this.messageNumber = 0;
    }
    
    public String getChatBotName(){
        return chatBotName;
    }
    
    public int getNumResponsesGenerated(){
        return numResponsesGenerated;
    }
    
    public static int getTotalNumResponsesGenerated() {
        return messageNumber;
    }

    public static int getTotalNumMessagesRemaining() {
        return messageLimit - messageNumber;
    }

    public static boolean limitReached() {
        return messageNumber >= messageLimit;
    }
    
    private String generateResponse(){
        numResponsesGenerated = numResponsesGenerated + 1;
        messageNumber = messageNumber + 1;
        String chatBotName = getChatBotName();
        String response = " Response from " + chatBotName + " >> generatedTextHere";
        return("(Message# " + messageNumber + ")" + response);
    }
    
    public String promptString(String requestMessage){
        if (!limitReached()) {
            return generateResponse();
        } 
        else {
            return "\nDaily Limit Reached. Wait 24 hours to resume chatbot usage";
        }
    }
    
    
    public String toString(){
        return("ChatBot Name: " + chatBotName + "Number Messages Used: " + getTotalNumResponsesGenerated());
    }    
    
}
