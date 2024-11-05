package com.gophers;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Submission {
    private static boolean isCompiled;
    private static Submission instance;
    public static String submissionDirectory;
    public static String ChatBotFile;
    public static String ChatBotGeneratorFile;
    public static String ChatBotPlatformFile;
    public static String ChatBotSimulationFile;

    private static URLClassLoader classLoader;

    private Submission(String submissionDirectory) {
        setDirectory(submissionDirectory);
        if (!compileAllClasses()) {
            // System.err.println("Compilation failed for one or more files in " +
            // submissionDirectory);
            isCompiled = false;
        }
        isCompiled = true;
        try {
            classLoader = URLClassLoader.newInstance(new URL[] { new File(submissionDirectory).toURI().toURL() });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Submission getInstance(String submissionDirectory) {
        if (instance == null)
            instance = new Submission(submissionDirectory);
        return instance;
    }

    public static void resetInstance(String newSubmissionDirectory) {
        instance = new Submission(newSubmissionDirectory);
    }

    private void setDirectory(String submissionDirectory) {
        Submission.submissionDirectory = submissionDirectory;
        this.ChatBotFile = submissionDirectory + "/ChatBot.java";
        this.ChatBotGeneratorFile = submissionDirectory + "/ChatBotGenerator.java";
        this.ChatBotPlatformFile = submissionDirectory + "/ChatBotPlatform.java";
        ChatBotSimulationFile = submissionDirectory + "/ChatBotSimulation.java";
    }

    public String toString() {
        return "Submission Directory: " + submissionDirectory + "\n";
    }

    public static boolean isCompiled() {
        return isCompiled;
    }

    public static boolean compileAllClasses() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("Java Compiler not available. Make sure you are running with JDK.");
            return false;
        }
        // Classpath as the directory of the submission files
        String classpath = submissionDirectory;
        // Compile all files at once
        int compilationResult = compiler.run(null, null, null,
                "-classpath", classpath,
                ChatBotFile, ChatBotGeneratorFile,
                ChatBotPlatformFile, ChatBotSimulationFile);

        return compilationResult == 0;
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
