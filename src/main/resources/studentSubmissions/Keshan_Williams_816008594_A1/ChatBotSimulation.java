//816008594
//ChatBotSimulation Class

import java.util.Random;

public class ChatBotSimulation{
    
    public static void main(String[] args){
        //Computer Science is a cult and "Hello World!" is how you join it
        System.out.println("Hello World!");
        
        //Declaring and initializing ChatBotPlatform object.
        ChatBotPlatform botPlatform = new ChatBotPlatform();
        
        //Adding bots to the Bot platform
        int i = 0;
        for(i=0; i<7; i=i+1){
            botPlatform.addChatBot(i);
            
        }
        
        //Printing out the current list of chatbots and their respective message usage
        System.out.println(botPlatform.getChatBotList());
        
        //Creating new Random object for generating random numbers
        Random random = new Random();
        
        
        //Interacting with bots up to 15 times
         for (int j = 0; j< 15; j++) {
            
             //The +2 is to see if the error checking is done properly for incorrect bot number.
            int botNum = random.nextInt(botPlatform.getBots().size() + 2);
            
            String message = botPlatform.interactWithBot(botNum, "Hello :)");
            
            System.out.println(message);
        }
        
        //Prints updated list of chatbots 
        System.out.println(botPlatform.getChatBotList());
    }
}

/*
Sources:
Dr. Phaedra's slides.
Pseudorandom numbers - https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

 */