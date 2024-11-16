//Student Name: Deepthi Valachery
//Student ID: 816035457

import java.util.Random;

public class ChatBot {
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
        
    public ChatBot() {
        this.chatBotName = "ChatGPT-3.5";
        this.numResponsesGenerated = 0;
    }
    
    public ChatBot(int LLMCode) {
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
    }
    
    //Accessors
    public String getChatBotName() {return chatBotName;}
    public int getNumResponsesGenerated(){return numResponsesGenerated;}
    public static int getTotalNumResponsesGenerated() {return messageNumber;} //class method
    public static int getTotalNumMessagesRemaining() {return messageLimit-messageNumber;} //class method
    
    public static boolean limitReached() {
        if(messageNumber >= messageLimit){
            return true;
        }else{
            return false;
        }
    } // class method
    
    //String[] words = {"Hi", "Hello!", "Sorry I didn't get that", "I don't understand", "Let's talk tomorrow"};
    //Random r = new Random();
    private String generateResponses() {
        numResponsesGenerated++;
        return ("(Message# " + ((messageNumber++)+1) + ") Response from " + chatBotName + " \t >> generateTextHere"); //+ words[r.nextInt(5)]);
    }
    
    public String prompt(String requestMessage) {
        if(!limitReached()){
            return generateResponses();
        }else{
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        }
    }
    
    public String toString() {
        return ("\tChatBot Name: " + chatBotName + "\tNumber Messages Used: " + numResponsesGenerated + "\n");
    }
}
