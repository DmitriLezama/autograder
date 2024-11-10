package com.gophers.services.testCases;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.gophers.services.handlers.TextProcessor;
import com.gophers.structures.domain.Submission;

import static org.junit.Assert.fail;

public class ChatBotSimulationTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Method main;
    private String GENERATED_MESSAGE_PATTERN = "(Message#";
    private String INCORRECT_BOT_PATTERN = "Incorrect Bot Number";
    private String DAILY_LIMIT_REACHED_PATTERN = "Daily Limit Reached";
    private String BOT_NUMBER_PATTERN = "Bot Number:";

    @Before
    public void setUp() throws Exception {
        main = Submission.getClass("ChatBotSimulation").getMethod("main", String[].class);
        System.setOut(new PrintStream(outputStreamCaptor));
        resetStaticFields();
    }

    public void resetStaticFields() {
        try {
            Field messageNumberField = Submission.getClass("ChatBot").getDeclaredField("messageNumber");;
            messageNumberField.setAccessible(true);
            messageNumberField.set(null, 0);
        } catch (Exception e) {}
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    // Test for requirement 1
    @Test
    public void testMainMethodStartsWithHelloWorld() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();
        assertTrue("Should start with 'Hello World!'", output.startsWith("Hello World!")); // 1 mark
    }

    // Test for requirement 2
    @Test
    public void testChatBotPlatformInitialization() {
        String filePath = Submission.getSubmissionDirectory() + "/ChatBotSimulation.java";
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
            fail("The file could not be found");
        }

        assertTrue(
                "The line 'ChatBotPlatform anyvariablename = new ChatBotPlatform();' was not found in the main method.",
                lineFound); // 1 mark
    }

    // Test for requirement 3: Adding ChatBots
    @Test
    public void testAllChatBotModelsPresent() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();

        long botCount = output.lines().filter(line -> TextProcessor.compareString(BOT_NUMBER_PATTERN, line)).count();
        assertTrue("Should have several ChatBots", botCount > 1);

        // Check for individual ChatBots
        assertTrue("Output should contain 'LLaMa'", TextProcessor.compareString("LLaMa", output));
        assertTrue("Output should contain 'Mistral7B'", TextProcessor.compareString("Mistral7B", output));
        assertTrue("Output should contain 'Bard'", TextProcessor.compareString("Bard", output));
        assertTrue("Output should contain 'Claude'", TextProcessor.compareString("Claude", output));
        assertTrue("Output should contain 'Solar'", TextProcessor.compareString("Solar", output));
        assertTrue("Output should contain 'ChatGPT-3.5'", TextProcessor.compareString("ChatGPT-3.5", output)); // 2 marks
    }

    // Test for requirement 4: Printing ChatBot statistics
    @Test
    public void testChatBotsSectionPresent() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();

        int messageSectionStart = output.indexOf("(Message");
        String initialSummarySection = output.substring(0, messageSectionStart).trim();

        assertTrue("Output should contain 'Your ChatBots' section",
                TextProcessor.compareString("Your ChatBots", initialSummarySection)); // 1 mark
    }

    @Test
    public void testInitialSummaryStatistics() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();

        int messageSectionStart = output.indexOf("(Message");
        String initialSummarySection = output.substring(0, messageSectionStart).trim();

        assertTrue("Output should contain 'Total Messages Used: 0'",
                TextProcessor.compareString("Total Messages Used: 0", initialSummarySection));
        assertTrue("Output should contain 'Total Messages Remaining: 10'",
                TextProcessor.compareString("Total Messages Remaining: 10", initialSummarySection)); // 1 mark
    }

    // :D
    // Test for requirement 5: Interactions with ChatBots
    // Check if any interactions occurred
    @Test
    public void testAtLeastOneInteractionOccurred() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();
        long interactionCount = output.lines()
                .filter(line -> TextProcessor.compareString(GENERATED_MESSAGE_PATTERN, line) ||
                                TextProcessor.compareString(INCORRECT_BOT_PATTERN, line) ||
                                TextProcessor.compareString(DAILY_LIMIT_REACHED_PATTERN, line))
                .count();

        assertTrue("There should be interactions with ChatBots.", interactionCount > 0); // 1 mark
    }

    // Check for presence of "(Message#" format indicating message interaction
    @Test
    public void testMessageNumberFormatInBotResponses() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();

        boolean messageFormatFound = output.lines().anyMatch(line -> TextProcessor.compareString(GENERATED_MESSAGE_PATTERN, line));
        assertTrue("Interaction messages should start with '(Message#'", messageFormatFound); // 1 mark
    }

    // Check for handling of incorrect bot number messages
    @Test
    public void testInvalidBotNumberResponsePresent() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();

        boolean incorrectBotMessageFound = output.lines().anyMatch(line -> TextProcessor.compareString(INCORRECT_BOT_PATTERN, line));
        assertTrue("Output should include 'Incorrect Bot Number' message if applicable.", incorrectBotMessageFound); // 1
    }

    // Check for exactly 15 interactions
    @Test
    public void testSimulationPerformsExactlyFifteenInteractions() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();
        long interactionCount = output.lines()
                .filter(line -> TextProcessor.compareString(GENERATED_MESSAGE_PATTERN, line) ||
                                TextProcessor.compareString(INCORRECT_BOT_PATTERN, line) ||
                                TextProcessor.compareString(DAILY_LIMIT_REACHED_PATTERN, line))
                .count();

        assertEquals("Should interact exactly 15 times with ChatBots.", 15, interactionCount); // 1 mark
    }

    // Test for requirement 6: Final summary statistics
    // Check that final summary output is not empty
    @Test
    public void testFinalSummaryNotEmpty() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();
        String summarySection = getFinalSummarySection(output);

        assertTrue("Final summary output should not be empty.", summarySection.lines().count() != 0); // 1 mark
    }

    // Check that final summary contains the required summary statistics
    // 1 mark
    @Test
    public void testFinalSummaryStatistics() throws Exception {
        String[] args = null;
        main.invoke(null, (Object) args);
        String output = outputStreamCaptor.toString().trim();
        String finalSummarySection = getFinalSummarySection(output);

        String totalMessagesUsedLine = extractLine(finalSummarySection, "Total Messages Used:");
        String totalMessagesRemainingLine = extractLine(finalSummarySection, "Total Messages Remaining:");

        assertTrue("Final summary should contain 'Total Messages Used:'",
                TextProcessor.compareString("Total Messages Used:", totalMessagesUsedLine));
        assertTrue("Final summary should contain 'Total Messages Remaining:'",
                TextProcessor.compareString("Total Messages Remaining:", totalMessagesRemainingLine));
        assertTrue("Total Messages Used should match the number of messages generated",
                extractNumberFromLine(totalMessagesUsedLine) != 0);
        assertTrue("Total Messages Remaining should correspond with number of messages used",
                extractNumberFromLine(totalMessagesRemainingLine) != 10);
    }

    private String getFinalSummarySection(String output) {
        int start = output.indexOf("(Message");
        return output.substring(start).trim();
    }

    private String extractLine(String output, String label) {
        return output.lines()
                .filter(l -> TextProcessor.compareString(label, l))
                .findFirst()
                .orElse("");
    }

    private int extractNumberFromLine(String line) {
        return Integer.parseInt(line.split(":")[1].trim());
    }
    
}