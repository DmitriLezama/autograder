package com.gophers;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicClassLoader {

    public static void main(String[] args) {
        // Define paths for all relevant files
        String[] filePaths = {
                "src/main/resources/StudentSubmissions/Dmitri_Lezama_816035591_A1/ChatBotGenerator.java",
                "src/main/resources/StudentSubmissions/Dmitri_Lezama_816035591_A1/ChatBot.java"
        };

        try {
            if (!compileClasses(filePaths)) {
                System.out.println("Compilation failed.");
                return;
            }
            System.out.println("All compilations successful!");

            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {
                    new File("src/main/resources/StudentSubmissions/Dmitri_Lezama_816035591_A1").toURI().toURL() });

            String chatBotClassName = "ChatBot"; // Ensure this matches the actual class name
            Class<?> chatBotClass = Class.forName(chatBotClassName, true, classLoader);

            Object chatBotInstance = chatBotClass.getDeclaredConstructor(int.class).newInstance(5);

            Method promptMethod = chatBotClass.getMethod("prompt", String.class);
            String response = (String) promptMethod.invoke(chatBotInstance, "Hello!"); // Example request message
            System.out.println("Response: " + response);

        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    private static boolean compileClasses(String[] filePaths) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String classpath = System.getProperty("java.class.path");

        String[] compileArgs = new String[filePaths.length + 2];
        compileArgs[0] = "-classpath";
        compileArgs[1] = classpath;
        System.arraycopy(filePaths, 0, compileArgs, 2, filePaths.length);

        int compilationResult = compiler.run(null, null, null, compileArgs);
        return compilationResult == 0;
    }
}
