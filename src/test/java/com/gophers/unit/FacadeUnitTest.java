package com.gophers.unit;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import com.gophers.services.handlers.GradingFacade;
import com.gophers.structures.domain.AssignmentDetails;

public class FacadeUnitTest {

    @Test
    public void testProcessSubmission(){
        GradingFacade gradingFacade = new GradingFacade();
        String filePath = "src\\main\\resources\\studentSubmissions\\Brandon_Chandoo_816034693_A1";
        AssignmentDetails result;
        result = gradingFacade.processSubmission(filePath);
        Assert.assertTrue(result != null);
    }

    @Test
    public void testProcessSubmissionsResultsGenerated() {
        GradingFacade gradingFacade = new GradingFacade();
        String zipFilePath = "src\\main\\resources\\submissions.zip";
        gradingFacade.processSubmissions(zipFilePath);
        File outputDir = new File("src/main/resources/studentSubmissions"); 
        Assert.assertTrue(outputDir.list().length > 0);
    }
    
}
