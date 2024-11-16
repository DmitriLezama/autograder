package com.gophers.interfaces;

import com.gophers.structures.domain.AssignmentDetails;

/**
 * The PDF interface defines a method for generating a PDF report.
 */
public interface PDF {
    /**
     * Generates a PDF report based on the provided assignment details.
     *
     * @param assignmentDetails the {@link AssignmentDetails} object containing
     *                          information
     *                          needed to create the PDF
     */
    public abstract void generate(AssignmentDetails assignmentDetails);
}
