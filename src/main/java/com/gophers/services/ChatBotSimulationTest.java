package com.gophers.services;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import com.gophers.data.ChatBot;
import com.gophers.data.ChatBotSimulation;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChatBotSimulationTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field messageNumberField = ChatBot.class.getDeclaredField("messageNumber");
        messageNumberField.setAccessible(true);
        messageNumberField.set(null, 0);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    // Test for requirement 1
    @Test
    public void testMainMethodStartsWithHelloWorld() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        assertTrue("Should start with 'Hello World!'", output.startsWith("Hello World!")); // 1 mark
    }

    // Test for requirement 2
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
                lineFound); // 1 mark
    }

    // Test for requirement 3: Adding ChatBots

    @Test
    public void testIndividualChatBotsAdded() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        // Check for individual ChatBots
        assertTrue("Output should contain 'LLaMa'", output.contains("LLaMa"));
        assertTrue("Output should contain 'Mistral7B'", output.contains("Mistral7B"));
        assertTrue("Output should contain 'Bard'", output.contains("Bard"));
        assertTrue("Output should contain 'Claude'", output.contains("Claude"));
        assertTrue("Output should contain 'Solar'", output.contains("Solar")); // 1 mark
    }

    @Test
    public void testAtLeastOneChatBotAdded() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        long botCount = output.lines().filter(line -> line.startsWith("Bot Number:")).count();
        assertTrue("At least one ChatBot should be added", botCount >= 1); // 1 mark
    }

    // Test for requirement 4: Printing ChatBot statistics
    @Test
    public void testChatBotsSectionPresent() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        assertTrue("Output should contain 'Your ChatBots' section", output.contains("Your ChatBots")); // 1 mark
    }

    @Test
    public void testChatBotSummaryStatisticsPresent() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        assertTrue("Output should contain 'Total Messages Used:'", output.contains("Total Messages Used:")); // 1 mark
        assertTrue("Output should contain 'Total Messages Remaining:'", output.contains("Total Messages Remaining:"));
    }

    // Test for requirement 5: Interactions with ChatBots
    // Check if any interactions occurred
    @Test
    public void testChatBotHasInteractions() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        long interactionCount = output.lines()
                .filter(line -> line.startsWith("(Message") ||
                        line.startsWith("Incorrect Bot Number") ||
                        line.startsWith("Daily Limit"))
                .count();

        assertTrue("There should be interactions with ChatBots.", interactionCount > 0); // 1 mark
    }

    // Check for presence of "(Message#" format indicating message interaction
    @Test
    public void testMessageFormatInInteractions() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        boolean messageFormatFound = output.lines().anyMatch(line -> line.startsWith("(Message"));
        assertTrue("Interaction messages should start with '(Message'", messageFormatFound); // 1 mark
    }

    // Check for handling of incorrect bot number messages
    @Test
    public void testIncorrectBotNumberHandling() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        boolean incorrectBotMessageFound = output.lines().anyMatch(line -> line.startsWith("Incorrect Bot Number"));
        assertTrue("Output should include 'Incorrect Bot Number' message if applicable.", incorrectBotMessageFound); // 1
    }

    // Check for exactly 15 interactions
    @Test
    public void testExactNumberOfInteractions() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        long interactionCount = output.lines()
                .filter(line -> line.startsWith("(Message#") ||
                        line.startsWith("Incorrect Bot Number") ||
                        line.startsWith("Daily Limit Reached"))
                .count();
        assertEquals("Should interact exactly 15 times with ChatBots.", 15, interactionCount); // 1 mark
    }

    // Test for requirement 6: Final summary statistics
    // Check that final summary output is not empty
    @Test
    public void testFinalSummaryNotEmpty() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();
        String[] lines = output.split("\n");

        assertTrue("Final summary output should not be empty.", lines.length > 0); // 1 mark
    }

    // Check that final summary contains the required summary statistics
    @Test
    public void testFinalSummaryStatistics() {
        ChatBotSimulation.main(null);
        String output = outputStreamCaptor.toString().trim();

        assertTrue("Final summary should contain 'Total Messages Used:'", output.contains("Total Messages Used:"));
        assertTrue("Final summary should contain 'Total Messages Remaining: 0'",
                output.contains("Total Messages Remaining: 0")); // 1 mark
    }

}