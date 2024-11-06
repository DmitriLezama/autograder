package com.gophers.structures.factory;

import org.junit.runner.Result;
import java.util.ArrayList;
import java.util.List;
import com.gophers.interfaces.Grade;

public abstract class AbstractGradeFactory {
    public List<Grade> createGrades(List<Result> results) {
        List<Grade> grades = new ArrayList<Grade>();
        for (int i = 0; i < results.size(); i++)
            grades.add(createGrade(results.get(i), i));
        return grades;
    }

    protected abstract Grade createGrade(Result result, int index);
}
