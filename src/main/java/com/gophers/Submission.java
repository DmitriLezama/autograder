package com.gophers;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

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
        isCompiled = compileAllClasses();
        try {
            classLoader = URLClassLoader.newInstance(new URL[] { new File(submissionDirectory).toURI().toURL() });
        } catch (Exception e) {
            System.out.println("Class could not compile");
            // e.printStackTrace();
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
        ChatBotFile = submissionDirectory + "/ChatBot.java";
        ChatBotGeneratorFile = submissionDirectory + "/ChatBotGenerator.java";
        ChatBotPlatformFile = submissionDirectory + "/ChatBotPlatform.java";
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

        // Collect all .java files in the submission directory
        File directory = new File(submissionDirectory);
        List<String> javaFiles = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles())
                if (file.isFile() && file.getName().endsWith(".java"))
                    javaFiles.add(file.getPath());
        }

        // If no .java files are found, return false
        if (javaFiles.isEmpty()) {
            System.out.println("No Java source files found for compilation.");
            return false;
        }

        List<String> compilerArgs = new ArrayList<>();
        compilerArgs.add("-classpath");
        compilerArgs.add(submissionDirectory);
        compilerArgs.addAll(javaFiles); // Add all .java files
        String[] argsArray = compilerArgs.toArray(new String[0]);
        int compilationResult = compiler.run(null, null, null, argsArray);

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
