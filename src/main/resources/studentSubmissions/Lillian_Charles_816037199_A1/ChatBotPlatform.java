//816037199
import java.util.ArrayList;

public class ChatBotPlatform{
    ArrayList<ChatBot> bots = new ArrayList<ChatBot>();
    
    //Constructor
    public ChatBotPlatform(){
        ArrayList<ChatBot> bots = new ArrayList<ChatBot>();
    }
    
    //Creates and adds a ChatBot object to the ArrayList of ChatBots
    public boolean addChatBot(int LLMCode){
        ChatBot bot = new ChatBot(LLMCode);     //calling constructor to create a new instance of a chatbot
        if(ChatBot.limitReached() == false){    //checks if daily limit reached
            bots.add(bot);                      //if the limit is not reached, chatbot will be added
            return true;
        }
        return false;
    }
    
    //Returns the list of ChatBots present in the ArrayList 
    //and basic statistical data
    public String getChatBotList(){
        String list = ("Your ChatBots" + '\n');             //initlializing a string to store data
        int botNumber = 0;
        for(ChatBot bot: bots){                             //for each loop sourced from https://howtodoinjava.com/java/collections/arraylist/iterate-through-arraylist/  
            list = list + ("Bot Number: " + botNumber + " " + bot.toString()+ '\n');
            botNumber = botNumber + 1;
        }
        list = list + ("Total Messages Used: ") + ChatBot.getTotalNumResponsesGenerated() + '\n' + ("Total Messages Remaining: ") + ChatBot.getTotalNumMessagesRemaining() + '\n';
        return list;
    }
    
    //Passed a bot number and a message, returns a message generated
    //by a chatbot
    //If the botNumber passed is outside of the range of ChatBots
    //an error message is returned
    public String interactWithBot(int botNumber, String message){
        if(botNumber >= bots.size()){
            return ("Incorrect Bot Number(" + botNumber + ") Selected. Try again");
        }
        ChatBot bot = bots.get(botNumber);
        return bot.prompt(message);
    }
}



//Sources
//https://www.geeksforgeeks.org/arraylist-in-java/
//https://howtodoinjava.com/java/collections/arraylist/iterate-through-arraylist/
