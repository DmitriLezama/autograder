package com.gophers.unit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;

import com.gophers.interfaces.Grade;
import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.structures.factory.GradeFactory;

public class GradeUnitTest {
    private List<Grade> grades;

    public GradeUnitTest(){
        GradeFactory factory = new GradeFactory();
        List<Result> results = new AssignmentTestRunner().runAllTests();
        this.grades = factory.createGrades(results);
    }

    
    @Test
    public void testGetMarks(){
        for(Grade g : this.grades)
            Assert.assertNotNull(g.getMarks());
    }

    @Test
    public void testGetTotalMarks(){
        for(Grade g : this.grades)
            Assert.assertNotNull(g.getTotalMarks());
    }

    @Test
    public void testGetFailedFeedback(){
        for(Grade g : this.grades)
            Assert.assertNotNull(g.getFailedFeedback());
    }
    
}
