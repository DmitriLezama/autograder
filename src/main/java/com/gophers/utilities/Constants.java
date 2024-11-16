package com.gophers.utilities;

/**
 * Constants class containing static final variables used across the application.
 * Provides file paths, file extensions, grading criteria, grading indexes, and status labels.
 */
public final class Constants {

    /**
     * Private constructor to prevent instantiation of the Constants class.
     */
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }

    /** Submission ZIP file name. */
    public static final String SUBMISSIONS = "submissions.zip";
    
    /** Mark scheme file name. */
    public static final String MARK_SCHEME = "mark_scheme.yaml";
    
    /** Directory for student submissions. */
    public static final String OUTPUT_DIRECTORY = "src/main/resources/studentSubmissions";
    
    /** PDF template path. */
    public static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    
    /** Directory for student submissions. */
    public static final String STUDENT_SUBMISSIONS_DIR = OUTPUT_DIRECTORY;

    /** ZIP file extension. */
    public static final String ZIP_EXTENSION = ".zip";
    
    /** PDF file extension. */
    public static final String PDF_EXTENSION = ".pdf";

    /** Program grading category. */
    public static final String PROGRAM_GRADE = "ProgramGrade";
    
    /** ChatBot Generator grading category. */
    public static final String CHATBOT_GENERATOR_GRADE = "ChatBotGeneratorGrade";
    
    /** ChatBot grading category. */
    public static final String CHATBOT_GRADE = "ChatBotGrade";
    
    /** ChatBot Platform grading category. */
    public static final String CHATBOT_PLATFORM_GRADE = "ChatBotPlatformGrade";
    
    /** ChatBot Simulation grading category. */
    public static final String CHATBOT_SIMULATION_GRADE = "ChatBotSimulationGrade";

    /** Index for Program Grade in grading array. */
    public static final int PROGRAM_GRADE_INDEX = 0;
    
    /** Index for ChatBot Generator Grade in grading array. */
    public static final int CHATBOT_GENERATOR_GRADE_INDEX = 1;
    
    /** Index for ChatBot Grade in grading array. */
    public static final int CHATBOT_GRADE_INDEX = 2;
    
    /** Index for ChatBot Platform Grade in grading array. */
    public static final int CHATBOT_PLATFORM_GRADE_INDEX = 3;
    
    /** Index for ChatBot Simulation Grade in grading array. */
    public static final int CHATBOT_SIMULATION_GRADE_INDEX = 4;

    /** Total marks for Program Grade. */
    public static final int PROGRAM_GRADE_TOTAL_MARKS = 15;
    
    /** Total marks for ChatBot Generator Grade. */
    public static final int CHATBOT_GENERATOR_GRADE_TOTAL_MARKS = 7;
    
    /** Total marks for ChatBot Grade. */
    public static final int CHATBOT_GRADE_TOTAL_MARKS = 36;
    
    /** Total marks for ChatBot Platform Grade. */
    public static final int CHATBOT_PLATFORM_GRADE_TOTAL_MARKS = 20;
    
    /** Total marks for ChatBot Simulation Grade. */
    public static final int CHATBOT_SIMULATION_GRADE_TOTAL_MARKS = 12;
    
    /** Total marks for the entire assignment. */
    public static final int ASSIGNMENT_TOTAL_MARKS = 100;

    /** Array of grading criteria categories. */
    public static final String[] CRITERIA = { "Bonus", "ChatBotGenerator", "ChatBot", "ChatBotPlatform", "ChatBotSimulation" };

    /** Label for passing status. */
    public static final String PASSED = "Pass";
    
    /** Label for failing status. */
    public static final String FAILED = "Fail";
    
    /** Feedback label. */
    public static final String FEEDBACK = "FeedBack";
    
    /** Label for passing all JUnit tests status. */
    public static final String PASSES_ALL_JUNIT_TESTS = "passesAllJUnitTests";
    
    /** Label for bonus criteria. */
    public static final String BONUS = "Bonus";
    
    /** Label for total score. */
    public static final String TOTAL = "Total";
    
    /** Label for student percentage. */
    public static final String STUDENT_PERCENTAGE = "StudentPercentage";
}
