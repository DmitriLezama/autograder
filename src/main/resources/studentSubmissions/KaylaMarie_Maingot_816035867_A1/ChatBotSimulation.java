//Student ID- 816035867
import java.util.Random;
import java.util.ArrayList;

public class ChatBotSimulation{
    public static void main(String[] args){
        System.out.println("Hello World!");
        System.out.println("-------------------");
        
        ChatBotPlatform chatBotPlatform = new ChatBotPlatform();
        
        chatBotPlatform.addChatBot(0);
        chatBotPlatform.addChatBot(1);
        chatBotPlatform.addChatBot(2);
        chatBotPlatform.addChatBot(3);
        chatBotPlatform.addChatBot(4);
        chatBotPlatform.addChatBot(5);
        chatBotPlatform.addChatBot(6);
        
        System.out.println(chatBotPlatform.getChatBotList());
        
        System.out.println("----------------------");

        Random random = new Random();
            for(int i=0; i<15; i++){ 
                int botNumber = random.nextInt(7);
                String response = chatBotPlatform.interactWithBot(botNumber,"(Message# " + i );
                System.out.println( response);
        }
        
        System.out.println("----------------------");
        System.out.println("Your ChatBots");
        System.out.println(chatBotPlatform.getChatBotList());
    }
    
}

    
    

