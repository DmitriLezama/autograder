package com.gophers.structures.domain;

public class TestFeedback {
    private String testName;
    private int marksEarned;

    public TestFeedback(String testName, int marksEarned) {
        this.testName = testName;
        this.marksEarned = marksEarned;
    }

    public String getTestName() {
        return testName;
    }

    public int getmarksEarned() {
        return marksEarned;
    }
}
