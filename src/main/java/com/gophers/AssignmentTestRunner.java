package com.gophers;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import com.gophers.services.ChatBotTest;
import com.gophers.services.ProgramTest;
import com.gophers.structures.AssignmentDetails;
import com.gophers.structures.AssignmentGrade;
import com.gophers.structures.StudentDetails;
import com.gophers.structures.factory.AbstractGradeFactory;
import com.gophers.structures.factory.GradeFactory;
import com.gophers.utilities.PDFGenerator;
import com.gophers.interfaces.PDF;
import com.gophers.services.ChatBotGeneratorTest;
import com.gophers.services.ChatBotPlatformTest;
import com.gophers.services.ChatBotSimulationTest;

public class AssignmentTestRunner {

    private final List<Class<?>> testClasses;

    public AssignmentTestRunner() {
        testClasses = new ArrayList<>();
        testClasses.add(ProgramTest.class);
        testClasses.add(ChatBotGeneratorTest.class);
        testClasses.add(ChatBotTest.class);
        testClasses.add(ChatBotPlatformTest.class);
        testClasses.add(ChatBotSimulationTest.class);
    }

    public Result runTest(Class<?> testname) {
        JUnitCore core = new JUnitCore();
        Result result = core.run(testname);
        return result;
    }

    public List<Result> runAllTest() {
        List<Result> results = new ArrayList<Result>();
        for (Class<?> testname : testClasses) {
            Result result = runTest(testname);
            results.add(result);
        }
        return results;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: Submission directory not provided.");
            System.exit(1);
        }

        String submissionDirectory = args[0]; // Read the directory from args
        AssignmentTestRunner runner = new AssignmentTestRunner();
        List<Result> results = runner.runAllTest();

        AbstractGradeFactory gradeFactory = new GradeFactory();
        AssignmentGrade assignmentGrade = new AssignmentGrade(gradeFactory.createGrades(results));
        StudentDetails student = new StudentDetails(submissionDirectory);

        System.out.println("Results:\n" + assignmentGrade.toString());

        PDF pdf = new PDFGenerator();
        pdf.generate(new AssignmentDetails(student, assignmentGrade));
        System.out.println("PDF generated");
    }
}
