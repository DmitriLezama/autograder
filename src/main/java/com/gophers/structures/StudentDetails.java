package com.gophers.structures;

public class StudentDetails {
    private String firstName;
    private String lastName;
    private String studentID;

    public StudentDetails(String studentID, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
    }

    public String getStudentName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getStudentID() {
        return this.studentID;
    }

}
