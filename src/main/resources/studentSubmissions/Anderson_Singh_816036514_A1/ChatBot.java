//816036514 Anderson Singh
import java.util.ArrayList;
public class ChatBot{
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
    
    public String getChatBotName(){return this.chatBotName;}
    public int getNumResponsesGenerated(){return this.numResponsesGenerated;}
    public int getMessageLimit(){return this.messageLimit;}
    public static int getTotalNumMessagesRemaining(){ return messageLimit - messageNumber;}
    public static int getTotalNumResponsesGenerated(){return messageNumber;}
    
    public ChatBot(){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(0);
    }
    public ChatBot(int LLMCode){
        chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
    }
    public static boolean limitReached(){
        if (messageNumber < messageLimit){
            return false;
        }
        else{
            return true;
        }
    }
    public String toString(){
        return "ChatBot Name: " + this.chatBotName + "   Number of Messages used: " + this.getNumResponsesGenerated();
    }
    private String generateResponse(){
        numResponsesGenerated += 1;
        messageNumber += 1;
        return "(Message #" + messageNumber + ") Response from " + chatBotName + " >>generatedTextHere";
    }
    public String prompt(String requestMessage){
        if(limitReached() == true){
            return "Daily limit reached, Wait 24 hours.";
        }
        else{
            return generateResponse();
        }
    }
    
    
    
}