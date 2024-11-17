package com.gophers.utilities;

public final class Constants {
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }

    public static final String SUBMISSIONS = "submissions.zip";
    public static final String MARK_SCHEME = "mark_scheme.yaml";
    public static final String OUTPUT_DIRECTORY = "src/main/resources/studentSubmissions";
    public static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    public static final String STUDENT_SUBMISSIONS_DIR = OUTPUT_DIRECTORY;
    public static final String ZIP_EXTENSION = ".zip";
    public static final String PDF_EXTENSION = ".pdf";
    
    public static final String PROGRAM_GRADE = "ProgramGrade";
    public static final String CHATBOT_GENERATOR_GRADE = "ChatBotGeneratorGrade";
    public static final String CHATBOT_GRADE = "ChatBotGrade";
    public static final String CHATBOT_PLATFORM_GRADE = "ChatBotPlatformGrade";
    public static final String CHATBOT_SIMULATION_GRADE = "ChatBotSimulationGrade";
    public static final int PROGRAM_GRADE_INDEX = 0;
    public static final int CHATBOT_GENERATOR_GRADE_INDEX = 1;
    public static final int CHATBOT_GRADE_INDEX = 2;
    public static final int CHATBOT_PLATFORM_GRADE_INDEX = 3;
    public static final int CHATBOT_SIMULATION_GRADE_INDEX = 4;
    public static final int PROGRAM_GRADE_TOTAL_MARKS = 15;
    public static final int CHATBOT_GENERATOR_GRADE_TOTAL_MARKS = 7;
    public static final int CHATBOT_GRADE_TOTAL_MARKS = 36;
    public static final int CHATBOT_PLATFORM_GRADE_TOTAL_MARKS = 20;
    public static final int CHATBOT_SIMULATION_GRADE_TOTAL_MARKS = 12;
    public static final int ASSIGNMENT_TOTAL_MARKS = 100;
    
    public static final String[] CRITERIA = { "Bonus", "ChatBotGenerator", "ChatBot", "ChatBotPlatform",
            "ChatBotSimulation" };
    public static final String PASSED = "Pass";
    public static final String FAILED = "Fail";
    public static final String FEEDBACK = "FeedBack";
    public static final String PASSES_ALL_JUNIT_TESTS = "passesAllJUnitTests";
    public static final String BONUS = "Bonus";
    public static final String TOTAL = "Total";
    public static final String STUDENT_PERCENTAGE = "StudentPercentage";
}