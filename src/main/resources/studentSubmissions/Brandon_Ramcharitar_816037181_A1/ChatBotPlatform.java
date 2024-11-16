//Brandon Ramcharitar 816037181
import java.util.ArrayList;
public class ChatBotPlatform{
    ArrayList<ChatBot> bots = new ArrayList<ChatBot>();     //Used ArrayList notes on myElearning
    
    public ChatBotPlatform(){
        ArrayList<ChatBot> bots = new ArrayList<ChatBot>();
    }
    
    public boolean addChatBot(int LLMCode){
        ChatBot cb = new ChatBot(LLMCode);
        if(!ChatBot.limitReached()){
            bots.add(cb);
            return true;
        }
        return false;
    }
    
    public String getChatBotList(){
        String message = "";
        int num = 0;
        message += ("---------------");
        message +=("\nYour ChatBots\n");
        for(ChatBot cb: bots){  
            message += "Bot Number: " + num;
            message += cb.toString()+ "\n";
            num = num + 1;
        }
        message += ("Total Messages Used: ") + ChatBot.getTotalNumResponsesGenerated() + "\n";
        message += ("Total Messages Remaining: ") + ChatBot.getTotalNumMessagesRemaining() + "\n";
        message +=("---------------");
        return message;
    }
    
    public String interactWithBot(int botNumber, String message){
        if(botNumber >= bots.size()){
            return ("Incorrect Bot Number(" + botNumber + ") Selected. Try again");
        }
        ChatBot cb = bots.get(botNumber);
        return cb.prompt(message);
    }
}
