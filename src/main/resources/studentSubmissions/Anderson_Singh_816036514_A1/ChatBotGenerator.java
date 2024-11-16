//816036514 Anderson Singh
public class ChatBotGenerator{
    public static String generateChatBotLLM(int LLMCodeNumber){
        String[] bots = {"ChatGPT-3.5", "LLaMa", "Mistral7B", "Bard", "Claude", "Solar"};
        return bots[LLMCodeNumber];
    }
}