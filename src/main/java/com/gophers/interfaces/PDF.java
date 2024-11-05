package com.gophers.interfaces;

import com.gophers.structures.domain.AssignmentDetails;

public interface PDF {
    public abstract void generate(AssignmentDetails assignmentDetails);
}
