//816034693
import java.util.Random;

public class ChatBotSimulation {
    public static void main(String args[]) {
        System.out.println("Hello World"); // print Hello World to the screen
        
        ChatBotPlatform platform = new ChatBotPlatform(); //initializes the bot platform and makes atleast one of each type
        for (int i = 0; i < 6; i++) {
            platform.addChatBot(i % 7);
        }

        Random r = new Random();    // now that there is atleast one of each type, randomly generate (from 0 to 9) extra bots
        int extraBots = r.nextInt(10); // number of extra bots
        for (int i =0; i < extraBots; i++){ 
            platform.addChatBot(r.nextInt(7));
        }


        System.out.println(platform.getChatBotList());
        int selection;
        for (int i = 0; i < 15; i++) { // interacting up to 15 times with a random bot
            selection = r.nextInt(15); // range for bot selection is up to 15
            System.out.println(platform.interactwithBot(selection, "Hello")); 
        }

        System.out.println("");
        System.out.println(platform.getChatBotList());
    }

}