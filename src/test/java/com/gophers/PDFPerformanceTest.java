package com.gophers;

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

        long duration = (System.nanoTime() - System.nanoTime()) / 1_000_000;
        String outputPath = Paths.get(Constants.OUTPUT_DIRECTORY, "John_Doe_816123456_A1",
                "John_Doe_816123456_A1" + Constants.PDF_EXTENSION).toString();
        pdfGenerator.generate(assignmentDetails);
        Assert.assertTrue("PDF not generated", Files.exists(Paths.get(outputPath)));
        Assert.assertTrue("Generation too slow: " + duration + " ms", duration < 1500);
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
