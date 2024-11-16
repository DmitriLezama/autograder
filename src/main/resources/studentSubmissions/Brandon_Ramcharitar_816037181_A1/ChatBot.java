//Brandon Ramcharitar 816037181
import java.util.Random;
public class ChatBot{
    //Attributes
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit;
    private static int messageNumber;
    
    //Constructors
    public ChatBot(){
        this.chatBotName = "ChatGPT-3.5";
        this.numResponsesGenerated = 0;
        this.messageLimit = 10;
        this.messageNumber = 0; 
    }
    
    public ChatBot(int LLMCode){
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
        this.messageLimit = 10;
        this.messageNumber = 0;
    }
    
    public String getChatBotName(){
        return chatBotName;
    }
    
    public int getNumResponsesGenerated(){
        return numResponsesGenerated;
    }
    
    public static int getTotalNumResponsesGenerated(){
        return ChatBot.messageNumber;
    }
    
    public static int getTotalNumMessagesRemaining(){
        int remainder = ChatBot.messageLimit - getTotalNumResponsesGenerated();
        return remainder;
    }

    public static boolean limitReached(){
        if(getTotalNumMessagesRemaining() == 0){
            return true;
        }
        return false;
    }
    
    private String generateResponse(){
        Random rand = new Random();
        String[] responses = {"Hello! How can I assist you today?","Hi there! What brings you here?", "Greetings! What can I do for you?",
        "Hey! I hope you're having a great day.", "Hi, how's it going? Anything on your mind?", "Good morning! Ready for a chat?", 
        "Hello! Is there something specific you'd like to discuss?", "Greetings and salutations! What's new with you?", 
        "Hey, good to see you! What's going on?", "Hi! How can I make your day a little brighter?"
        };  //Using a string array to generate random responses
        
        this.numResponsesGenerated += 1;
        ChatBot.messageNumber += 1;
        return("(Message #" + ChatBot.messageNumber + ") Response from " + this.getChatBotName() + " >> " + responses[rand.nextInt(9)]);
    }
    
    public String prompt(String requestMessage){
        if(!limitReached()){
            return (generateResponse());
        }
        return ("Daily Limit Reached. Wait 24 hours to resume chatbot usage");
    }
    
    public String toString(){
        return (" ChatBot Name: " + getChatBotName() + "     Number Messages Used: " + getNumResponsesGenerated());
    }
    
}