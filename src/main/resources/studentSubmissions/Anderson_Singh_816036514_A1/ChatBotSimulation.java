//816036514 Anderson Singh
import java.util.Random;
public class ChatBotSimulation {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("-------------------");
        ChatBotPlatform chatBotPlatform = new ChatBotPlatform();
        
        chatBotPlatform.addChatBot(0);
        chatBotPlatform.addChatBot(1); 
        chatBotPlatform.addChatBot(2);  
        chatBotPlatform.addChatBot(3);  
        chatBotPlatform.addChatBot(4);  
        chatBotPlatform.addChatBot(5);  
        
        System.out.println(chatBotPlatform.getChatBotList());
        System.out.println("--------------------");
        
        int i=0;
        while (i < 15) {
            Random rand = new Random();
            int num = rand.nextInt(10);
            String message = "";
            String response = chatBotPlatform.interactWithBot(num, message);
            System.out.println(response);
            i+=1;
        }

        System.out.println("-------------------");
        System.out.println(chatBotPlatform.getChatBotList());
    }
}
// Random researched from W3schools
//https://www.w3schools.in/java/examples/generate-random-numbers
