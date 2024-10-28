package com.gophers.services;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import com.gophers.data.ChatBotSimulation;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChatBotSimulationTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainMethodStartsWithHelloWorld() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        assertTrue("Should start with 'Hello World!'", output.startsWith("Hello World!"));
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

        assertTrue(
                "The line 'ChatBotPlatform anyvariablename = new ChatBotPlatform();' was not found in the main method.",
                lineFound);
    }

    @Test
    public void testChatBotsAdded() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        assertTrue("Output should contain 'Your ChatBots' section", output.contains("Your ChatBots"));
        // assertTrue(output.contains("Bot Number"), "Output should list 'Bot Number:'
        // for each bot");
        assertTrue("Output should contain 'LLaMa'", output.contains("LLaMa"));
        assertTrue("Output should contain 'Mistral7B'", output.contains("Mistral7B"));
        assertTrue("Output should contain 'Bard'", output.contains("Bard"));
        assertTrue("Output should contain 'Claude'", output.contains("Claude"));
        assertTrue("Output should contain 'Solar'", output.contains("Solar"));
        long botCount = output.lines().filter(line -> line.startsWith("Bot Number:")).count();
        assertTrue("At least one ChatBot should be added", botCount >= 1);
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
        assertTrue("Output should contain 'Your ChatBots' section", output.contains("Your ChatBots"));
        // assertTrue(output.contains("Number Messages Used:"), "Output should contain
        // 'Number Messages Used'");
        assertTrue("Output should contain 'Total Messages Used:'", output.contains("Total Messages Used:"));
        assertTrue("Output should contain 'Total Messages Remaining:'", output.contains("Total Messages Remaining:"));
    }

    @Test
    public void testChatBotFinalSummary() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");
        assertTrue("Output should not be empty.", lines.length > 0);
        assertTrue("The last output line should contain 'Total Messages Used:'",
                output.contains("Total Messages Used"));
        assertTrue("The last output line should contain 'Total Messages Remaining: 0'",
                output.contains("Total Messages Remaining: 0"));
    }

}
