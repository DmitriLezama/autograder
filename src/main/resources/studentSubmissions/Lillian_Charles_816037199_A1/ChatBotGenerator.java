//Student ID: 816037199
public class ChatBotGenerator{
    private static String Default = "ChatGpt-3.5";
    //Storing the LLM names in an array to later generate the name
    private static String[] LLMs = {"", "LlaMa", "Mistral7B", "Bard", "Claude", "Solar"};
    
    public static String generateChatBotLLM(int LLMCodeNumber){
        //If the LLMCodeNumber passed is within the range of chatbot
        //names, the appropriate name will be returned
        if(LLMCodeNumber >0 && LLMCodeNumber <6){
            return LLMs[LLMCodeNumber];
        }
        
        //otherwise the default bot name will be returned
        return Default;
    }
}