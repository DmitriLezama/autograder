//Brandon Ramcharitar 816037181
import java.util.ArrayList;         
import java.util.Random;
public class ChatBotSimulation{
    public static void main(String[] args){
        Random rand = new Random();
        int botlimit = 7;       //Number of bots is atleast 6, used 7 to show the event of a default bot
        int interactLimit = rand.nextInt(15-1+1) + 1;     //Maximum number of times (15) user is allowed to interact with the bots
        
        System.out.println("Hello World!");

        ChatBotPlatform cbp = new ChatBotPlatform();
        for(int i = 0; i < botlimit; i++){
            cbp.addChatBot(i);      
        }
        System.out.println(cbp.getChatBotList());
        
        for(int x = 0; x < interactLimit; x++){
            System.out.println(cbp.interactWithBot(rand.nextInt(botlimit + 1), ""));    //Used botlimit + 1 to show the case where a choice is invalid
        }
        
        System.out.println(cbp.getChatBotList());   //Printing out all the ChatBots
    } 
}
