//Student ID: 816037199
import java.util.Scanner;
import java.util.Random;

public class ChatBotSimulation{
    public static void main(String[] args){
        String response = "Hi Bots"; 
        Random r = new Random();
        
        System.out.println("Hello World!");
        System.out.println("----------------------");
        
        //initialization of ChatBptPlatform Object
        ChatBotPlatform simulation = new ChatBotPlatform();
        
        //for loop creating various Chatbots
        for(int i=0; i<7; i++){
            simulation.addChatBot(i);
        }
        
        //Output of ChatBot List
        String chatBotInfo = simulation.getChatBotList();
        System.out.println(chatBotInfo);
        System.out.println("----------------------");
        
        //Interactions with Chatbots, choosing random chatbot to interact with
        for(int i=0; i < 16; i++){
            int randomBotNumber = r.nextInt(10);
            System.out.println (simulation.interactWithBot(randomBotNumber, response));
        }
        
        //Outputting updated Chatbot information
        System.out.println("----------------------");
        chatBotInfo = simulation.getChatBotList();
        System.out.println(chatBotInfo);
        System.out.println("----------------------");
        
        
    }
}