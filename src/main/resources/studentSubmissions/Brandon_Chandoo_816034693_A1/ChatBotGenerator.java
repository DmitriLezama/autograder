//816034693
public class ChatBotGenerator {
    public String generateChatBotLLM(int LLMCodeNumber) {
        if (LLMCodeNumber == 1)
            return "LLaMa";
        else if (LLMCodeNumber == 2)
            return "Mistral7B";
        else if (LLMCodeNumber == 3)
            return "Bard";
        else if (LLMCodeNumber == 4)
            return "Claude";
        else if (LLMCodeNumber == 5)
            return "Solar";
        else
            return "ChatGPT-3.5";
    }
// it is assumed this function is used in the case where a string is passed into a ChatBot constructor
    public String generateChatBotLLM(String botName) {
        if (botName.equalsIgnoreCase("LLaMa"))
            return "LLaMa";
        else if (botName.equalsIgnoreCase("Mistral7B"))
            return "Mistral7B";
        else if (botName.equalsIgnoreCase("Bard"))
            return "Bard";
        else if (botName.equalsIgnoreCase("Claude"))
            return "Claude";
        else if (botName.equalsIgnoreCase("Solar"))
            return "Solar";
        else
            return "ChatGPT-3.5";
    }
// equalsIgnoreCase learnt from: https://www.w3schools.com/java/ref_string_equalsignorecase.asp#:~:text=The%20equalsIgnoreCase()%20method%20compares,strings%20lexicographically%2C%20ignoring%20case%20differences.
}