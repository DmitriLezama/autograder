//816008594
//ChatBotGenerator Class
public class ChatBotGenerator{
    
    //Methods
    //This method returns the name of an LLM based on an integer code (LLMCodeNumber)
    public String generateChatBotLLM(int LLMCodeNumber){
        if(LLMCodeNumber==1){
            return "LLaMa";
        }
        else if(LLMCodeNumber==2){
            return "Mistral7B";
        }
        else if(LLMCodeNumber==3){
            return "Bard";
        }
        else if(LLMCodeNumber==4){
            return "Claude";
        }
        else if(LLMCodeNumber==5){
            return "Solar";
        }
        else{
            return "ChatGPT-3.5";
        }
    }
    
    public String generateChatBotLLM(String botName){
        return botName;
    }
    
    
}

/*
Sources: 
Dr. Phaedra's slides

*/
