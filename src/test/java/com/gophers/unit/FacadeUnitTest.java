package com.gophers.unit;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Test;
import com.gophers.services.handlers.GradingFacade;
import com.gophers.structures.domain.AssignmentDetails;

public class FacadeUnitTest {
    private static final PrintStream originalOut = System.out;

    @Test
    public void testProcessSubmission() {
        GradingFacade gradingFacade = new GradingFacade();
        String filePath = "src/main/resources/studentSubmissions/Brandon_Chandoo_816034693_A1";
        AssignmentDetails result;
        result = gradingFacade.processSubmission(filePath);
        assertTrue("Submission failed to be processed", result != null);
    }

    @Test
    public void testProcessSubmissionsResultsGenerated() {
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        GradingFacade gradingFacade = new GradingFacade();
        String zipFilePath = "src/main/resources/submissions.zip";
        gradingFacade.processSubmissions(zipFilePath);
        File outputDir = new File("src/main/resources/studentSubmissions");
        System.setOut(originalOut);
        assertTrue("Submissions failed to be processed", outputDir.list().length > 0);
    }
}
