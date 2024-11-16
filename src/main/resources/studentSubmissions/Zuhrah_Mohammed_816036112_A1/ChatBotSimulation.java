// 816036112 COMP 2603 Assignment 1

import java.util.Random;

public class ChatBotSimulation{
    
    public static void main (String[] args){
        
       //1.
       System.out.println("Hello World!");
       System.out.println("----------------------");
       
       //2. declaring and initialising ChatBotPlatform object
       ChatBotPlatform platformObject = new ChatBotPlatform();
       
       //3. adding several ChtBot objects to the ChatBotPlatform
       platformObject.addChatBot(0);
       platformObject.addChatBot(1);
       platformObject.addChatBot(2);
       platformObject.addChatBot(3);
       platformObject.addChatBot(4);
       platformObject.addChatBot(5);
       platformObject.addChatBot(6);
       
       //4. printing list of all ChatBots managed by the ChatBotPlatform with their summary statistics
       System.out.println(platformObject.getChatBotList());
       System.out.println("----------------------");
       
       //5. interacting 15 times with random ChatBots by sending messages and printing out their responses
       Random random = new Random();
       
       for (int i = 1; i <= 15; i++){
           
           int index = random.nextInt(platformObject.getBots().size());
           String response = platformObject.interactWithBot(index, "message");
           
           System.out.println(response);
       }
       
       //6. printing the final list of all ChatBots managed by the ChatBotPlatform with their summary statistics
       System.out.println("----------------------");
       System.out.println(platformObject.getChatBotList());
       System.out.println("----------------------");
    }
}