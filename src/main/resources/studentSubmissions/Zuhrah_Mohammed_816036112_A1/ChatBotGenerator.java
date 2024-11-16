// 816036112 COMP 2603 Assignment 1

public class ChatBotGenerator{
    
    public static String generateChatBotLLM(int LLMCodeNumber){
        String chatBotName;
        
        if (LLMCodeNumber == 1){
            chatBotName = "LLaMa";
        }
        else if (LLMCodeNumber == 2){
            chatBotName = "Mistral7B";
        }
        else if (LLMCodeNumber == 3){
            chatBotName = "Bard";
        }
        else if (LLMCodeNumber == 4){
            chatBotName = "Claude";
        }
        else if (LLMCodeNumber == 5){
            chatBotName = "Solar";
        }
        else{
            chatBotName = "ChatGPT-3.5";
        }
        
        return chatBotName;
    }
}