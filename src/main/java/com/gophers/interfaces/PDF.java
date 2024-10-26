package com.gophers.interfaces;

import com.gophers.structures.AssignmentGrade;

public interface PDF {
    public abstract void generate(AssignmentGrade assignmentGrade);
}
