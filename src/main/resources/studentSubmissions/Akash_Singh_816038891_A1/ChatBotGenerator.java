
import java.util.ArrayList; //Learnt through the use of W3 Schools - https://www.w3schools.com/java/java_arraylist.asp
import java.util.Random;
import java.lang.*;
public class ChatBotGenerator{
    public static String generateChatBotLLM(int LLMCodeNumber){
        if(LLMCodeNumber == 1){
            return "LLaMa";
        } else if(LLMCodeNumber == 2){
            return "Mistral7B";
        } else if(LLMCodeNumber == 3){
            return "Bard";
        } else if(LLMCodeNumber == 4){
            return "Claude";
        } else if(LLMCodeNumber == 5){
            return "Solar";
        } else{
            return "ChatGPT-3.5";
        }
    }

}
