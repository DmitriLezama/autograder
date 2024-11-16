//Brandon Ramcharitar 816037181

public class ChatBotGenerator{
    public static String generateChatBotLLM(int LLMCodeNumber){ 
        switch(LLMCodeNumber){      //Used w3schools.com for switch operation
            case 1:
                return("LLaMa");
            case 2:
                return("Mistral7B");
            case 3:
                return("Bard");
            case 4:
                return("Claude");
            case 5:
                return("Solar");
            default:
                return("ChatGPT-3.5");
        }
    }
    
    public static String generateChatBotLLM(String botName){
        return ("ChatGPT-3.5");
    }
}
