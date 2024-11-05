package com.gophers;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Submission {
    private static Submission instance;
    public static String submissionDirectory;
    public String ChatBotFile;
    public String ChatBotGeneratorFile;
    public String ChatBotPlatformFile;
    public static String ChatBotSimulationFile;

    private static URLClassLoader classLoader;

    private Submission(String submissionDirectory) {
        setDirectory(submissionDirectory);
        if (!compileAllClasses()) {
            System.err.println("Compilation failed for one or more files in " + submissionDirectory);
        }

        try {
            classLoader = URLClassLoader.newInstance(new URL[] { new File(submissionDirectory).toURI().toURL() });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Submission getInstance(String submissionDirectory) {
        if (instance == null) {
            instance = new Submission(submissionDirectory);
        }
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
        this.ChatBotSimulationFile = submissionDirectory + "/ChatBotSimulation.java";
    }

    public String toString() {
        return "Submission Directory: " + submissionDirectory + "\n";
    }

    private boolean compileAllClasses() {
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
            System.err.println("Class " + className + " not found in directory: " + submissionDirectory);
            return null;
        }
    }

    public void invokeMethod(String className, String methodName, Class<?>[] paramTypes, Object... params) {
        try {
            Class<?> clazz = getClass(className);
            if (clazz != null) {
                Method method = clazz.getMethod(methodName, paramTypes);
                Object instance = clazz.getDeclaredConstructor(int.class).newInstance(1);
                Object result = method.invoke(instance, params);
                System.out.println("Method " + methodName + " invoked successfully. Result: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
