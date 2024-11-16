
import java.util.ArrayList; //Learnt through the use of W3 Schools - https://www.w3schools.com/java/java_arraylist.asp
import java.util.Random;
import java.lang.*;
public class ChatBotSimulation{
    public static void main(String[] args){
        System.out.println("Hello World! \n----------------------------\n Your ChatBots");
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(1);
        platform.addChatBot(2);
        platform.addChatBot(3);
        platform.addChatBot(4);
        platform.addChatBot(5);
        platform.addChatBot(6);
        
        System.out.println(platform.toString());
    }

}
