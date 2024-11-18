package com.gophers.unit;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Test;
import com.gophers.services.handlers.GradingFacade;

public class FacadeUnitTest {
    private static final PrintStream originalOut = System.out;

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
