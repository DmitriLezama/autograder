//Student Name: Deepthi Valachery
//Student ID: 816035457

import java.util.Random;
//import java.util.Scanner;

public class ChatBotSimulation {
    public static void main (String[] args) {
        System.out.println("Hello World!" + "\n-------------------------");
        
        ChatBotPlatform chatBotPlatform = new ChatBotPlatform();
 
        // Add several ChatBot objects to the ChatBotPlatform
        for(int j=0;j<=6;j++){
            chatBotPlatform.addChatBot(j);
        }
        
        //Printing the initial list of ChatBots summary statistics
        System.out.println(chatBotPlatform.getChatBotList());
        System.out.println("----------------------");
        
        
        //The bots are chatting with each other without external user input (as seen in output/as expected fromlecturer)
        String userMessage = "User Input";
        int botSize = chatBotPlatform.getChatbotList().size() + 1;
        for(int i=0;i<15;i++){
            int randomBotNumber = (int) (Math.random() * botSize);
            String response = chatBotPlatform.interactWithBot(randomBotNumber, userMessage);
            System.out.println(response);
        }
        
        /*
        //The bots are 'responding' randomly to user input
        Scanner scanner = new Scanner(System.in);
        int interactionCount = 0;
        int botSize = chatBotPlatform.getChatbotList().size();

        while (interactionCount < 15) {
            String userMessage = scanner.nextLine();

            int randomBotNumber = (int) (Math.random() * botSize);
            String response = chatBotPlatform.interactWithBot(randomBotNumber, userMessage);
            System.out.println(response);

            interactionCount++;
        }
        */

        //The final list of ChatBots summary statistics
        System.out.println("----------------------");
        System.out.println(chatBotPlatform.getChatBotList());
        System.out.println("----------------------");
    }
}