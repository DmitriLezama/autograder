package com.gophers.structures.domain;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import com.gophers.interfaces.CompileCommand;
import com.gophers.services.helpers.JavaCompileCommand;

public class Submission {
    private static boolean isCompiled;
    private static Submission instance;
    private static String submissionDirectory;
    private static URLClassLoader classLoader;

    private Submission(String submissionDirectory) {
        Submission.submissionDirectory = submissionDirectory;
        CompileCommand compileCommand = new JavaCompileCommand(submissionDirectory);
        isCompiled = compileCommand.compile();
        try {
            classLoader = URLClassLoader.newInstance(new URL[] { new File(submissionDirectory).toURI().toURL() });
        } catch (Exception e) {
            System.err.println("Class could not be loaded");
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

    public static boolean isCompiled() {
        return isCompiled;
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static String getSubmissionDirectory() {
        return submissionDirectory;
    }
}
