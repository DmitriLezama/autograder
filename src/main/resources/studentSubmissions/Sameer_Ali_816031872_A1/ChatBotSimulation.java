//Sameer Ali 816031872
public class ChatBotSimulation {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        ChatBotPlatform chatBotPlatform = new ChatBotPlatform();
        
        chatBotPlatform.addChatBot(0); // ChatGPT-3.5
        chatBotPlatform.addChatBot(1); // LLaMa
        chatBotPlatform.addChatBot(2); // Mistral7B
        chatBotPlatform.addChatBot(3); // Bard
        chatBotPlatform.addChatBot(4); // Claude
        chatBotPlatform.addChatBot(5); // Solar
        chatBotPlatform.addChatBot(0); // ChatGPT-3.5

        System.out.println("----------------------\n" + chatBotPlatform.getChatBotList() + "\n----------------------");
 
        for (int i = 0; i < 15; i++) {
            if(i != 10){ 
                int randomBotNumber = (int) (Math.random() * chatBotPlatform.bots.size());
                String response = chatBotPlatform.interactWithBot(randomBotNumber, "User message");
                System.out.println(response);
            }
            //to show what would happen if the botNumber was invalid
            else{ 
                String response = chatBotPlatform.interactWithBot(7, "User message");
                System.out.println(response);
            }
        }
        
        System.out.println("----------------------\n" + chatBotPlatform.getChatBotList() + "\n----------------------"); 
    } 
}
//https://www.w3schools.com/java/java_math.asp
//used for random, decided to try a new method for random instead of the method used in lab 1