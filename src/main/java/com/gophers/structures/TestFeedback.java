package com.gophers.structures;

public class TestFeedback {
    private String feedback;
    private int priority;

    public TestFeedback(String feedback, int priority) {
        this.feedback = feedback;
        this.priority = priority;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getPriority() {
        return priority;
    }
}
