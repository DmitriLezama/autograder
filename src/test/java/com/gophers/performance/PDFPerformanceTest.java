package com.gophers.performance;

import com.gophers.interfaces.PDF;
import com.gophers.structures.domain.*;
import com.gophers.interfaces.Grade;
import com.gophers.utilities.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class PDFPerformanceTest {
    @Test
    public void testGeneratePDFPerformance() throws Exception {
        PDF pdfGenerator = new PDFGenerator();
        AssignmentDetails assignmentDetails = new AssignmentDetails(
                new StudentDetails("John_Doe_816123456_A1"),
                new AssignmentGrade(createMockGrades()));
        String outputPath = Paths.get(Constants.OUTPUT_DIRECTORY, "John_Doe_816123456_A1",
                "John_Doe_816123456_A1" + Constants.PDF_EXTENSION).toString();
        PerformanceTestResult result = ExecutionTimer.testExecutionTime(
                () -> pdfGenerator.generate(assignmentDetails),
                TestConstants.MAX_ALLOWABLE_THRESHOLD_MS,
                "PDF Generation");
        assertTrue("PDF not generated", Files.exists(Paths.get(outputPath)));
        assertTrue("Test execution took too long", result.isSuccess());
    }

    private List<Grade> createMockGrades() {
        return List.of(new Grade() {
            public int getMarks() {
                return 20;
            }

            public int getTotalMarks() {
                return 100;
            }

            public Map<String, Boolean> getTestFeedback() {
                return Map.of("Feedback1", true);
            }
        });
    }
}
