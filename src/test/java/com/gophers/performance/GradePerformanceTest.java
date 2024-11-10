package com.gophers.performance;
import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.structures.factory.GradeFactory;
import org.junit.runner.Result;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class GradePerformanceTest {
    @Test
    public void creatGradePerformace(){
        GradeFactory factory = new GradeFactory();
        List<Result> results = new AssignmentTestRunner().runAllTests();
        long startTime = System.nanoTime();
        factory.createGrades(results);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        long performanceThreshold = 1000; 
        System.out.println("Execution time for createGrades: " + duration + " ms");
        Assert.assertTrue("Test execution took too long: " + duration + " ms", duration < performanceThreshold);     
        
    }
    
}