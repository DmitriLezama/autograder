package com.gophers.interfaces;

import java.util.List;
import org.junit.runner.Result;

public interface Factory<T> {
    public abstract List<T> createItems(List<Result> results);

    public abstract T createItem(Result result, int index);
}
