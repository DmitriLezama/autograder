package com.gophers.services;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import com.gophers.data.ChatBot;
import com.gophers.data.ChatBotPlatform;
import com.gophers.data.ChatBotGenerator;
import com.gophers.data.ChatBotSimulation;

public class ProgramTest {

    @Test
    public void test_ChatBotCompiles() {
        assertTrue("ChatBot class should compile", doesClassCompile(ChatBot.class));
    }

    @Test
    public void test_ChatBotPlatformCompiles() {
        assertTrue("ChatBotPlatform class should compile", doesClassCompile(ChatBotPlatform.class));
    }

    @Test
    public void test_ChatBotGeneratorCompiles() {
        assertTrue("ChatBotGenerator class should compile", doesClassCompile(ChatBotGenerator.class));
    }

    @Test
    public void test_ChatBotSimulationCompiles() {
        assertTrue("ChatBotSimulation class should compile", doesClassCompile(ChatBotSimulation.class));
    }

    @Test
    public void testAllCompiles() {
        assertTrue("All required classes should compile",
                doesClassCompile(ChatBot.class) &&
                        doesClassCompile(ChatBotPlatform.class) &&
                        doesClassCompile(ChatBotGenerator.class) &&
                        doesClassCompile(ChatBotSimulation.class));
    }

    @Test
    public void testChatBotSimulationRuns() {
        try {
            ChatBotSimulation.main(null);
            assertTrue("ChatBotSimulation.main(null) should run without error", true);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("ChatBotSimulation.main(null) threw an exception: " + e.getMessage(), false);
        }
    }

    private static boolean doesClassCompile(Class<?> clazz) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null)
            throw new IllegalStateException("Java compiler not available. Ensure a JDK (not JRE) is used.");
        String baseDir = "src/main/java/";
        String classPath = baseDir + clazz.getName().replace('.', File.separatorChar) + ".java";
        File sourceFile = new File(classPath);

        if (!sourceFile.exists())
            throw new IllegalArgumentException("Source file not found for class: " + clazz.getName());

        int compilationResult = compiler.run(null, null, null, classPath);
        return compilationResult == 0;
    }
}
