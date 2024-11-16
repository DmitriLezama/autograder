package com.gophers.structures.domain;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import com.gophers.interfaces.CompileCommand;
import com.gophers.services.helpers.JavaCompileCommand;

/**
 * Singleton class representing a student's submission, including compilation
 * status and file loading.
 */
public class Submission {
    private static boolean isCompiled;
    private static Submission instance;
    private static String submissionDirectory;
    private static URLClassLoader classLoader;

    /**
     * Private constructor to initialize a submission by compiling the code and
     * setting up the class loader.
     *
     * @param submissionDirectory Path to the directory containing the submission
     *                            files.
     */
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

    /**
     * Retrieves the singleton instance of Submission. Creates one if it does not
     * exist.
     *
     * @param submissionDirectory Path to the submission directory.
     * @return The singleton instance of Submission.
     */
    public static Submission getInstance(String submissionDirectory) {
        if (instance == null)
            instance = new Submission(submissionDirectory);
        return instance;
    }

    /**
     * Resets the singleton instance with a new submission directory.
     *
     * @param newSubmissionDirectory Path to the new submission directory.
     */
    public static void resetInstance(String newSubmissionDirectory) {
        instance = new Submission(newSubmissionDirectory);
    }

    /**
     * Checks if the submission was successfully compiled.
     *
     * @return True if compilation was successful, otherwise false.
     */
    public static boolean isCompiled() {
        return isCompiled;
    }

    /**
     * Retrieves a class by its name using the submission's class loader.
     *
     * @param className Name of the class to load.
     * @return The loaded class or null if not found.
     */
    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Gets the directory path of the submission.
     *
     * @return The submission directory path.
     */
    public static String getSubmissionDirectory() {
        return submissionDirectory;
    }
}
