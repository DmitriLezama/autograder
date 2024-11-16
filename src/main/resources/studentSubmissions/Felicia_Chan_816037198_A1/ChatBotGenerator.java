// 816037198
public class ChatBotGenerator {
    
    public static String generateChatBotLLM (int LLMCodeNumber) {
        // uses switch to return ChatBot names based on the LLM code provided
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