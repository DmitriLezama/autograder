package com.gophers.utilities;

public final class Constants {
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }

    public static final String OUTPUT_DIRECTORY = "src/main/resources/studentSubmissions";
    public static final String TEMPLATE_PATH = "src/main/resources/Template.pdf";
    public static final String STUDENT_SUBMISSIONS_DIR = OUTPUT_DIRECTORY;
    public static final String ZIP_EXTENSION = ".zip";
    public static final int PROGRAM_GRADE = 0;
    public static final int CHATBOT_GENERATOR_GRADE = 1;
    public static final int CHATBOT_GRADE = 2;
    public static final int CHATBOT_PLATFORM_GRADE = 3;
    public static final int CHATBOT_SIMULATION_GRADE = 4;
}
