/*816037026 Akash Nimchan*/
import java.util.ArrayList; //Learnt through the use of W3 Schools - https://www.w3schools.com/java/java_arraylist.asp
import java.util.Random;
import java.lang.*;
public class ChatBot{
    //Attributes
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber;
    
    //Accessor Methods
    public String getChatBotname(){return chatBotName;}
    public int getNumResponsesGenerated(){return numResponsesGenerated;}
    public int getTotalNumResponsesGenerated(){return messageNumber;}
    public int getTotalNumMessagesRemaining(){
        return messageLimit - messageNumber;
    }
    
    //Constructors
    public ChatBot(){
        this.chatBotName = "ChatGPT-3.5";
        this.numResponsesGenerated = 0;
        this.messageLimit = 3;
        this.messageNumber = 0;
    }
    public ChatBot(int LLMCode){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
        this.messageLimit = 3;
        this.messageNumber = 0;
    }
    public boolean limitReached(){
        if(messageNumber < messageLimit){
            return false;
        } else{
            return true;
        }
    }
    private String generateResponse(){
        if(!limitReached()){
            numResponsesGenerated++;
            messageNumber++;
            return "(Message# " + messageNumber + ")  Response from " + chatBotName + " >> generatedTextHere";
        } else{
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        }
        
    }
    public String prompt(String requestMessage){return generateResponse();}
    public String toString(){
        return "ChatBot Name: " + chatBotName + "           Number of messages used: " + numResponsesGenerated;
    }
    
}
    
    
