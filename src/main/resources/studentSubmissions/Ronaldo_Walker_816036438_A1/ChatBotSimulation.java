//816036438

//ChatBotSimulation class / The main

import java.util.Random;

public class ChatBotSimulation{
    
    //Creating the main
    public static void main(String[] args){
        
        String[] input = new String[] {"Hello", "I'm fine", "Yes I do", "I think so ?", "Don't know"};
        
        System.out.println("Hello World!\n");
        
        ChatBotPlatform chatPlatform = new ChatBotPlatform();
        
        for(int i = 0; i < 6; i++){
            
            chatPlatform.addChatBot(i);
        }
        
        System.out.println(chatPlatform.getChatBotList());
        
        
        for(int i = 0; i < 15; i++){
            
            Random rand1 = new Random();
            int botNum = rand1.nextInt(7);
            int message = rand1.nextInt(4);
            
            System.out.println(chatPlatform.interactWithBot(botNum, input[message]));
            
        }
        
        System.out.println(chatPlatform.getChatBotList() + "\n");
        
    }
    
    
    
}