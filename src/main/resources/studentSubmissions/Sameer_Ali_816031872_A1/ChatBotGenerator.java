//Sameer Ali 816031872
public class ChatBotGenerator {

    // Class method to generate chatbot names based on LLM codes
    public static String generateChatBotLLM(int LLMCodeNumber) {
        switch (LLMCodeNumber) {
            case 1:
                return "LLaMa";
            case 2:
                return "Mistral7B";
            case 3:
                return "Bard";
            case 4:
                return "Claude";
            case 5:
                return "Solar";
            default:
                return "ChatGPT-3.5";
        }
    }
}
//https://www.w3schools.com/java/java_switch.asp
//used for switch syntax