
import java.util.ArrayList; //Learnt through the use of W3 Schools - https://www.w3schools.com/java/java_arraylist.asp
import java.util.Random;
import java.lang.*;
public class ChatBotPlatform{
    private ArrayList<ChatBot> bots;
    public ChatBotPlatform(){
        this.bots = new ArrayList<ChatBot>();
    }
    public boolean addChatBot(int LLMCode){
        if(bots.size() < 7){
            ChatBot newBot = new ChatBot(LLMCode);
            bots.add(newBot);
            return true;
        } else{
            return false;
        }
    }
    private int getMessagesUsed(){
        int numMessagesUsed = 0;
        for(ChatBot bot: bots){
            numMessagesUsed += bot.getNumResponsesGenerated();
        }
        return numMessagesUsed;
    }
    private int getMessagesRemaining(){
        int messagesRemaining = 0;
        for(ChatBot bot: bots){
            messagesRemaining += bot.getTotalNumMessagesRemaining();
        }
        return messagesRemaining;
    }
    public String getChatBotList(){
        StringBuilder result = new StringBuilder();//("Hello World!\n---------------------\nYour ChatBots\n");
        for(int i = 0; i < bots.size(); i++){
            result.append("Bot Number: ").append(i).append("  ").append(bots.get(i).toString()).append("\n");
        }
        result.append("Total Messages Used: ").append(getMessagesUsed()).append("\n");
        result.append("Total Messages Remaining: ").append(getMessagesRemaining()).append("\n----------------------------\n");
        return result.toString();
    }
    public String interactWithBot(int botNumber, String message){
        if(botNumber >= 0 && botNumber < bots.size()){
            return bots.get(botNumber).prompt(message);
        } else{
            return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        }
    }
    public String toString(){
        StringBuilder result = new StringBuilder(getChatBotList());
        Random r = new Random();
        for(int i = 0; i <= 15; i++){
            int randBotNumber  = r.nextInt(bots.size());
            String response = interactWithBot(randBotNumber, "Message #" + i);
            result.append(response).append("\n");
        }
        result.append(getChatBotList());
        return result.toString();
    }

}
