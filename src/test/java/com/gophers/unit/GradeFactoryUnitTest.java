package com.gophers.unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;

import com.gophers.services.handlers.AssignmentTestRunner;
import com.gophers.structures.factory.GradeFactory;
import com.gophers.interfaces.Grade;

public class GradeFactoryUnitTest {

    @Test
    public void TestCreateGrade(){
        List<Grade> grades = new ArrayList<Grade>();
        GradeFactory factory = new GradeFactory();
        List<Result> results = new AssignmentTestRunner().runAllTests();
        for(int i = 0; i < results.size(); i++)
            grades.add(factory.createItem(results.get(i), i));
        for(Grade g : grades)
            Assert.assertTrue("Grade should not be null",g != null);      
    }
    

    @Test
    public void TestCreateGrades(){
        GradeFactory factory = new GradeFactory();
        List<Result> results = new AssignmentTestRunner().runAllTests();
        List<Grade> grades = factory.createItems(results);
        for(Grade g : grades)
          Assert.assertTrue("Grade should not be null",g != null);  
    }
    
}
