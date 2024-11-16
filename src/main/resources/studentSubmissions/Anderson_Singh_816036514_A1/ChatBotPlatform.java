//816036514 Anderson Singh
import java.util.ArrayList;
public class ChatBotPlatform{
   private static ArrayList<ChatBot> bots;
   
   public ChatBotPlatform(){
       this.bots = new ArrayList<>();
   }
   public ArrayList<ChatBot> getBots() {
        return bots;
}
   public boolean addChatBot(int LLMcode){
       if(!ChatBot.limitReached()){
           ChatBot bot = new ChatBot(LLMcode);
           bots.add(bot);
           return true;
       }
       else{
           return false;
       }
       
   }
   public String getChatBotList(){
       StringBuilder print = new StringBuilder();
       print.append("Your ChatBots\n");
       int totalMsg = 0;
       for(int i=0; i<bots.size(); i+=1){
           ChatBot curr = bots.get(i);
           print.append("Bot Number: ").append(i).append(" ");
           print.append(curr.toString()).append("\n");
           totalMsg += curr.getNumResponsesGenerated();
       }
       print.append("Total Messages Used: ").append(totalMsg).append("\n");
       print.append("Total Messages Remaining: ").append(ChatBot.getTotalNumMessagesRemaining()).append("\n");
       
       return print.toString();
   }
   public static String interactWithBot(int botNumber, String message){
       if(botNumber >= 0 && botNumber < bots.size()){
           ChatBot selected = bots.get(botNumber);
           return selected.prompt(message);
       }
       else {
           return "Incorrect Bot Number (" + botNumber + ") Selected, Try again.";
       }
       
       
    }
   
}
// String builder researched from W3schools
//https://www.w3schools.blog/append-stringbuilder-method-in-java
// Array list researched from W3schools 
//https://www.w3schools.com/java/java_arraylist.asp