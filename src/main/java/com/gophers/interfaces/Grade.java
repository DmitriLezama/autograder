package com.gophers.interfaces;

import java.util.List;
import com.gophers.structures.TestFeedback;

public interface Grade {
    public abstract int getMarks();

    public abstract int getTotalMarks();

    public abstract List<TestFeedback> getFailedFeedback();
}
