// 816037198
public class ChatBot {
    // ChatBot Attributes
    private String chatBotName;
    private int numResponsesGenerated;
    private static final int messageLimit = 10;
    private static int messageNumber = 0;
    
    // ChatBot Constructor
    public ChatBot () {
        ChatBotGenerator generator = new ChatBotGenerator();
        chatBotName = generator.generateChatBotLLM(0);
        numResponsesGenerated = 0;
    }
    // ChatBot Overloaded Constructor
    public ChatBot (int LLMCode) {
        ChatBotGenerator generator = new ChatBotGenerator();
        chatBotName = generator.generateChatBotLLM(LLMCode);
        numResponsesGenerated = 0;
    }
    // ChatBot Accessors
    public String getChatBotName () { return chatBotName;}
    
    public int getNumResponsesGenerated () { return numResponsesGenerated;}
    
    public static int getMessageLimit () { return messageLimit;}    
    
    public static int getTotalNumResponsesGenerated () { return messageNumber;}
    
    // returns the remaining number of messages a user can send based on the daily limit
    public static int getTotalNumMessagesRemaining () { return getMessageLimit() - getTotalNumResponsesGenerated();}    
    // checks if the daily limit has been reached
    public static boolean limitReached () {
        if (getTotalNumMessagesRemaining() == 0) {
            return true;
        }
        return false;
    }
    // generates response from ChatBot
    private String generateResponse () {
        numResponsesGenerated++;            // increments the number of responses generated by the chat bot
        messageNumber++;                    // increments the number of responses generated by all bots in the ChatBot class
        
        return "(Message #" + getTotalNumResponsesGenerated() + ") Response from " + getChatBotName() + "\t>> generatedTextHere";
    }
    // returns string response to user's message
    public String prompt(String requestMessage) {
        if (!limitReached()) {
            return generateResponse();
        }
        return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
    }
    // returns formatted information on a ChatBot
    public String toString () {
        return "ChatBot Name: " + getChatBotName() + "\tNumber Messages Used: " + getNumResponsesGenerated() + "\n";
    }
} 