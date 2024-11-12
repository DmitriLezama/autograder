package com.gophers.interfaces;

import java.util.Map;

public interface Grade {
    public abstract int getMarks();

    public abstract int getTotalMarks();

    public abstract Map<String, Boolean> getTestFeedback();
}
