package com.gophers.interfaces;

import java.util.List;

public interface TestRunner<T> {
    public abstract List<T> runAllTests();
}
