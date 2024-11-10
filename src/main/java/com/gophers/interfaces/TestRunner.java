package com.gophers.interfaces;

import org.junit.runner.Result;
import java.util.List;

public interface TestRunner {
    public abstract List<Result> runAllTests();
}
