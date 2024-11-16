// 816037198
import java.util.Random;

public class ChatBotSimulation {
    
    public static void main (String[] args) {
        
        System.out.println("Hello World!");

        ChatBotPlatform platform = new ChatBotPlatform();               // declare and initialise ChatBotPlatform
        
        Random r = new Random();                                        // declare and initalise Random object
        int maxBots = 10;
        int minBots = 6;
        int numBots = r.nextInt(minBots, maxBots + 1);                  // generates random number of bots to add to platform
        
        // adds bots of each kind to ChatBot
        for (int i = 0; i < minBots; i++) {
            // uses [i] as the LLM code to add bots to the platform
            if (!platform.addChatBot(i)) {
                System.out.println("Unable to add ChatBot to Platform");
            }                                   
        }     
        
        // adds other bots to platform if numBots is more than 6
        for (int j = 0; j < numBots - minBots; j++) {
            // uses random object to generate random LLM codes to add bots to the platform
            // anything other than [1,2,3,4,5] will result in a ChatBot "ChatGPT-3.5"
            if (!platform.addChatBot(r.nextInt(maxBots))) {
                System.out.println("Unable to add ChatBot to Platform");
            }                 
        }
             
        // prints initial ChatBot Platform information to screen
        System.out.println(platform.getChatBotList());
        
        // simulates interaction with ChatBot Platform for 15 intervals
        for (int i = 0; i < 15; i++) {
            // prints ChatBot response to "user" message
            // the Random object picks a ChatBot to interact with at each interval
            System.out.println(platform.interactWithBot(r.nextInt(maxBots+1), "UserMessageHere"));
        }

        // prints ChatBot Platform information after interaction
        System.out.println(platform.getChatBotList());
    }
    
}