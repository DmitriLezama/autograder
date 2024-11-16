
// 816035591 Dmitri Lezama
import java.util.Random;

public class ChatBotSimulation {
    // This should be private however the instructions somewhat indicated that
    // methods should remain public to allow for unit testing.
    public static void initBots(ChatBotPlatform chatBotPlatform) {
        int numBots = new Random().nextInt(6, 10); // Ensure at least 6 bots
        for (int i = 0; i < numBots; i++)
            if (chatBotPlatform.addChatBot(i) == false)
                System.out.println("Failed to add bot");
    }

    public static void interactWithBot(ChatBotPlatform chatBotPlatform) {
        for (int i = 0; i < 15; i++) {
            int randomBotNumber = new Random().nextInt(10);
            String response = chatBotPlatform.interactWithBot(randomBotNumber, "User message #" + i);
            System.out.println(response);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ChatBotPlatform chatBotPlatform = new ChatBotPlatform();
        initBots(chatBotPlatform);

        String initialSummary = chatBotPlatform.getChatBotList();
        System.out.print(initialSummary);

        interactWithBot(chatBotPlatform);

        String finalSummary = chatBotPlatform.getChatBotList();
        System.out.println(finalSummary);
    }
}

// all information is sources from
// Dr. Phaedra Mohammed's slides