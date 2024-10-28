package com.gophers.services;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gophers.data.ChatBotSimulation;

/*This class contains units tests for the ChatBotSimulation class.
 * The purpose of these is to verify the proper functioning of the main method of ChatBotSimulation,
 * which includes checking the initialization of the ChatBotPlatform, adding various types of chatbots,
 * interacting with the chatbots, and printing out summmary statistics.
 * The tests ensure that all the requirements specified for the ChatBotSimulation are met,
 * such as the correct output messages and appropriate behaviour during execution.
 */
public class ChatBotSimulationTest {
    //Captures the output printed to the console during test execution.
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    /*
     * Sets up the output stream to capture console output before each test.
     * This method redirects the standard output stream (System.out) to a ByteArrayOutputStream,
     * allowing us to capture and analyze console output produced during the execution of the main method
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /*
     * Tests that the main method starts by printing "Hello World!" to the console.
     */
    @Test
    public void testMainStartsWithHelloWorld() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.startsWith("Hello World!"), "Should start with 'Hello World!'");
    }
    
    @Test
    public void testChatBotPlatformInitialization() throws Exception {
        String filePath = "src/main/java/com/gophers/data/ChatBotSimulation.java"; // Adjust the path as necessary
        boolean lineFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("ChatBotPlatform") && line.contains("new ChatBotPlatform()")) {
                    lineFound = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(lineFound,
                "The line 'ChatBotPlatform anyvariablename = new ChatBotPlatform();' was not found in the main method.");
    }
    

    @Test
    public void testChatBotsAdded() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Your ChatBots"), "Output should contain 'Your ChatBots' section");
        // assertTrue(output.contains("Bot Number"), "Output should list 'Bot Number:'
        // for each bot");
        assertTrue(output.contains("LLaMa"), "Output should contain 'LLaMa'");
        assertTrue(output.contains("Mistral7B"), "Output should contain 'Mistral7B'");
        assertTrue(output.contains("Bard"), "Output should contain 'Bard'");
        assertTrue(output.contains("Claude"), "Output should contain 'Claude'");
        assertTrue(output.contains("Solar"), "Output should contain 'Solar'");
        long botCount = output.lines().filter(line -> line.startsWith("Bot Number:")).count();
        assertTrue(botCount >= 1, "At least one ChatBot should be added");
    }

    @Test
    public void testChatBotInteraction() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        long botInteractions = output.lines()
                .filter(line -> line.startsWith("(Message#") ||
                        line.startsWith("Incorrect Bot Number") ||
                        line.startsWith("Daily Limit Reached"))
                .count();
        assertEquals(15, botInteractions);
    }

    @Test
    public void testChatBotSummaryStatistics() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Your ChatBots"), "Output should contain 'Your ChatBots' section");
        // assertTrue(output.contains("Number Messages Used:"), "Output should contain
        // 'Number Messages Used'");
        assertTrue(output.contains("Total Messages Used:"), "Output should contain 'Total Messages Used:'");
        assertTrue(output.contains("Total Messages Remaining:"), "Output should contain 'Total Messages Remaining:'");
    }

    @Test
    public void testFinalChatBotSummaryStatistics() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        int totalMessagesIndex = output.indexOf("Total Messages Used:");
        assertTrue(totalMessagesIndex != -1, "'Total Messages Used:' should exist in the output");
        
        String substring = output.substring(totalMessagesIndex + "Total Messages Used:".length()).trim();
        assertTrue(substring.contains("Your ChatBots"), 
                "'Your ChatBots' should appear after 'Total Messages Used:'");
    }
}
