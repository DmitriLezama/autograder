package com.gophers.performance;

import com.gophers.interfaces.PDF;
import com.gophers.interfaces.Grade;
import com.gophers.structures.domain.*;
import com.gophers.utilities.Constants;
import com.gophers.utilities.PDFGenerator;
import org.junit.Assert;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PDFPerformanceTest {

    @Test
    public void testGeneratePDFPerformance() throws Exception {
        PDF pdfGenerator = new PDFGenerator();
        AssignmentDetails assignmentDetails = new AssignmentDetails(
                new StudentDetails("John_Doe_816123456_A1"),
                new AssignmentGrade(createMockGrades()));
        String outputPath = Paths.get(Constants.OUTPUT_DIRECTORY, "John_Doe_816123456_A1",
                "John_Doe_816123456_A1" + Constants.PDF_EXTENSION).toString();
        long startTime = System.nanoTime();
        pdfGenerator.generate(assignmentDetails);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        Assert.assertTrue("PDF not generated", Files.exists(Paths.get(outputPath)));
        Assert.assertTrue("Generation too slow: " + duration + " ms", duration < 100);
        System.out.println("PDF generation time: " + duration + " ms");
    }

    private List<Grade> createMockGrades() {
        return List.of(new Grade() {
            public int getMarks() {
                return 20;
            }

            public int getTotalMarks() {
                return 100;
            }

            public List<TestFeedback> getFailedFeedback() {
                return List.of(new TestFeedback("Feedback1", 99));
            }
        });
    }
}
