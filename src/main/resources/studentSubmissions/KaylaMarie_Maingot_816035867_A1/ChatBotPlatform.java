//Student ID- 816035867
import java.util.ArrayList;

public class ChatBotPlatform{
    ChatBot bot = new ChatBot();
    private ArrayList<ChatBot>bots;  
    
    public ChatBotPlatform(){
        this.bots = new ArrayList<>();
    }
    
    public boolean addChatBot(int LLMcode){
        if(!ChatBot.limitReached()){
           ChatBot newBot = new ChatBot(LLMcode);
           bots.add(newBot);            
           return true;
        }
        else{
            return false;
        }

    }
    
    public String getChatBotList(){
         String result = "";
         int botNum = 0;
         
         for (int i = 0; i < bots.size(); i++) {
             ChatBot bot = bots.get(i);
             result += "Bot Number:" + i + " ChatBot Name:" + bot.getChatBotName() + "   Number Messages Used:" + bot.getNumResponsesGenerated() + "\n";
             botNum += botNum;
        }

        result += "Total Messages Used: " + ChatBot.getTotalNumResponsesGenerated() + "\n";
        result += "Total Messages Remaining: " + ChatBot.getTotalNumMessagesRemaining() + "\n";
        
        return result;
    }
    
    public String interactWithBot(int botNumber, String message){
        if (botNumber < 0 || botNumber >= bots.size()) {
        return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        }

        ChatBot selectBot = bots.get(botNumber);
        if (selectBot != null) {
            return selectBot.promptString(message);
        } 
        else {
        return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        }
    }
}

        
  