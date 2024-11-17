package com.gophers.structures.factory;

import org.junit.runner.Result;
import java.util.ArrayList;
import java.util.List;

import com.gophers.interfaces.Factory;
import com.gophers.interfaces.Grade;

public abstract class AbstractGradeFactory implements Factory<Grade> {
    public List<Grade> createItems(List<Result> results) {
        List<Grade> grades = new ArrayList<>();
        for (int i = 0; i < results.size(); i++)
            grades.add(createItem(results.get(i), i));
        return grades;
    }

    public abstract Grade createItem(Result result, int index);
}
