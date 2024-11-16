//Student ID- 816035867
import java.util.ArrayList;
import java.util.Random;

public class ChatBotGenerator{
    public String generateChatBotLLM (int LLMCodeNumber){
        String[] LLMNames = {"ChatGPT-3.5", "LLaMa", "Mistral7B", "Bard","Claude", "Solar"};
        if (LLMCodeNumber >= 0 && LLMCodeNumber < LLMNames.length) {
            return LLMNames[LLMCodeNumber];
        } else {
            return "ChatGPT-3.5"; 
        }
    }

}