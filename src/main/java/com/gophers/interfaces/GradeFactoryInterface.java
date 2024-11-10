package com.gophers.interfaces;

import java.util.List;

import org.junit.runner.Result;

public interface GradeFactoryInterface {
    public abstract List<Grade> createGrades(List<Result> results);

    public abstract Grade createGrade(Result result, int index);
}
