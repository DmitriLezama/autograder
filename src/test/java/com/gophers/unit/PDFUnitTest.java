package com.gophers.unit;

import com.gophers.interfaces.PDF;
import com.gophers.services.handlers.PDFGenerator;
import com.gophers.structures.domain.*;
import com.gophers.interfaces.Grade;
import com.gophers.utilities.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class PDFUnitTest {
    @Test
    public void testGeneratePDF() throws Exception {
        PDF pdfGenerator = new PDFGenerator();
        AssignmentDetails assignmentDetails = new AssignmentDetails(
                new StudentDetails("John_Doe_816123456_A1"),
                new AssignmentGrade(createMockGrades()));
        String outputPath = Paths.get(Constants.OUTPUT_DIRECTORY, "John_Doe_816123456_A1",
                "John_Doe_816123456_A1" + Constants.PDF_EXTENSION).toString();
        pdfGenerator.generate(assignmentDetails);
        assertTrue("PDF not generated", Files.exists(Paths.get(outputPath)));

    }

    private List<Grade> createMockGrades() {
        return List.of(new Grade() {
            public int getMarks() {
                return 20;
            }

            public int getTotalMarks() {
                return 100;
            }

            public Map<String, Integer> getTestFeedback() {
                return Map.of("Feedback1", 1);
            }
        });
    }
}
